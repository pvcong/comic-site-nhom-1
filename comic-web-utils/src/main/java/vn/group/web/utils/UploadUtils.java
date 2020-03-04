package vn.group.web.utils;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


public class UploadUtils {
    @Autowired
    static Logger logger = Logger.getLogger(UploadUtils.class);
    public static Object[] uploadFile (MultipartFile[] file,String folder,String path) throws FileNotFoundException,IOException{
        StringBuilder pathFiles = new StringBuilder();
        String location = path + File.separator + folder;
        File currentDirFile = new File("resources");
        currentDirFile.exists();
        currentDirFile.getAbsolutePath();
        createFolderNoExits(location);
        Boolean isFailed = false;
        for(MultipartFile multipartFile : file){
            try {
                File fileWrite = new File(location + File.separator + multipartFile.getOriginalFilename());
                if(fileWrite.exists()){
                    if(!fileWrite.delete()){
                        isFailed = true;
                        break;
                    }
                }
                else{
                    fileWrite.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(fileWrite);
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.close();
                pathFiles.append(fileWrite.getName()).append(",");
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage(),e);
                isFailed = true;
                break;

            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                isFailed = true;
                break;
            }

        }

        return new Object[] {isFailed,location,pathFiles};
    }
    private static void createFolderNoExits(String location){
        File fileFolder = new File(location);
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
    }
}
