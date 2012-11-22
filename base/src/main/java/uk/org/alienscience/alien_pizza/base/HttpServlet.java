package uk.org.alienscience.alien_pizza.base;

import kilim.Pausable;

import java.io.IOException;

/**
 * TODO: ServletContext
 */
public class HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, Pausable, ServletException {
        if (req.getMethod().equals("GET")) {
            this.doGet(req,res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, Pausable, ServletException {
        res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
