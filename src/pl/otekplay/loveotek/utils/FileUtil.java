package pl.otekplay.loveotek.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File createFolder(File file){
        if(file.exists()){
            return file;
        }
        file.mkdir();
        return file;
    }
    public static File createFile(File file){
        if(file.exists()){
            return file;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
