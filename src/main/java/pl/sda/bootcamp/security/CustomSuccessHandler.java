package pl.sda.bootcamp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        String redirectUrl = null;

        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();
        label:
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "ROLE_USER":
                    redirectUrl = "/panel-klienta";
                    break label;
                case "ROLE_TEACHER":
                    redirectUrl = "/panel-trenera";
                    break label;
                case "ROLE_ADMIN":
                    redirectUrl = "/admin";
                    break label;
            }
        }

        if (redirectUrl == null) {
            throw new IllegalStateException();
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
