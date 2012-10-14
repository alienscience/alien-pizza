package uk.org.alienscience.alien_pizza.examples;

/**
 * A JaxRS type example of the arc challenge
 * The SessionLifetime interface keeps the object alive for the lifetime of a session
 */
@Path("said")
public class ArcChallenge implements SessionLifetime {
    private String what;

    ArcChallenge(){
        what = "nothing";
    }

    public void endSession() {
        // Do nothing
    }

    @GET
    public String inputPage() {
        return html.form().input("answer").submit().over();
    }

    @POST
    public String input(@FormParam("answer") String answer) {
        what = answer;
        return html.a("click here", "show").over();
    }

    @GET
    @Path("show")
    public String show() {
        html.raw("you said:", what).over();
    }
}
