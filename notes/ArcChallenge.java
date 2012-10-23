package uk.org.alienscience.alien_pizza.examples;

/**
 * An example of the arc challenge that uses annotations
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
	return form(input.name("answer").submit()).toString();
    }

    @POST
    public String input(@FormParam("answer") String answer) {
        what = answer;
	return a("click here").href("said/show").toString();
    }

    @GET
    @Path("show")
    public String show() {
	return p("you said: ", what).toString();
    }
}
