package csl;

import csl.model.ScreenCaptureService;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {

    public static void main(String[] args) {

        String strPath = "C:/Users/Carmen/dev/screenshots";
//        Path path = Paths.get("C:/Users/Carmen/dev/screenshots");

        ScreenCaptureService scs = new ScreenCaptureService();
//        scs.makeScreenshot("Capture", "jpg", strPath);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Carmen\\bin\\chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get("http://google.com");
    }
}
