package ro.msg.edu.jbugs.userManagement.business.service.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteToFilesConverter {

    public BufferedImage getImagefromBytes(byte[] bytes){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(bytes));
            File outputFile = new File("C:/Users/giurgi/Desktop/saved.jpg");
            ImageIO.write(img,"jpg",outputFile);
            return img;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getDocfromBytes(byte[] bytes){
        try {
            Path path = Paths.get("C:/Users/giurgi/Desktop/saved.doc");
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
