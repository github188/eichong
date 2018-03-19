package com.wanma.ims.session.cookie;

import org.springframework.util.Assert;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.*;

@SuppressWarnings("deprecation")
public class CookieSession implements HttpSession {

    private final long creationTime = System.currentTimeMillis();
    private final ServletContext servletContext;
    private final Map<String, Object> attributes = new LinkedHashMap<>();
    private int maxInactiveInterval;
    private long lastAccessedTime = System.currentTimeMillis();
    private boolean invalid = false;

    private boolean isNew = true;

    public CookieSession() {
        this(null);
    }

    public CookieSession(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public String getId() {
        return "" + System.identityHashCode(this);
    }

    public void access() {
        this.lastAccessedTime = System.currentTimeMillis();
        this.isNew = false;
    }

    public long getLastAccessedTime() {
        return this.lastAccessedTime;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }

    public int getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;
    }

    public HttpSessionContext getSessionContext() {
        throw new UnsupportedOperationException("getSessionContext");
    }

    public Object getAttribute(String name) {
        Assert.notNull(name, "Attribute name must not be null");
        return this.attributes.get(name);
    }

    public Object getValue(String name) {
        return getAttribute(name);
    }

    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(new LinkedHashSet<>(this.attributes.keySet()));
    }

    public String[] getValueNames() {
        return this.attributes.keySet().toArray(new String[this.attributes.size()]);
    }

    public void setAttribute(String name, Object value) {
        Assert.notNull(name, "Attribute name must not be null");
        if (value != null) {
            this.attributes.put(name, value);
        } else {
            removeAttribute(name);
        }
    }

    public void putValue(String name, Object value) {
        setAttribute(name, value);
    }

    public void removeAttribute(String name) {
        Assert.notNull(name, "Attribute name must not be null");
        attributes.remove(name);
    }

    public void removeValue(String name) {
        removeAttribute(name);
    }

    public void clearAttributes() {
        attributes.clear();
    }

    public void invalidate() {
        if (this.invalid) {
            throw new IllegalStateException("The session has already been invalidated");
        }
        this.invalid = true;
        clearAttributes();
    }

    public boolean isInvalid() {
        return this.invalid;
    }

    public boolean isNew() {
        return this.isNew;
    }

    public void setNew(boolean value) {
        this.isNew = value;
    }
}
