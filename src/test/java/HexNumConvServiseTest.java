
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import resources.HexadecimalNumberResource;
import resources.RomanNumberResource;

import javax.ws.rs.core.Application;
import java.io.File;

public class HexNumConvServiseTest extends NumConvServiseTest {
    private static String urlPathPrefix = "/hexadecimal/";
    private static String pathToCSVFile =  new File("resources/hex_data.csv").getAbsolutePath();

    @Override
    protected Application configure() {
        return new ResourceConfig(HexadecimalNumberResource.class);
    }

    @Test
    public  void test () throws InterruptedException {
        super.test(urlPathPrefix , pathToCSVFile);

    }


}
