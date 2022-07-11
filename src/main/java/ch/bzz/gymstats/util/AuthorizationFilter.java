package ch.bzz.gymstats.util;

import java.lang.reflect.Method;
import java.util.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * checks the authorization for the services
 * Source: https://howtodoinjava.com/jersey/jersey-rest-security/
 */

@Provider
public class AuthorizationFilter implements javax.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "cookie";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();

        if(method.isAnnotationPresent(DenyAll.class)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Access blocked for all users !!").build());
        } else if (!method.isAnnotationPresent(PermitAll.class) &&
                method.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            Set<String> requiredRoles = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

            String userRole = getToken(requestContext.getHeaders());

            if (userRole == null || !isUserAllowed(requiredRoles, userRole)){
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
            }
        }


    }

    /**
     * checks if the userrole is sufficent to access the service
     * @param requiredRoles  the required roles
     * @param userRole  the role of this user
     * @return  allowed true/false
     */
    private boolean isUserAllowed(final Set<String> requiredRoles, String userRole)
    {
        return (requiredRoles.contains(userRole));
    }

    /**
     * get the authorization token
     * @param headers  the request headers
     * @return token
     */
    private String getToken(MultivaluedMap<String, String> headers) {
        String role = "";
        String roleOutput;

        if(headers.get(AUTHORIZATION_PROPERTY).get(0)==null) {
            role = headers.get(AUTHORIZATION_PROPERTY).get(0);

            if (!role.equals("")) {
                roleOutput = role
                        .substring(0, role.indexOf(";"))
                        .substring(role.indexOf("=") + 1);
            } else {
                roleOutput = "guest";
            }
        }
        else{
            roleOutput="guest";
        }
        return roleOutput;
    }
}