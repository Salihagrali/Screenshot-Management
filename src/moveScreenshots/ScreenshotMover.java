package moveScreenshots;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class ScreenshotMover {

    //Screenshot folder
    private String sourceUrl;

    //Destination folder
    private String destinationUrl;
    private Path path;
    private String nameOfTheFolder;

    public ScreenshotMover(String sourceUrl, String destinationUrl,String nameOfTheFolder) {
        this.sourceUrl = sourceUrl;
        this.destinationUrl = destinationUrl;
        this.nameOfTheFolder = nameOfTheFolder;
        path = Paths.get(destinationUrl);
    }

    public void moveScreenshots() {
        //We go through each file from the source folder which is the screenshot folder.
        try{
            Files.walk(Paths.get(sourceUrl))
                    .filter(e -> {
                        try {
                            FileTime creationTime = (FileTime) Files.getAttribute(e, "creationTime");
                            FileTime creationOfSourceFolder = (FileTime) Files.getAttribute(path, "creationTime");
                            return creationTime.compareTo(creationOfSourceFolder) > 0 && !(e.toString().contains(nameOfTheFolder));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .forEach(e -> {
                        try {
                           // I tried Files.move() method to do the same thing, but it didn't worked that well.
                           // So I decided to use Apache Commons Library.
                           FileUtils.moveFileToDirectory(e.toFile(),path.toFile(),true);
                       } catch (IOException ex) {
                           throw new RuntimeException(ex);
                       }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }
}
