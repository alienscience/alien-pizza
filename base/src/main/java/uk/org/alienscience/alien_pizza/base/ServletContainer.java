package uk.org.alienscience.alien_pizza.base;

/**
 * Singleton that keeps track of servlets
 */
public enum ServletContainer {
    INSTANCE;

    // TODO turn into datastructure that is searchable by path
    private HttpServlet servlet;

    public HttpServlet getServlet(String path) {
        assert(servlet != null);
        return servlet;
    }

    public void setServlet(String path, HttpServlet servlet) {
        this.servlet = servlet;
    }
}
