import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Class which pulls data from a CSV to be used with TestNG data provider
 *  Test Data location : src/main/resources/testData
 */
public class CSVDataProvider {


    //Grab each row in CSV, convert to a list of hash maps<String, String> and return
    public static List<Map<String, String>> getData(String filePath) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                Map<String, String> map = new HashMap<>();
                String value1 = line.split(",")[0];
                String value2 = line.split(",")[1];
                map.put("value1", value1);
                map.put("value2", value2);
                list.add(map);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //Data provider for FindTeam.java methods
    @DataProvider(name = "findTeamTestData")
    public static Object[][] findTeamTestData() {
        List<Map<String, String>> result = getData("D:\\randomBots\\TheScoreTests\\src\\main\\resources\\testData\\findTeamData.csv");
        Object[][] files = new Object[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            files[i] = new Object[]{result.get(i)};
        }
        return files;
    }

}


