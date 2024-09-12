package createFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FolderCreator {
    // The destination that folder will be created.
    // User should give the address.
    private String url;

    //Name of the folder
    private String name;

    public FolderCreator(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public void createFolderIfNotExist() {
        try {
            if(Files.exists(Paths.get(url+"/"+name))) {
                System.out.println("Folder already exists");
            }else{
                Files.createDirectory(Paths.get(url+"/"+name));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
