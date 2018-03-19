package com.wanma.ims.session;

import com.wanma.ims.session.cookie.CookieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class SessionRequestWrapper extends HttpServletRequestWrapper {

    HttpSession session;

    public SessionRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (session == null && create) {
            session = new CookieSession();
        }
        return session;
    }
}
