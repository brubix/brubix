package com.brubix.common.service;

import com.brubix.common.repository.UserRepository;
import com.brubix.entity.identity.User;
import com.brubix.entity.inventory.Institution;
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

        Institution institution = user.getInstitution();
        BrubixUserDetails.AssociatedInstitution associatedInstitution = new BrubixUserDetails.AssociatedInstitution();
        associatedInstitution.setCode(institution.getInstitutionCode());
        associatedInstitution.setName(institution.getInstitutionName());

        BrubixUserDetails brubixUserDetails = new BrubixUserDetails(user.getFirstName(), user.getPassword(), user.isEnabled());
        brubixUserDetails.setRoles(userRoles);
        brubixUserDetails.setInstitution(associatedInstitution);
        return brubixUserDetails;
    }
}
