package com.tsin.vueblog.shiro;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * @author tsin
 * @since 2021/9/12-17:13
 */
public class JwtToken implements AuthenticationToken {

    private String token;
    public JwtToken(String jwt) {
        this.token = jwt;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}
