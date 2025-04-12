/**
 *
 *  @author Wierzba Kacper S30508
 *
 */

package zad1;


import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Tools {
    public static Options createOptionsFromYaml(String fileName) {

        try {
            InputStream inputStream = new FileInputStream(fileName);
            Yaml yaml = new Yaml();

            Map<String, Object> data = yaml.load(inputStream);

            return new Options(
                    (String) data.get("host"),
                    (int) data.get("port"),
                    (boolean) data.get("concurMode"),
                    (boolean) data.get("showSendRes"),
                    (Map<String, List<String>>) data.get("clientsMap")
            );


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
