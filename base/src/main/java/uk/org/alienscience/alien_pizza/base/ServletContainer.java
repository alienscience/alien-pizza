package uk.org.alienscience.alien_pizza.base;

import java.io.File;
import java.io.IOException;

/**
 * Singleton that keeps track of servlets
 */
public enum ServletContainer {
    INSTANCE;

    // TODO turn into datastructure that is searchable by path
    private HttpServlet servlet;

    /**
     * Start the servlet container as an application
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // Print the working directory
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        // Weave and load the servlet classes in the example directory
        ClassLoader loader = null;
        try {
            loader = RuntimeWeaver.load("uk.org.alienscience.alien_pizza.examples",
                    new File("examples/target/classes/uk/org/alienscience/alien_pizza/examples/"));
        } catch (IOException e) {
            System.err.println("Weaving failed: " + e.getMessage());
        }

        // Add our servlet manually
        Class<HttpServlet> servletClass = (Class<HttpServlet>) loader.loadClass("HelloWorldServlet");
        ServletContainer.INSTANCE.setServlet("hello", servletClass.newInstance());

        // Start the webserver
        if ( new HttpServer(7262).start() ) {
            System.out.println("ServletContainer listening on http://localhost:7262");
        }
    }


    // TODO remove
    public HttpServlet getServlet(String path) {
        assert(servlet != null);
        return servlet;
    }

    // TODO remove
    public void setServlet(String path, HttpServlet servlet) {
        this.servlet = servlet;
    }
}
