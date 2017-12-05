
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import resources.RomanNumberResource;

import javax.ws.rs.core.Application;
import java.io.File;

public class RomNumConvServiseTest extends  NumConvServiseTest{
    private static String urlPathPrefix = "/roman/";
    private static String pathToCSVFile =  new File("resources/roman_data.csv").getAbsolutePath();

    @Override
    protected Application configure() {
        return new ResourceConfig(RomanNumberResource.class);
    }

    @Test
    public  void test () throws InterruptedException {
        super.test(urlPathPrefix , pathToCSVFile);

    }
}
