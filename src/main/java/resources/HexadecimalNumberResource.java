package resources;

import model.ErrorMessage;
import model.NumberModel;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
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

    private static String decimalToHexadecimal(int a){
        if(a == 0) return "0";
        StringBuilder s = new StringBuilder();
        boolean minus = false;
        if (a < 0) {minus = true; a = -a;}
        int b;
        while (a > 0){
            b = a % 16;
            a = a/16;
            if(b < 10) s.insert(0,Integer.toString(b));
            else if (b == 10) s.insert(0, "A");
            else if (b == 11) s.insert(0, "B");
            else if (b == 12) s.insert(0, "C");
            else if (b == 13) s.insert(0, "D");
            else if (b == 14) s.insert(0, "E");
            else  s.insert(0, "F");
        }
        if(minus) s.insert(0,"-");
        return s.toString();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getHexadecimal(@PathParam("id") String id){

        //String a = Integer.toString(id);

        if(isInteger(id)){
            String answer = decimalToHexadecimal(Integer.parseInt(id));
            NumberModel numberModel = new NumberModel(answer);
            return Response.status(Response.Status.OK)
                    .entity(numberModel)
                    .build();
        }

        else {
            //String message = "The argument you have provided is not an integer number!";
            String message = "Not a number!";
            ErrorMessage errorMessage = new ErrorMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .build();
        }


    }

}
