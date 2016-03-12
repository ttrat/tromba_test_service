package helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/helloworld")
public class HelloWorldService {
	
	//This is a test.

	@GET
	@Path("/print")
	@Produces( MediaType.TEXT_PLAIN )
	public Response helloWorld(){
		
		return Response.ok("Hello World!").build();
		
	}
	
}
