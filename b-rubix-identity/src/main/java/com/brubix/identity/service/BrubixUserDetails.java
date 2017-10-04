package com.brubix.identity.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BrubixUserDetails implements UserDetails {

    private boolean isEnabled;
    private String username;
    private List<UserRole> roles;

    public BrubixUserDetails(String username, boolean isEnabled, List<UserRole> roles) {
        this.username = username;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public List<UserRole> getRoles() {
        return roles;
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
}
