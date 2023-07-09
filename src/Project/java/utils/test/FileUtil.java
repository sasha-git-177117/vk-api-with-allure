package utils.test;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileUtil {

    private static final Integer HEXADECIMAL_REPRESENTATION = 0xff;
    private static final Integer BIT_FOR_RED = 16;
    private static final Integer BIT_FOR_GREEN = 8;

    public static void savePhotoFromVk(String url, String pathToSaveUrl) {
        try {
            URL saveUrl = new URL(url);
            File destination = new File(pathToSaveUrl);
            FileUtils.copyURLToFile(saveUrl, destination);
        }
        catch (IOException ex) {
            AqualityServices.getLogger().error(ex.getMessage());
        }
    }

    public static void deleteDirectory(String pathDirectory) {
        try {
            FileUtils.deleteDirectory(new File(pathDirectory));
        } catch (IOException ex) {
            AqualityServices.getLogger().error(ex.getMessage());
        }
    }

    public static boolean compareImage(String pathFile1, String pathFile2) {
        BufferedImage imgA = null;
        BufferedImage imgB = null;

        try {
            imgA = ImageIO.read(new File(pathFile1));
            imgB = ImageIO.read(new File(pathFile2));
        }
        catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }

        if ((imgA.getWidth() != imgB.getWidth()) || (imgA.getHeight() != imgB.getHeight()))
            return false;
        else {
            long difference = 0;
            for (int y = 0; y < imgA.getHeight(); y++) {
                for (int x = 0; x < imgB.getHeight(); x++) {

                    difference += Math.abs(getRed(imgA.getRGB(x, y)) - getRed(imgB.getRGB(x, y)));
                    difference += Math.abs(getGreen(imgA.getRGB(x, y)) - getGreen(imgB.getRGB(x, y)));
                    difference += Math.abs(getBlue(imgA.getRGB(x, y))- getBlue(imgB.getRGB(x, y)));
                }
            }
            return difference==0;
        }
    }
    public static int getRed(int rgbValue){
        return (rgbValue >> BIT_FOR_RED) & HEXADECIMAL_REPRESENTATION;
    }

    public static int getGreen(int rgbValue){
        return (rgbValue >> BIT_FOR_GREEN) & HEXADECIMAL_REPRESENTATION;
    }

    public static int getBlue(int rgbValue){
        return (rgbValue) & HEXADECIMAL_REPRESENTATION;
    }
}
