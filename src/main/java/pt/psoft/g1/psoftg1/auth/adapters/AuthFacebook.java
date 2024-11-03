package pt.psoft.g1.psoftg1.auth.adapters;


import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

@Component
@Profile("facebook")
public class AuthFacebook implements AuthAdapter {

    @Override
    public RedirectView authenticate() {
        return new RedirectView(OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/facebook");
    }
}
