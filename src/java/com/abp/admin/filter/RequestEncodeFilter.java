/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter can be used to set the content type as UTF-8.
 *
 * @author SANTHOSH REDDY MANDADI
 * @version 1.0
 * @since 20th April 2008
 */
public class RequestEncodeFilter implements Filter {
    //FilterConfig object

    private FilterConfig filterConfig = null;

    //Default constructor
    public RequestEncodeFilter() {
        System.out.println("Request response encoder Filter object has been created");
    }

    //Intitialization method
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest hrequest, ServletResponse hresponse, FilterChain chain) throws IOException, ServletException {
        //Setting the character set for the request
        HttpServletRequest request = (HttpServletRequest) hrequest;
        HttpServletResponse response = (HttpServletResponse) hresponse;

        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");

        chain.doFilter(request, response);

        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        //Setting the character set for the response
    }

    public void destroy() {
        this.filterConfig = null;
    }
}
