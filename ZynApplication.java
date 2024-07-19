package ma.zs.zyn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import ma.zs.zyn.service.facade.admin.project.ProjectTechnologyProfileAdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.zynerator.security.bean.*;
import ma.zs.zyn.zynerator.security.common.AuthoritiesConstants;
import ma.zs.zyn.zynerator.security.service.facade.*;

import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import ma.zs.zyn.service.facade.admin.project.ProjectTechnologyTypeAdminService;

import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class ZynApplication {
    public static ConfigurableApplicationContext ctx;
    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(ZynApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService ) {
    return (args) -> {
        if(true){

            createProjectTechnologyType();

        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Collaborator
        User userForCollaborator = new User("collaborator");
		userForCollaborator.setPassword("123");
		// Role Collaborator
		Role roleForCollaborator = new Role();
		roleForCollaborator.setAuthority(AuthoritiesConstants.COLLABORATOR);
		roleForCollaborator.setCreatedAt(LocalDateTime.now());
		Role roleForCollaboratorSaved = roleService.create(roleForCollaborator);
		RoleUser roleUserForCollaborator = new RoleUser();
		roleUserForCollaborator.setRole(roleForCollaboratorSaved);
		if (userForCollaborator.getRoleUsers() == null)
			userForCollaborator.setRoleUsers(new ArrayList<>());

		userForCollaborator.getRoleUsers().add(roleUserForCollaborator);
		if (userForCollaborator.getModelPermissionUsers() == null)
			userForCollaborator.setModelPermissionUsers(new ArrayList<>());


        userForCollaborator.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForCollaborator);

            }
        };
    }


    private void createProjectTechnologyType(){
            ProjectTechnologyType itemSuccess = new ProjectTechnologyType();
            itemSuccess.setCode("success");
            itemSuccess.setLibelle("Front");
            projectTechnologyTypeService.create(itemSuccess);
            ProjectTechnologyType itemWarning = new ProjectTechnologyType();
            itemWarning.setCode("warning");
            itemWarning.setLibelle("Back");
            projectTechnologyTypeService.create(itemWarning);
            ProjectTechnologyType itemDanger = new ProjectTechnologyType();
            itemDanger.setCode("danger");
            itemDanger.setLibelle("DB");
            projectTechnologyTypeService.create(itemDanger);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("ProjectTechnologyType"));
        modelPermissions.add(new ModelPermission("ProjectTechnology"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    ProjectTechnologyTypeAdminService projectTechnologyTypeService;
}


