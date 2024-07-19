package ma.zs.zyn.zynerator.security.service.impl;


import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.coupon.Influenceur;
import ma.zs.zyn.service.impl.collaborator.collaborator.CollaboratorCollaboratorServiceImpl;
import ma.zs.zyn.service.impl.influenceur.coupon.InfluenceurInfluenceurServiceImpl;
import ma.zs.zyn.zynerator.security.bean.ModelPermissionUser;
import ma.zs.zyn.zynerator.security.bean.RoleUser;
import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.common.AuthoritiesConstants;
import ma.zs.zyn.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.zyn.zynerator.security.dao.facade.core.UserDao;
import ma.zs.zyn.zynerator.security.dao.specification.core.UserSpecification;
import ma.zs.zyn.zynerator.security.service.facade.*;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import ma.zs.zyn.zynerator.transverse.emailling.EmailRequest;
import ma.zs.zyn.zynerator.transverse.emailling.EmailService;
import ma.zs.zyn.zynerator.util.ListUtil;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserCriteria, UserDao> implements UserService {


    private static final String CHARACTERS = "0123456789";
    @Autowired
    private CollaboratorCollaboratorServiceImpl collaboratorService;

    @Autowired
    private InfluenceurInfluenceurServiceImpl influenceurService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public User create(User t) {
        return createWithEnable(t, true);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public User createAndDisable(User t) {
        return createWithEnable(t, false);
    }

    private User createWithEnable(User t, boolean enable) {
        User foundedUserByUsername = findByUsername(t.getUsername());
        User foundedUserByEmail = dao.findByEmail(t.getEmail());
        if (foundedUserByUsername != null || foundedUserByEmail != null) return null;
        else {
            if (t.getPassword() == null || t.getPassword().isEmpty()) {
                t.setPassword(bCryptPasswordEncoder.encode(t.getUsername()));
            } else {
                t.setPassword(bCryptPasswordEncoder.encode(t.getPassword()));
            }
            t.setAccountNonExpired(true);
            t.setAccountNonLocked(true);
            t.setCredentialsNonExpired(true);
            t.setEnabled(enable);
            t.setPasswordChanged(false);
            t.setCreatedAt(LocalDateTime.now());
            if (t.getModelPermissionUsers() == null)
                t.setModelPermissionUsers(new ArrayList<>());

            t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());
            User saved = new User();
            String roleAsString = t.getRoleUsers().get(0).getRole().getAuthority();
            if (roleAsString.equals(AuthoritiesConstants.ADMIN)) {
                saved = super.create(t);
            }
            else if (roleAsString.equals(AuthoritiesConstants.INFLUENCER)) {
                saved = influenceurService.create(convertUserToInfluenceur(t));
            }
            else if (roleAsString.equals(AuthoritiesConstants.COLLABORATOR)) {
                saved = collaboratorService.create(convertUserToCollaborator(t));
            }

            if (t.getModelPermissionUsers() != null) {
                User finalSaved = saved;
                t.getModelPermissionUsers().forEach(e -> {
                    e.setUser(finalSaved);
                    modelPermissionUserService.create(e);
                });
            }
            if (t.getRoleUsers() != null) {
                User finalSaved = saved;
                t.getRoleUsers().forEach(element -> {
                    if (element.getRole() != null && element.getRole().getId() == null && StringUtil.isNotEmpty(element.getRole().getAuthority())) {
                        element.setRole(roleService.findByAuthority(element.getRole().getAuthority()));
                    }
                    element.setUser(finalSaved);
                    roleUserService.create(element);
                });
            }
            return saved;
        }

    }

    private Collaborator convertUserToCollaborator(User user) {
        Collaborator collaborator = new Collaborator();
        BeanUtils.copyProperties(user, collaborator);
        return collaborator;
    }

    private Influenceur convertUserToInfluenceur(User user) {
        Influenceur influenceur = new Influenceur();
        BeanUtils.copyProperties(user, influenceur);
        return influenceur;
    }

    public User findWithAssociatedLists(Long id) {
        User result = dao.findById(id).orElse(null);
        if (result != null && result.getId() != null) {
            result.setModelPermissionUsers(modelPermissionUserService.findByUserId(id));
            result.setRoleUsers(roleUserService.findByUserId(id));
        }
        return result;
    }

    @Transactional
    public void deleteAssociatedLists(Long id) {
        modelPermissionUserService.deleteByUserId(id);
        roleUserService.deleteByUserId(id);
    }


    public void updateWithAssociatedLists(User user) {
        if (user != null && user.getId() != null) {
            List<List<ModelPermissionUser>> resultModelPermissionUsers = modelPermissionUserService.getToBeSavedAndToBeDeleted(modelPermissionUserService.findByUserId(user.getId()), user.getModelPermissionUsers());
            modelPermissionUserService.delete(resultModelPermissionUsers.get(1));
            ListUtil.emptyIfNull(resultModelPermissionUsers.get(0)).forEach(e -> e.setUser(user));
            modelPermissionUserService.update(resultModelPermissionUsers.get(0), true);
            List<List<RoleUser>> resultRoleUsers = roleUserService.getToBeSavedAndToBeDeleted(roleUserService.findByUserId(user.getId()), user.getRoleUsers());
            roleUserService.delete(resultRoleUsers.get(1));
            ListUtil.emptyIfNull(resultRoleUsers.get(0)).forEach(e -> e.setUser(user));
            roleUserService.update(resultRoleUsers.get(0), true);
        }
    }


    public User findByReferenceEntity(User t) {
        return dao.findByEmail(t.getEmail());
    }

    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }


    public User findByLinkValidationCode(String linkValidationCode) {
        return dao.findByLinkValidationCode(linkValidationCode);
    }

    @Override
    public User findByUsername(String username) {
        if (username == null)
            return null;
        return dao.findByUsername(username);
    }

    public List<User> findAllOptimized() {
        return dao.findAllOptimized();
    }

    public String generateCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    @Override
    public String cryptPassword(String value) {
        return value == null ? null : bCryptPasswordEncoder.encode(value);
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        User user = dao.findByUsername(username);
        if (user != null) {
            user.setPassword(cryptPassword(newPassword));
            user.setPasswordChanged(true);
            dao.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User findByUsernameWithRoles(String username) {
        if (username == null)
            return null;
        return dao.findByUsername(username);
    }

    @Override
    @Transactional
    public int deleteByUsername(String username) {
        return dao.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsernameWithRoles(username);
    }

    public void configure() {
        super.configure(User.class, UserSpecification.class);
    }




    private static final int MAX_ACTIVATION_REQUESTS = 3;
    private static final int REQUEST_DELAY_HOURS = 24;

    @Autowired
    private UserDao userDao;
    @Autowired
    EmailService emailService;


    @Transactional
    public void resendActivationCode(String username) {
        System.out.println("tesssssssts user service");
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        LocalDateTime now = LocalDateTime.now();
        if (user.getActivationRequestCount() >= MAX_ACTIVATION_REQUESTS) {
            LocalDateTime nextAllowedRequestTime = user.getLastActivationRequest().plusHours(REQUEST_DELAY_HOURS);
            if (nextAllowedRequestTime.isAfter(now)) {
                Duration duration = Duration.between(now, nextAllowedRequestTime);
                long seconds = duration.getSeconds();
                throw new MaxActivationRequestsReachedException("Max activation requests reached. Please try again later.", seconds);
            } else {
                user.setActivationRequestCount(0);  // Reset count after delay period
            }
        }

        // Generate new activation code
        String activationCode = generateCode(8);
        System.out.println("Sending activation code: " + activationCode);

        // Update user with new activation code and request count
        user.setActivationRequestCount(user.getActivationRequestCount() + 1);
        user.setLastActivationRequest(now);
        user.setLinkValidationCode(activationCode);
        userDao.save(user);

        // Send email with new activation code
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setFrom("votre email");
        emailRequest.setBcc(user.getEmail());
        emailRequest.setCc(user.getEmail());
        emailRequest.setTo(user.getEmail());
        emailRequest.setSubject("Resend Activation Code");
        emailRequest.setBody("Your new activation code is " + activationCode);
        emailService.sendSimpleMessage(emailRequest);
    }




    // logic to send the activation code to manage the password reset

    private static final int MAX_FORGET_PASSWORD_REQUESTS = 3;
    private static final long REQUEST_DELAY_HOURS_FORGET_PASSWORD = 24;

    public void sendForgetPasswordCode(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastActivationRequest = user.getLastActivationRequestForgetPassword();

        if (lastActivationRequest == null) {
            lastActivationRequest = LocalDateTime.MIN;
        }

        if (user.getActivationRequestCountForgetPassword() >= MAX_FORGET_PASSWORD_REQUESTS) {
            LocalDateTime nextAllowedRequestTime = lastActivationRequest.plusHours(REQUEST_DELAY_HOURS_FORGET_PASSWORD);
            if (nextAllowedRequestTime.isAfter(now)) {
                Duration duration = Duration.between(now, nextAllowedRequestTime);
                long seconds = duration.getSeconds();
                throw new MaxActivationRequestsReachedException("Max forget password requests send code reached. Please try again later.", seconds);
            } else {
                user.setActivationRequestCountForgetPassword(0);  // Reset count after delay period
            }
        }

        // Generate new activation code
        String activationCode = generateCode(8);

        // Update user with new activation code and request count
        user.setActivationRequestCountForgetPassword(user.getActivationRequestCountForgetPassword() + 1);
        user.setLastActivationRequestForgetPassword(now);
        user.setLinkValidationCodeForgetPassword(activationCode);
        userDao.save(user);

        // Send email with new activation code
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setFrom("votre email");
        emailRequest.setTo(user.getEmail());
        emailRequest.setBcc(user.getEmail());
        emailRequest.setCc(user.getEmail());
        emailRequest.setSubject("Forget Password Activation Code");
        emailRequest.setBody("Your activation code for resetting your password is " + activationCode);
        emailService.sendSimpleMessage(emailRequest);
    }


    // Service pour réinitialiser le mot de passe

    private static final int MAX_CHANGE_PASSWORD = 4;
    private static final int REQUEST_DELAY_HOURS_CHANGE_PASSWORD =24;

    public void resetPassword(String email, String activationCode, String newPassword) {
        User user = userDao.findByEmail(email);
        System.out.println("1111111");
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("User found: " + user.getEmail());

        if (!activationCode.equals(user.getLinkValidationCodeForgetPassword())) {
            System.out.println("Invalid activation code");
            throw new RuntimeException("Invalid activation code");
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current time: " + now);
        System.out.println("User countForgetPassword: " + user.getCountForgetPassword());

        if (user.getCountForgetPassword() >= MAX_CHANGE_PASSWORD) {
            System.out.println("222222");
            LocalDateTime lastUpdateForgetPassword = user.getLastUpdateForgetPassword();
            if (lastUpdateForgetPassword == null) {
                // Handle the case where lastUpdateForgetPassword is null
                lastUpdateForgetPassword = LocalDateTime.MIN;
            }

            System.out.println("Last update forget password: " + lastUpdateForgetPassword);
            LocalDateTime nextAllowedRequestTime = lastUpdateForgetPassword.plusHours(REQUEST_DELAY_HOURS_CHANGE_PASSWORD);
            System.out.println("Next allowed request time: " + nextAllowedRequestTime);

            if (nextAllowedRequestTime.isAfter(now)) {
                System.out.println("3333333");
                Duration duration = Duration.between(now, nextAllowedRequestTime);
                long seconds = duration.getSeconds();
                System.out.println("44444");
                System.out.println("Max password reset requests reached. Please try again later. Retry after seconds: " + seconds);
                throw new MaxActivationRequestsReachedException("Max password reset requests reached. Please try again later.", seconds);
            } else {
                System.out.println("Resetting forget password count");
                user.setCountForgetPassword(0);  // Reset count after delay period
            }
        }

        System.out.println("55555");
        // Hash the new password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(newPassword);
        System.out.println("Hashed new password: " + hashedPassword);

        // Update password
        user.setPassword(hashedPassword);  // Store the hashed password

        user.setCountForgetPassword(user.getCountForgetPassword() + 1);
        user.setLastUpdateForgetPassword(now);
        userDao.save(user);
        System.out.println("Password reset successfully");
    }





    public class MaxActivationRequestsReachedException extends RuntimeException {
        private long retryAfterSeconds;

        public MaxActivationRequestsReachedException(String message, long retryAfterSeconds) {
            super(message);
            this.retryAfterSeconds = retryAfterSeconds;
        }

        public long getRetryAfterSeconds() {
            return retryAfterSeconds;
        }

        @Override
        public String getMessage() {
            return super.getMessage() + " Retry after seconds: " + retryAfterSeconds;
        }
    }
    @Autowired
    private RoleUserService roleUserService;
    @Autowired
    private ModelPermissionService modelPermissionService;
    @Autowired
    private ActionPermissionService actionPermissionService;
    @Autowired
    private ModelPermissionUserService modelPermissionUserService;
    @Autowired
    private RoleService roleService;
    @Lazy
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserDao dao) {
        super(dao);
    }

}
