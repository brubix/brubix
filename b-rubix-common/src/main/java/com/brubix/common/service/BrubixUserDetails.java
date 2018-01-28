package com.brubix.common.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class BrubixUserDetails implements UserDetails {

    private boolean isEnabled;
    private String username;
    private String password;
    private AssociatedInstitution institution;
    private List<UserRole> roles;

    public BrubixUserDetails(String username, String password, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void setInstitution(AssociatedInstitution institution) {
        this.institution = institution;
    }

    public AssociatedInstitution getInstitution() {
        return institution;
    }

    @Getter
    @Setter
    public static class UserRole {

        private String name;
        private String description;
        private List<RolePrivilege> privileges;

    }

    @Getter
    @Setter
    public static class RolePrivilege {
        private String name;
        private String description;
    }

    @Getter
    @Setter
    public static class AssociatedInstitution {
        private String name;
        private String code;
    }
}
