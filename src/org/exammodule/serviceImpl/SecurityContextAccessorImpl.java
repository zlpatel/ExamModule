package org.exammodule.serviceImpl;

import java.util.Collection;

import org.exammodule.service.SecurityContextAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public final class SecurityContextAccessorImpl
implements SecurityContextAccessor {

@Autowired
private AuthenticationTrustResolver authenticationTrustResolver;

@Override
public boolean isCurrentAuthenticationAnonymous() {
final Authentication authentication =
  SecurityContextHolder.getContext().getAuthentication();
return authenticationTrustResolver.isAnonymous(authentication);
}

@Override
public String determineDefaultTargetUrl() {
	final Authentication authentication =
			  SecurityContextHolder.getContext().getAuthentication();
	boolean isAdmin = false;
	boolean isUser = false;
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (GrantedAuthority grantedAuthority : authorities) {
        if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
            isAdmin = true;
            break;
        } else if (grantedAuthority.getAuthority().equals("ROLE_USER_VIDEO") || grantedAuthority.getAuthority().equals("ROLE_USER_IMAGE") || grantedAuthority.getAuthority().equals("ROLE_USER_NOTHING")) {
            isUser = true;
            break;
        }
    }

    if (isAdmin) {
        return "redirect:../admin/home";
    } else if (isUser) {
        return "redirect:../user/home";
    }else {
        throw new IllegalStateException();
    }
}
}