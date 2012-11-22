package uk.org.alienscience.alien_pizza.examples;

import kilim.Pausable;
import uk.org.alienscience.alien_pizza.base.HttpServlet;
import uk.org.alienscience.alien_pizza.base.HttpServletRequest;
import uk.org.alienscience.alien_pizza.base.HttpServletResponse;
import uk.org.alienscience.alien_pizza.base.ServletException;
import uk.org.alienscience.alien_pizza.base.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Hello world servlet
 */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

    public HelloWorldServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Pausable {
        response.setContentType("text/html");
        PrintWriter out  = response.getWriter();
        out.println("<h1>Hello World!</h1>");
        out.close();
    }

}
