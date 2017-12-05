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
@Path("/roman")
public class RomanNumberResource {

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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getRoman(@PathParam("id") String id){

        //String a = Integer.toString(id);

        if(isInteger(id)){
            String answer = decimalToRoman(Integer.parseInt(id));
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

    private static String romanDecimalLevel(int x, String one, String five, String ten){
        if (x == 9) return one+ten;
        else if (x==8) return five+one+one+one;
        else if (x==7) return five+one+one;
        else if (x==6) return five+one;
        else if (x==5) return five;
        else if (x==4) return one+five;
        else if (x==3) return one+one+one;
        else if (x==2) return one+one;
        else if (x==1) return one;
        else return "";
    }

    private static String decimalToRoman(int a){
        StringBuilder s = new StringBuilder();
        int b;
        b = a/1000;
        a -= b*1000;
        for(int i = 0; i < b; i++) s.append("M");

        b = a/100;
        a -= b*100;
        s.append(romanDecimalLevel(b,"C","D","M"));

        b = a/10;
        a -= b*10;
        s.append(romanDecimalLevel(b,"X","L","C"));

        s.append(romanDecimalLevel(a,"I","V","X"));

        return s.toString();

    }

}