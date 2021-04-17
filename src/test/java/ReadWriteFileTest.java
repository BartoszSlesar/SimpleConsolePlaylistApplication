import utils.ReadWriteFiles;

import java.io.File;

public class ReadWriteFileTest {
    public static void main(String[] args) {
        File direcotry = new File("src/main/resources/" + "test");
        System.out.println(direcotry.getPath());
    }
}
