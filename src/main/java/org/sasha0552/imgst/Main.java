package org.sasha0552.imgst;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        List<File> images = Arrays.stream(Objects.requireNonNull(new File(".").listFiles())).filter(file -> file.getName().endsWith(".png")).sorted().collect(Collectors.toList());

        int x = 0;
        int y = 0;

        for (File file : images) {
            BufferedImage image = ImageIO.read(file);

            x += image.getWidth();
            y += image.getHeight();

            System.out.printf("%s: %dx%d%n", file.getName(), image.getWidth(), image.getHeight());
        }

        y = new Double(((Integer) y).doubleValue() / images.size()).intValue();

        System.out.printf("New image size: %dx%d%n", x, y);

        BufferedImage result = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);

        int interval = 0;

        for (File file : images) {
            BufferedImage image = ImageIO.read(file);

            result.getGraphics().drawImage(image, interval, 0, null);

            interval += image.getWidth();
        }

        ImageIO.write(result,"png", new File("result.png"));
    }
}
