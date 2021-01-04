package Misc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Episode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class EcouteurDownload implements EventHandler<ActionEvent> {
    private Episode episodeliee;

    public EcouteurDownload(Episode e){
        this.episodeliee=e;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Episode e;
        String MediaURL=this.episodeliee.getURL();
        class MyThread extends Thread{
            String MediaURL;
            MyThread(String URL){
                this.MediaURL=URL;
            }
            public void run(){
                System.out.println("Downloading Thread starting");
                try {
                    DownloadMedia downloader=new DownloadMedia(episodeliee.getName()+".mp3",MediaURL);
                    downloader.downloadURL();
                    System.out.println("Download done");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        MyThread runnable=new MyThread(MediaURL);
        runnable.start();



    }
}