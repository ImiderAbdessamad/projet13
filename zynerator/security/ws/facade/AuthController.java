package ma.zs.zyn.zynerator.security.ws.facade;

import jakarta.validation.Valid;


import ma.zs.zyn.zynerator.security.bean.Role;
import ma.zs.zyn.zynerator.security.bean.RoleUser;
import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.common.AuthoritiesConstants;
import ma.zs.zyn.zynerator.security.common.SecurityParams;
import ma.zs.zyn.zynerator.security.dao.facade.core.RoleDao;
import ma.zs.zyn.zynerator.security.payload.request.ActivationRequest;
import ma.zs.zyn.zynerator.security.service.facade.UserService;
import ma.zs.zyn.zynerator.security.ws.dto.UserDto;
import ma.zs.zyn.zynerator.security.ws.converter.UserConverter;

import ma.zs.zyn.zynerator.security.jwt.JwtUtils;
import ma.zs.zyn.zynerator.security.payload.request.LoginRequest;
import ma.zs.zyn.zynerator.security.payload.response.JwtResponse;

import ma.zs.zyn.zynerator.transverse.emailling.EmailRequest;
import ma.zs.zyn.zynerator.transverse.emailling.EmailService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    EmailService emailService;

    @Autowired
    RoleDao roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("register/influencer")
    public ResponseEntity<Map<String, String>> registerInfluencer(@RequestBody UserDto userDto) {
        return register(userDto, AuthoritiesConstants.INFLUENCER);
    }

    @PostMapping("register/collaborator")
    public ResponseEntity<Map<String, String>> registerCollaborator(@RequestBody UserDto userDto) {
        return register(userDto, AuthoritiesConstants.COLLABORATOR);
    }

    @PostMapping("register/admin")
    public ResponseEntity<Map<String, String>> registerAdmin(@RequestBody UserDto userDto) {
        return register(userDto, AuthoritiesConstants.ADMIN);
    }



    private @NotNull ResponseEntity<Map<String, String>> register(UserDto userDto, String roleUsers) {
        if (userService.findByUsername(userDto.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("error", "This username has already been taken"));
        }
        if (userService.findByEmail(userDto.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("error", "This email is already on use "));
        }
        // Validate email format
        if (userDto.getEmail() == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("error", "Invalid email format ,valide format is:  xxxx@xxx.xxx"));
        }
        // Validate password length
        if (userDto.getPassword() == null || userDto.getPassword().length() < 8) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("error", "Password must be at least 8 characters long."));
        }

        LocalDateTime expirationDate = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
        userDto.setEnabled(false);
        userDto.setExpirationLinkDate(expirationDate);
        userDto.setLinkValidationCode(userService.generateCode(8));
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setFrom("votre email");
        emailRequest.setBcc(userDto.getEmail());
        emailRequest.setCc(userDto.getEmail());
        emailRequest.setTo(userDto.getEmail());
        emailRequest.setSubject("Verify your email");
        emailRequest.setBody("Welcome to Zynerator !! Your activation code is : " + userDto.getLinkValidationCode());
        userConverter.setRoleUsers(true);

        // Convertir le DTO en entité User
        User user = userConverter.toItem(userDto);

        // Initialiser et attacher RoleUser
        RoleUser roleUser = new RoleUser();
        Role role = new Role();
        role.setAuthority(roleUsers);
        roleUser.setRole(role);
        roleUser.setUser(user);
        user.setRoleUsers(Collections.singletonList(roleUser));

        // Créer et désactiver l'utilisateur
        userService.createAndDisable(user);
        emailService.sendSimpleMessage(emailRequest);

        Map<String, String> response = new HashMap<>();
        response.put("message", "You have registered successfully");
        return ResponseEntity.ok(response);
    }


    @PostMapping("activateAccount")
    public ResponseEntity<?> verifyUser(@RequestBody ActivationRequest activationRequest) {
        User user = userService.findByLinkValidationCode(activationRequest.getActivationCode());
        String username = activationRequest.getUsername();
        User userVerifier = userService.findByUsername(username);
        System.out.println(username);
        if (user == null) {
            return new ResponseEntity<>("Bad Crentials", HttpStatus.BAD_REQUEST);
        } else if (userVerifier == null) {
            System.out.println(username);
            System.out.println("userVerifier is null");
            return new ResponseEntity<>("Bad Crentials", HttpStatus.BAD_REQUEST);
        } else if (!user.getUsername().equals(userVerifier.getUsername())) {
            return new ResponseEntity<>("Bad Crentials", HttpStatus.BAD_REQUEST);
        } else if (!isActivationLinkValid(user)) {
            return new ResponseEntity<>("Activation code is not valid", HttpStatus.BAD_REQUEST);
        } else {
            user.setEnabled(true);
            userService.update(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    private boolean isActivationLinkValid(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = user.getExpirationLinkDate();
        return expiry != null && now.isBefore(expiry);
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getRoleUsers().stream().map(item -> item.getRole().getAuthority()).collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityParams.JWT_HEADER_NAME, SecurityParams.HEADER_PREFIX + jwt);
        return ResponseEntity.ok().headers(headers)
                .body(new JwtResponse(jwt, userDetails.getFirstName(), userDetails.getLastName(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }


    // Contrôleur pour envoyer le code d'activation de forget Password


    /*
    @PutMapping("forgetPassword")
    public ResponseEntity<Map<String, String>> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        User user = userService.findByEmail(forgetPasswordRequest.getEmail());

        if (user != null) {
            if (user.getLinkValidationCode().equals(forgetPasswordRequest.getLinkValidationCode())) {

                boolean passwordChanged = userService.changePassword(user.getUsername(), forgetPasswordRequest.getNewPassword());

                if (passwordChanged) {
                    EmailRequest emailRequest = new EmailRequest();
                    emailRequest.setFrom(user.getEmail());
                    emailRequest.setBcc(user.getEmail());
                    emailRequest.setCc(user.getEmail());
                    emailRequest.setTo(user.getEmail());
                    emailRequest.setSubject("Verify your email");
                    emailRequest.setBody("your password has been changed");
                    emailService.sendSimpleMessage(emailRequest);
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "check your email");
                    return ResponseEntity.ok(response);
                }
            } else {
                ResponseEntity.badRequest().body(Collections.singletonMap("error", "invalid verification code"));
            }
        }
        return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Your email  is uncorrect"));
    }

     */


}
