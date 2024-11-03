package pt.psoft.g1.psoftg1.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;
import pt.psoft.g1.psoftg1.auth.adapters.AuthAdapter;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthAdapter authAdapter;

    @Override
    public RedirectView authenticate() {
        return authAdapter.authenticate();
    }
}
