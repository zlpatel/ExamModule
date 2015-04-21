package org.exammodule.handler;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	protected static Logger logger = Logger.getLogger("service");
 
	@Autowired
    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
	
	public static final String USERNAME_KEY = "USERNAME";
	public static final String ACCESS_LEVEL_KEY = "ACCESS_LEVEL";
	public static final String QUESTION_ORDER_KEY = "QUESTION_ORDER";
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
        String usernameParameter =
                usernamePasswordAuthenticationFilter.getUsernameParameter();
            String userName = request.getParameter(usernameParameter);

            HttpSession session = request.getSession(false);
            if (session != null) {
                request.getSession().setAttribute(USERNAME_KEY, userName);
            }
    }
 
    protected void handle(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication) throws IOException {
        
    	HttpSession session = request.getSession(false);
    	String targetUrl = determineTargetUrl(session,authentication);
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(HttpSession session, Authentication authentication) {
    	boolean isAdmin = false;
        boolean isUser = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                if (session != null) {
                   session.setAttribute(ACCESS_LEVEL_KEY,"ROLE_ADMIN");
                }
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_USER_VIDEO")){ 
                isUser = true;
                if (session != null) {
                    session.setAttribute(ACCESS_LEVEL_KEY,"ROLE_USER_VIDEO");
                    session.setAttribute(QUESTION_ORDER_KEY,1);
                 }
                break;
            }else if(grantedAuthority.getAuthority().equals("ROLE_USER_IMAGE")){
            	isUser = true;
            	if (session != null) {
                    session.setAttribute(ACCESS_LEVEL_KEY,"ROLE_USER_IMAGE");
                    session.setAttribute(QUESTION_ORDER_KEY,1);
                 }
                break;
            }else if(grantedAuthority.getAuthority().equals("ROLE_USER_NOTHING")){
            	isUser = true;
            	if (session != null) {
                    session.setAttribute(ACCESS_LEVEL_KEY,"ROLE_USER_NOTHING");
                    session.setAttribute(QUESTION_ORDER_KEY,1);
                 }
                break;
            }
        }
 
        if (isAdmin) {
            return "/secure/admin/home";
        } else if (isUser) {
            return "/secure/user/home";
        } else {
            throw new IllegalStateException();
        }
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}