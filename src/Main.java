import createFolder.FolderCreator;
import moveScreenshots.ScreenshotMover;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("First enter the address where do you want to create this folder" +
                " and enter the name of your folder.");

        String inputUrl = scanner.nextLine();
        String nameOfTheFolder = scanner.nextLine();

        FolderCreator folderCreator = new FolderCreator(inputUrl,nameOfTheFolder);
        folderCreator.createFolderIfNotExist();

        //Automatically get the url of screenshots folder of the user
        String username = System.getProperty("user.name");
        ScreenshotMover screenshotMover = new ScreenshotMover("C:\\Users\\" + username + "\\Pictures\\Screenshots\\"
                        ,inputUrl+"\\"+nameOfTheFolder
                        ,nameOfTheFolder);

        while(true){
            screenshotMover.moveScreenshots();
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
