package pt.psoft.g1.psoftg1.auth.adapters;

import org.springframework.web.servlet.view.RedirectView;

public interface AuthAdapter {
    RedirectView authenticate();
}
