import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress first = new GameProgress(100, 10, 10, 1500);
        GameProgress second = new GameProgress(95, 8, 11, 1400);
        GameProgress third = new GameProgress(50, 1, 1, 200);

        File ziptext = new File("D://Games/savegames/zip.txt");
        try {
            if (ziptext.createNewFile()) {
                System.out.println("OK");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path1 = "D://Games/savegames/savedata1.dat";
        saveGame(path1, first);
        String path2 = "D://Games/savegames/savedata2.dat";
        saveGame(path2, second);
        String path3 = "D://Games/savegames/savedata3.dat";
        saveGame(path3, third);

        String zipPath = "D://Games/savegames/zip.zip";
        String[] allfiles = {"D://Games/savegames/savedata1.dat", "D://Games/savegames/savedata2.dat", "D://Games/savegames/savedata3.dat", "D://Games/savegames/zip.txt"};
        zipFiles(zipPath, allfiles);

        for (String s : allfiles) {
            File file = new File(s);
            if (file.delete()) {
                System.out.println(s + " файл удален");
            } else System.out.println(s + " не обнаружено");
        }
    }

    public static void zipFiles(String path, String[] list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
             FileInputStream fis = new FileInputStream("D://Games/savegames/zip.txt")) {
            for (String s : list) {
                ZipEntry entry1 = new ZipEntry(s);
                zout.putNextEntry(entry1);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception ex) {
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
