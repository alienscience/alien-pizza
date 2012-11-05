package javax.servlet.http;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * TODO: ServletContext, Serializable
 */
public class HttpServlet extends GenericServlet implements Servlet {

    /**
     * Dispatches client requests to the protected service method.
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client made of the servlet
     * @param res  the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     */
    public void service(ServletRequest req, ServletResponse res) {
        service((HttpServletRequest) req, (HttpServletResponse) res);
    }


    protected void service(HttpServletRequest req, HttpServletResponse res) {
    }


    protected void doGet(HttpServletRequest req,HttpServletResponse res)  throws IOException {
        res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
