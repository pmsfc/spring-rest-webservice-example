package com.springmvc.app.secure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.GenericFilterBean;


/**
 * Filter bean to verify if user is using a token based auth
 * @author Pedro Caldeira
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        @SuppressWarnings("unchecked")
        Map<String, String[]> params = request.getParameterMap();

        if (params.containsKey("token")) {
            String strToken = params.get("token")[0]; // grab the first "token" parameter
            System.out.println("Token: " + strToken);

            if (strToken.equals("ryw0hZmCJpR4WdLlA4v3")) {
                System.out.println("valid token found");
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken("ryw0hZmCJpR4WdLlA4v3", "ryw0hZmCJpR4WdLlA4v3");

                token.setDetails(new WebAuthenticationDetails((HttpServletRequest) request));
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken("ryw0hZmCJpR4WdLlA4v3", "ryw0hZmCJpR4WdLlA4v3", authorities);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                System.out.println("invalid token");
            }
        } else {
            System.out.println("no token found");
        }
        // continue thru the filter chain
        chain.doFilter(request, response);
    }
}
