package pt.psoft.g1.psoftg1.auth.api.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public String getUserEmail(OAuth2AuthenticationToken authentication) {
        return authentication.getPrincipal().getAttributes().get("email").toString();
    }

}