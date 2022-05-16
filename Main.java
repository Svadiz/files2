import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress first = new GameProgress(100, 10, 10, 1500);
        GameProgress second = new GameProgress(95, 8, 11, 1400);
        GameProgress third = new GameProgress(50, 1, 1, 200);

        String path1 = "C://Games/savegames/savedata1.dat";
        saveGame(path1, first);
        String path2 = "C://Games/savegames/savedata2.dat";
        saveGame(path2, first);
        String path3 = "C://Games/savegames/savedata3.dat";
        saveGame(path3, first);

        String zipPath = "C://Games/savegames/zip.zip";
        String[] allfiles = {"C://Games/savegames/savedata1.dat", "C://Games/savegames/savedata2.dat", "C://Games/savegames/savedata3.dat" };
        zipFiles(zipPath, allfiles);

    }

    public static void zipFiles(String path, String[] list) {
        for (int i = 0; i < list.length; i++) {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
                 FileInputStream fis = new FileInputStream(list[i])) {
                ZipEntry entry = new ZipEntry(list[i] + "archive");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
