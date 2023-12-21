package com.example.nserver.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.nserver.security.UserPermisson.*;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum UserRole {
    ADMIN(Sets.newHashSet(USER_READ,USER_WRITE,QUERY_PRODUCT_READ,QUERY_PRODUCT_READ,COMMAND_PRODUCT_ADD,COMMAND_PRODUCT_READ,COMMAND_PRODUCT_REMOVE,COMMAND_PRODUCT_UPDATE)),
    USER(Sets.newHashSet(QUERY_PRODUCT_READ,COMMAND_PRODUCT_READ,COMMAND_PRODUCT_UPDATE));


    //    CLIENT(Sets.newHashSet(PRODUCT_READ, ORDER_READ, ORDERDETAILS_READ));
    private final Set<UserPermisson> permission;

    public Set<UserPermisson> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions= getPermission().stream().map(permission->new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("Role_"+this.name()));
        return permissions;
    }

}
