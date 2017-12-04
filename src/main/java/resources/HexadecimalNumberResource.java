package resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/hexadecimal")
public class HexadecimalNumberResource {

    public static boolean isInteger(String s) {
        if(s.length() == 0) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),10) < 0) return false;
        }
        return true;
    }


    @GET
    @Path("/{id}")
    public Response getHexadecimal(@PathParam("id") String id){

        //String a = Integer.toString(id);

        if(isInteger(id)){
            String a = id + id + id;
            return Response.status(Response.Status.ACCEPTED)
                    .entity(a)
                    .build();
        }

        else {
            //String message = "The argument you have provided is not an integer number!";
            String message = "Not a number!";
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(message)
                    .build();
        }


    }

}
