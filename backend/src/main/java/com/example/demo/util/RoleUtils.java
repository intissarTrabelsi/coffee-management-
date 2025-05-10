package com.example.demo.util;

import com.example.demo.enums.Role;

public class RoleUtils {
    
    public static boolean hasRequiredRole(Role userRole, Role requiredRole) {
        return userRole != null && userRole.equals(requiredRole);
    }

    public static boolean isClient(Role role) {
        return Role.CLIENT.equals(role);
    }

    public static boolean isBarista(Role role) {
        return Role.BARISTA.equals(role);
    }

    public static String getRoleDisplayName(Role role) {
        if (role == null) {
            return "Unknown";
        }
        return role.getValue();
    }
} 