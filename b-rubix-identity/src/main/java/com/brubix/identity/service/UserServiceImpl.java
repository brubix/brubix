package com.brubix.identity.service;

import com.brubix.entity.identity.User;
import com.brubix.entity.inventory.School;
import com.brubix.identity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public BrubixUserDetails getUserDetailsByIdentifier(String identifier) {
        User user = userRepository.findByEmailId(identifier);
        if (user == null) {
            user = userRepository.findByPhoneNumber(identifier);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
        }
        List<BrubixUserDetails.UserRole> userRoles = user.getRoles()
                .stream()
                .map(role -> {
                    BrubixUserDetails.UserRole userRole = new BrubixUserDetails.UserRole();
                    userRole.setName(role.getName());
                    userRole.setDescription(role.getDescription());
                    userRole.setPrivileges(role.getPrivileges()
                            .stream()
                            .map(privilege -> {
                                BrubixUserDetails.RolePrivilege rolePrivilege = new BrubixUserDetails.RolePrivilege();
                                rolePrivilege.setName(privilege.getName());
                                rolePrivilege.setDescription(privilege.getDescription());
                                return rolePrivilege;
                            }).collect(Collectors.toList()));
                    return userRole;
                }).collect(Collectors.toList());

        School school = user.getSchool();
        BrubixUserDetails.AssociatedSchool associatedSchool = new BrubixUserDetails.AssociatedSchool();
        associatedSchool.setCode(school.getSchoolCode());
        associatedSchool.setName(school.getSchoolName());

        BrubixUserDetails brubixUserDetails = new BrubixUserDetails(user.getName(), user.getPassword(), user.isEnabled());
        brubixUserDetails.setRoles(userRoles);
        brubixUserDetails.setSchool(associatedSchool);
        return brubixUserDetails;
    }
}
