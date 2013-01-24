package uk.org.alienscience.alien_pizza;

import kilim.http.HttpResponse;

import java.io.PrintWriter;

/**
 * TODO: Document
 */
public class Response {

    private final HttpResponse kilimResponse;
    private PrintWriter writer;

    public Response(HttpResponse resp) {
        this.kilimResponse = resp;
    }

    /**
     * Append the given string to the response body
     * @param s The string to append
     */
    public void write(String s) {
        getPrintWriter().append(s);
    }

    /**
     * Get a print writer that appends the response body
     * @return A print writer
     */
    public PrintWriter getPrintWriter() {
        if (writer == null) {
            writer = new PrintWriter(kilimResponse.getOutputStream());
        }
        return writer;
    }

    /**
     * Flush any output
     */
    void flush() {
        getPrintWriter().flush();
    }
}
