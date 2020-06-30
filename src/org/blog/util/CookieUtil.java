package org.blog.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void   getCookie(String username, String password, HttpServletRequest request, HttpServletResponse response){
        Cookie c1 = new Cookie("uname",username);
        c1.setMaxAge(60*60*24);
        c1.setPath("/");
        Cookie c2 = new Cookie("upswd",password);
        c2.setMaxAge(60*60*24);
        c2.setPath("/");
        response.addCookie(c1);
        response.addCookie(c2);

    }
    public static void deleteCookie(Cookie[] cookies,HttpServletResponse response ){

        Cookie c1 = new Cookie("uname","");
        c1.setMaxAge(0);
        c1.setPath("/");
        response.addCookie(c1);
        Cookie c2 = new Cookie("upswd","");
        c2.setMaxAge(0);
        c2.setPath("/");
        response.addCookie(c2);

    }
}
