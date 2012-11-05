import javax.swing.text.html.HTML;
import javax.xml.ws.Response;
import java.lang.Object;
import java.lang.String;

/**
 * An example of the Arc Challenge that uses anonymous classes in a similar way to Spark
 */
@Path("said")
public class ArcChallenge {

    public void main (String[] args) {
        get(new Route("/said") {
            public Object handle(Request request, Response response) {
                return form(
                        input.name("answer"),
                            input.submit(new Callback() {
                                public Object handle(Request request, Response response) {
                                return a("click here")
                                        .href(new Callback() {
                                            public Object handle(Request req, Response res) {
                                                return p("you said", request.param("answer"));
                                            }
                                        })
                            }
                        })
                )
            }
        })
    }
}

