package uk.org.alienscience.alien_pizza.base;

import javax.servlet.http.HttpServlet;

/**
 * Singleton that keeps track of servlets
 */
public enum ServletContainer {
    INSTANCE;

    // TODO turn into datastructure that is searchable by path
    private HttpServlet servlet;

    public HttpServlet getServlet(String path) {
        return servlet;
    }

    public void setServlet(String path, HttpServlet servlet) {
        this.servlet = servlet;
    }
}
