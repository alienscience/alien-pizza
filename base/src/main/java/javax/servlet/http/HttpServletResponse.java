package javax.servlet.http;

import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * TODO: document
 */
public class HttpServletResponse extends ServletResponse {
    /**
     * Status code (405) indicating that the method specified in the Request-Line is not allowed for the
     * resource identified by the Request-URI.
     */
    public static final int SC_METHOD_NOT_ALLOWED = 405;

    /**
     *  Sends an error response to the client using the specified status code and clears the buffer.
     *  The server will preserve cookies and may clear or update any headers needed to serve the error page as
     *  a valid response. If an error-page declaration has been made for the web application corresponding to the
     *  status code passed in, it will be served back the error page.
     *  If the response has already been committed, this method throws an IllegalStateException.
     *  After using this method, the response should be considered to be committed and should not be written to.
     *
     * @param sc the error status code
     */
    public void sendError(int sc) throws IOException, IllegalStateException {
    }
}
