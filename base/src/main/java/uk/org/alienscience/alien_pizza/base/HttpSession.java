package uk.org.alienscience.alien_pizza.base;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;

import kilim.Pausable;
import kilim.http.HttpRequest;
import kilim.http.HttpResponse;
import kilim.http.KeyValues;

/**
 * kilm.http.HttpSession is an HTTPSession task, itself a thin wrapper over the socket connection. An instance of this
 * task is launched for each new connection, and its execute method is called when the task is scheduled.
 * <p/>
 * The HttpRequest and HttpResponse objects are wrappers over a bytebuffer,
 * and unrelated to the socket. The request object is "filled in" by HttpSession.readRequest() and the response object
 * is sent by HttpSession.sendResponse().
 */

public class HttpSession extends kilim.http.HttpSession {

    @Override
    public void execute() throws Pausable, Exception {
        try {
            // We will reuse the req and resp objects
            HttpRequest req = new HttpRequest();
            HttpResponse resp = new HttpResponse();
            while (true) {
                super.readRequest(req);
                System.out.println("Received: " + req);
                if (req.method.equals("GET") || req.method.equals("HEAD")) {
                    resp.setContentType("text/html");
                    PrintWriter pw = new PrintWriter(resp.getOutputStream());
                    // Note that resp.getOutputStream() does not write to the socket; it merely buffers the entire
                    // output.
                    pw.append("<html><body>path = ");
                    pw.append(req.uriPath).append("<p>");
                    KeyValues kvs = req.getQueryComponents();
                    for (int i = 0; i < kvs.count; i++) {
                        pw.append(kvs.keys[i]).append(" = ").append(kvs.values[i]).append("<br>");
                    }
                    pw.append("</body></html>");
                    pw.flush();
                    sendResponse(resp);
                } else {
                    super.problem(resp, HttpResponse.ST_FORBIDDEN, "Only GET and HEAD accepted");
                }

                if (!req.keepAlive())
                    break;
                break;
            }
        } catch (EOFException e) {
            System.out.println("[" + this.id + "] Connection Terminated");
        } catch (IOException ioe) {
            System.out.println("[" + this.id + "] IO Exception:" + ioe.getMessage());
        }
        super.close();
    }
}
