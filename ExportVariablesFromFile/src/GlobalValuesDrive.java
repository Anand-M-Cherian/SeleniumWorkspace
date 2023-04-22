import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalValuesDrive {

    // Ensure to create file with .properties extension

    public static void main(String[] args) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\anand\\Documents\\Selenium Webdriver\\SeleniumEclipseWorkspace\\ExportVariablesFromFile\\src\\data.properties");
        prop.load(fis);

        System.out.println(prop.getProperty("Manufacturer"));
        System.out.println(prop.getProperty("Model"));

        // We can also change the properties at runtime
        prop.setProperty("Manufacturer", "RE");

        // Writing back to the properties file
        FileOutputStream fos = new FileOutputStream("C:\\Users\\anand\\Documents\\Selenium Webdriver\\SeleniumEclipseWorkspace\\ExportVariablesFromFile\\src\\data.properties");
        prop.setProperty("Capacity", "450");
        prop.store(fos, null);

        fis.close();
        fos.close();

    }
}
