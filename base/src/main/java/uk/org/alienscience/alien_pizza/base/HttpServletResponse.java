package uk.org.alienscience.alien_pizza.base;

import kilim.Pausable;
import kilim.http.HttpResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Implementation of a Response from a servlet
 */
public class HttpServletResponse {
    private final HttpResponse response;
    private final HttpSession session;
    private boolean sent;

    /**
     * Status code (405) indicating that the method specified in the Request-Line is not allowed for the
     * resource identified by the Request-URI.
     */
    public static final int SC_METHOD_NOT_ALLOWED = 405;

    public HttpServletResponse(HttpResponse response, HttpSession session) {
        this.response = response;
        this.session = session;
        this.sent = false;
    }

    public void sendError(int sc) throws IOException, IllegalStateException, Pausable {
        response.setStatus(String.valueOf(sc));
        response.setContentType("text/html");
    }

    public void sendError(int sc, String msg) throws IOException, IllegalStateException, Pausable {
        response.setStatus(sc + " " + msg);
        response.setContentType("text/html");
    }

    public void setContentType(String s) {
        response.setContentType("text/html");
    }

    public PrintWriter getWriter() {
        return new PrintWriter(response.getOutputStream());
    }

    boolean hasSent() {
        return sent;
    }

    void sendResponse() throws IOException, IllegalStateException, Pausable {
        if (sent) {
            throw new IllegalStateException("HTTPServletResponse has already been sent");
        }
        session.sendResponse(response);
        sent = true;
    }

    void reuse() {
        sent = false;
        response.reuse();
    }
}
