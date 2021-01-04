package Misc;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadMedia {
    String OutputName;
    URL fileURL;
    FileOutputStream stream;

    public DownloadMedia(String DownloadName, String fileURL) throws FileNotFoundException, MalformedURLException {
        this.fileURL = new URL(fileURL);

        this.OutputName = DownloadName;
        this.stream = new FileOutputStream(OutputName);

    }
    public void downloadURL() throws IOException {
        FileUtils.copyURLToFile(this.fileURL, new File(this.OutputName));
    }





}
