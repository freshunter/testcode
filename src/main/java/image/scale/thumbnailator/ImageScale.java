package image.scale.thumbnailator;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ImageScale {
    
    public static void main(String[] args) throws IOException {
        Thumbnails.of("test.jpg").size(200, 300).toFile("200x300test.jpg");
    }

}
