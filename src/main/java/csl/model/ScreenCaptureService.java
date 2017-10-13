package csl.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Carmen on 12-10-2017.
 */
public class ScreenCaptureService {

    private static final Logger LOG = Logger.getLogger("ScreenCaptureService");

    public void makeScreenshot(String fileName) {
        FileSystem fs = FileSystems.getDefault();
        Iterable<Path> paths = fs.getRootDirectories();
        Iterator<Path> it = paths.iterator();
        makeScreenshot(fileName, "png", it.next());
    }

    public void makeScreenshot(String fileName, String fileType, String path) {
        Path p = Paths.get(path);
        makeScreenshot(fileName, fileType, p);
    }

    public void makeScreenshot(String fileName, String fileType, Path path) {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(screenFullImage, "png", out);

            File directory = new File(path.toString());
            System.out.println(directory.mkdirs());

            Files.write(Paths.get(directory.toString() + "/" + fileName + formatFileType(fileType)), out.toByteArray());
        } catch (Exception ex) {
            LOG.info(ex.getMessage());
        }
    }

    private String formatFileType(String type) {
        return type.startsWith(".") ? type : "." + type;
    }
}
