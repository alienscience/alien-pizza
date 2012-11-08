package uk.org.alienscience.alien_pizza.base;

import kilim.Pausable;
import kilim.http.HttpResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Implementation of a {@link javax.servlet.http.HttpServletResponse}
 */
public class HttpServletResponse implements javax.servlet.http.HttpServletResponse {
    private final HttpResponse response;
    private final HttpSession session;
    private boolean sent;

    public HttpServletResponse(HttpResponse response, HttpSession session) {
        this.response = response;
        this.session = session;
        this.sent = false;
    }

    @Override
    public void sendError(int sc) throws IOException, IllegalStateException, Pausable {
        response.setStatus(String.valueOf(sc));
        response.setContentType("text/html");
        sendResponse();
    }

    @Override
    public void sendError(int sc, String msg) throws IOException, IllegalStateException, Pausable {
        response.setStatus(sc + " " + msg);
        response.setContentType("text/html");
        sendResponse();
    }

    @Override
    public void setContentType(String s) {
        response.setContentType("text/html");
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(response.getOutputStream());
    }

    private void sendResponse() throws IOException, Pausable {
        if (sent) {
            response.reuse();
            throw new IllegalStateException("HTTPServletResponse has already been sent");
        }
        session.sendResponse(response);
        sent = true;
    }
}
