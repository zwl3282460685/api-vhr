package com.zwl.vhrapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwl.vhrapi.model.RespBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证码过滤器
 * @author zwl
 * @data 2020/12/27 10:12
 **/
@Component
public class VerificationCodeFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if("POST".equals(req.getMethod()) && "/doLogin".equals(req.getServletPath())){
            //登录请求
            String code = req.getParameter("code");
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            if(code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())){
                //验证码不正确
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(RespBean.error("验证码不正确！")));
                out.flush();
                out.close();
                return;
            }else{
                filterChain.doFilter(req, resp);
            }
        }else{
            filterChain.doFilter(req, resp);
        }
    }
}
