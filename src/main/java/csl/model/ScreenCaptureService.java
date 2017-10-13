package csl.model;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Carmen on 12-10-2017.
 */
public class ScreenCaptureService {

    private static final Logger LOG = Logger.getLogger("ScreenCaptureService");

    public void makeDesktopScreenshotSaveToRoot(String fileName) {
        FileSystem fs = FileSystems.getDefault();
        Iterable<Path> paths = fs.getRootDirectories();
        Iterator<Path> it = paths.iterator();
        makeDesktopScreenshot(fileName, it.next().toString());
    }

    public void makeDesktopScreenshot(String fileName, String fileLocation) {
        makeDesktopScreenshot(fileName, "png" ,fileLocation);
    }

    public void makeDesktopScreenshot(String fileName, String fileType, String fileLocation) {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(screenFullImage, fileType, out);

            File directory = new File(fileLocation);
            System.out.println(directory.mkdirs());
            Files.write(Paths.get(directory.toString() + "/" + fileName + formatFileType(fileType)), out.toByteArray());
        } catch (AWTException | IOException ex) {
            LOG.info(ex.getMessage());
        }
    }

    public void makeBrowserScreenshot(String url, String fileName, String fileLocation) {
        Path path = Paths.get(fileLocation);
        ChromeOptions options = new ChromeOptions().addArguments("--start-fullscreen");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://" + url);
        makeBrowserScreenshot(fileName, driver, path);
        driver.close();
    }

    public void makeBrowserScreenshot(String fileName, ChromeDriver driver, Path locationPath) {
        makeBrowserScreenshot(fileName, driver, locationPath.toString());
    }

    public void makeBrowserScreenshot(String fileName, ChromeDriver driver, String locationPath) {
        byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
        File targetDir = new File(locationPath);
        System.out.println(targetDir.mkdirs());

        try {
            Files.write(Paths.get(targetDir.toString() + "/" + fileName + ".png"), screenshot);
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
    }

    private String formatFileType(String type) {
        return type.startsWith(".") ? type : "." + type;
    }
}
