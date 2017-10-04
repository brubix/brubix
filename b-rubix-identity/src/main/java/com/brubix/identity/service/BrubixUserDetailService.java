package com.brubix.identity.service;

import com.brubix.entity.identity.User;
import com.brubix.identity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BrubixUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public BrubixUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {

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

        return new BrubixUserDetails(user.getName(), user.isEnabled(), userRoles);
    }
}
