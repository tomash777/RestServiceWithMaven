import model.NumberModel;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import resources.RomanNumberResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NumConvServiseTest extends JerseyTest {



    protected Map<String, String> getDataFormCSV (String pathToCSVFile) {
        Map<String, String> result = new HashMap<String, String>();

        String line = "";
        String cvsSplitBy = ",";

        try  {
            BufferedReader br = new BufferedReader(new FileReader(pathToCSVFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] lineArray = line.split(cvsSplitBy);

                result.put(lineArray[0], lineArray[1]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }


    protected  void makeUnitTest (String input, String expected, String urlPathPrefix) throws InterruptedException {

        NumberModel model = target(urlPathPrefix + input )
                .request().get(NumberModel.class);
        String response = model.getConvertedNumber();
        Assert.assertEquals(expected, response);

    }
    protected  void test (String urlPathPrefix, String pathToDataFile ) throws InterruptedException {

        Map<String, String> map = getDataFormCSV(pathToDataFile);
        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (Map.Entry<String, String> entry: entries) {
            makeUnitTest(entry.getKey(), entry.getValue(), urlPathPrefix);

        }


    }



}
