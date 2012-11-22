package uk.org.alienscience.alien_pizza.base;

import kilim.http.HttpRequest;


/**
 * TODO: document
 */
public class HttpServletRequest {
    private final HttpRequest request;

    public HttpServletRequest(HttpRequest request) {
        this.request = request;
    }

    public String getMethod() {
        return request.method;
    }

}
