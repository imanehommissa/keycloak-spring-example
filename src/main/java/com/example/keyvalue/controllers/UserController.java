package com.example.keyvalue.controllers;

import com.example.keyvalue.services.KeyCloakService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.spi.AuthOutcome;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    KeyCloakService keyCloakService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @RolesAllowed("user")
    public ResponseEntity<?> sayHello(HttpServletRequest request) {


        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            return new ResponseEntity<>("Hi!, you are auhorized to view this response!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }


    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {

        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        String userId = token.getSubject();

        keyCloakService.logoutUser(userId);

        return new ResponseEntity<>("Hi!, you have logged out successfully!", HttpStatus.OK);

    }

    @RequestMapping(value = "/update/password", method = RequestMethod.GET)
    public ResponseEntity<?> updatePassword(HttpServletRequest request, String newPassword) {

        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        String userId = token.getSubject();

        keyCloakService.resetPassword(newPassword, userId);

        return new ResponseEntity<>("Hi!, your password has been successfully updated!", HttpStatus.OK);

    }

}
