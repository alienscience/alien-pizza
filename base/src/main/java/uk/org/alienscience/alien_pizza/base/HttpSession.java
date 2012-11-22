package uk.org.alienscience.alien_pizza.base;

import kilim.Pausable;
import kilim.http.HttpRequest;
import kilim.http.HttpResponse;

import java.io.EOFException;
import java.io.IOException;

/**
 * kilm.http.HttpSession is an HTTPSession task, itself a thin wrapper over the socket connection. An instance of this
 * task is launched for each new connection, and its execute method is called when the task is scheduled.
 * <p/>
 * The HttpRequest and HttpResponse objects are wrappers over a bytebuffer,
 * and unrelated to the socket. The request object is "filled in" by HttpSession.readRequest() and the response object
 * is sent by HttpSession.sendResponse().
 */

public class HttpSession extends kilim.http.HttpSession {

    public HttpSession() {
        super();
    }

    @Override
    public void execute() throws Pausable, Exception {
        try {
            // We will reuse the req and resp objects
            HttpRequest req = new HttpRequest();
            HttpResponse resp = new HttpResponse();
            HttpServletRequest servletRequest = new HttpServletRequest(req);
            HttpServletResponse servletResponse = new HttpServletResponse(resp, this);

            while (true) {
                // Read the request
                super.readRequest(req);
                System.out.println("Received: " + req);

                // Get the servlet that will handle the request
                HttpServlet servlet = ServletContainer.INSTANCE.getServlet(req.uriPath);

                // Call the servlet
                servlet.service(servletRequest, servletResponse);

                // Send the response
                if (!servletResponse.hasSent()) servletResponse.sendResponse();
                servletResponse.reuse();

                // Keep the connection alive?
                if (!req.keepAlive()) break;
            }
        } catch (EOFException e) {
            System.out.println("[" + this.id + "] Connection Terminated");
        } catch (IOException ioe) {
            System.out.println("[" + this.id + "] IO Exception:" + ioe.getMessage());
        }
        super.close();
    }
}
