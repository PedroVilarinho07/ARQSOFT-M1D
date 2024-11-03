package pt.psoft.g1.psoftg1.auth.services;

import org.springframework.web.servlet.view.RedirectView;

public interface AuthService {
    RedirectView authenticate();
}

