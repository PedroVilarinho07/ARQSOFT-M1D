package pt.psoft.g1.psoftg1.auth.api.infrastructure;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuth {

    public String getUserInfo(OAuth2AuthenticationToken authentication) {
        return authentication.getPrincipal().getAttributes().get("email").toString();
    }

}
