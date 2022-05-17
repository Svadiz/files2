import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress first = new GameProgress(100, 10, 10, 1500);
        GameProgress second = new GameProgress(95, 8, 11, 1400);
        GameProgress third = new GameProgress(50, 1, 1, 200);

        String path1 = "D://Games/savegames/savedata1.dat";
        saveGame(path1, first);
        String path2 = "D://Games/savegames/savedata2.dat";
        saveGame(path2, second);
        String path3 = "D://Games/savegames/savedata3.dat";
        saveGame(path3, third);

        String zipPath = "D://Games/savegames/zip.zip";
        String[] allfiles = {"D://Games/savegames/savedata1.dat", "D://Games/savegames/savedata2.dat", "D://Games/savegames/savedata3.dat" };
        zipFiles(zipPath, allfiles);

        File file1 = new File("D://Games/savegames/savedata1.dat");
        if(file1.delete()){
            System.out.println("D://Games/savegames/savedata1.dat файл удален");
        } else System.out.println("Файла D://Games/savegames/savedata1.dat не обнаружено");

        File file2 = new File("D://Games/savegames/savedata2.dat");
        if(file2.delete()){
            System.out.println("D://Games/savegames/savedata2.dat файл удален");
        } else System.out.println("Файла D://Games/savegames/savedata2.dat не обнаружено");

        File file3 = new File("D://Games/savegames/savedata3.dat");
        if(file3.delete()){
            System.out.println("D://Games/savegames/savedata3.dat файл удален");
        } else System.out.println("Файла D://Games/savegames/savedata3.dat не обнаружено");

        File file4 = new File("D://Games/savegames/zip.txt");
        if(file4.delete()){
            System.out.println("D://Games/savegames/zip.txt файл удален");
        } else System.out.println("Файла D://Games/savegames/zip.txt не обнаружено");


    }

    public static void zipFiles(String path, String[] list) {
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D://Games/savegames/zip.zip"));
            FileInputStream fis= new FileInputStream("D://Games/savegames/zip.txt");)
        {
            for (String s : list) {
                ZipEntry entry1 = new ZipEntry(s);
                zout.putNextEntry(entry1);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void saveGame(String path, GameProgress saveGame) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(saveGame);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
