package csl;

import csl.model.ScreenCaptureService;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {

        // Make screenshot from certain page
        // Save it under certain file name
        // To a certain location

        ScreenCaptureService scs = new ScreenCaptureService();

        String url = "carmenscholte.github.io/carmenscholteart";
        String fileName = "BrowserShot";
        String fileLocation = "C:/Users/Carmen/dev/screenshots";

        scs.makeBrowserScreenshot(url, fileName, fileLocation);

        // Make screenshot from desktop
        // Save it under certain file name
        // To a certain location

        fileName = "DesktopShot";
        scs.makeDesktopScreenshot(fileName, fileLocation);


    }
}
