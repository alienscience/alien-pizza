package uk.org.alienscience.alien_pizza;

import kilim.Pausable;
import kilim.http.HttpRequest;
import kilim.http.HttpResponse;

import java.io.EOFException;
import java.io.IOException;

/**
 * TODO: Document
 */
public class HttpSession extends kilim.http.HttpSession {


    public HttpSession() {
        super();
    }

    @Override
    public void execute() throws Pausable {
        try {
            // We will reuse the req and resp objects
            HttpRequest req = new HttpRequest();
            HttpResponse resp = new HttpResponse();

            while (true) {
                // Read the request
                super.readRequest(req);

                // Handle the request
                Route route = Container.INSTANCE.getRoute(req.uriPath);
                Response response  = new Response(resp);

                if (route == null) {
                    super.problem(resp, HttpResponse.ST_NOT_FOUND, "Not Found");
                    continue;
                }

                Request request = new Request(req);
                route.handle(request, response);
                response.flush();
                sendResponse(resp);

                // Keep the connection alive?
                if (!req.keepAlive()) break;
            }
        } catch (EOFException e) {
            // TODO : convert to slf4j
            //System.out.println("[" + this.id + "] Connection Terminated");
        } catch (IOException ioe) {
            System.out.println("[" + this.id + "] IO Exception:" + ioe.getMessage());
        }
        super.close();
    }
}
