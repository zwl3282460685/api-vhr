package com.zwl.vhrapi.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.model.RespBean;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zwl
 * @data 2021/2/9 13:51
 **/
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException,
            IOException, ServletException {
        System.out.println(request.getInputStream());
        //Hr hr = new ObjectMapper().readValue(request.getInputStream(), Hr.class);
        Hr hr = new ObjectMapper().readValue(request.getInputStream(), Hr.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(hr.getUsername(), hr.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); //获取登录用户的角色
        StringBuffer sb = new StringBuffer();
        for(GrantedAuthority authority : authorities){
            sb.append(authority.getAuthority()).append(",");
        }
        String jwt = Jwts.builder()
                .claim("authorities", sb)
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 60 *60 *1000))
                .signWith(SignatureAlgorithm.HS512, "zwl@123")
                .compact();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Hr hr = (Hr) authentication.getPrincipal(); //登录成功的hr对象
        hr.setPassword(null);
        RespBean ok = RespBean.ok("登录成功！", hr, jwt);
        out.write(new ObjectMapper().writeValueAsString(ok));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "登录失败");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
}
