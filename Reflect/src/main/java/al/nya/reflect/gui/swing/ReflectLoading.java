package al.nya.reflect.gui.swing;

import al.nya.reflect.Reflect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReflectLoading{
    public JFrame frame;
    JProgressBar progressBar=new JProgressBar();
    JProgressBar downloadBar=new JProgressBar();
    JLabel message=new JLabel();
    private String defaultString = "Loading Reflect";
    public String loadingType = "";
    public ReflectLoading(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);//setting layout to null
        frame.setUndecorated(true);//Turning off Title bar
        frame.setSize(410,60);//Setting size
        frame.setLocationRelativeTo(null);//Setting location to the center of screen
        frame.getContentPane().setBackground(Color.darkGray);//setting background color
        frame.setVisible(true);//setting visibility

        //Total progress bar
        progressBar.setBounds(5,5,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.BLACK);
        progressBar.setForeground(Color.WHITE);
        progressBar.setValue(0);
        frame.add(progressBar);

        //Loading message
        message.setBounds(5,35,400,20);//Setting the size and location of the label
        message.setForeground(Color.WHITE);//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        message.setText("Loading Reflect-"+loadingType);
        frame.add(message);//adding label to the frame
    }
    public void setLoadingType(String type){
        loadingType = type;
        message.setText("Loading Reflect-"+loadingType);
    }
    public void downloadProgress(int now,int max){
        message.setText("Loading Reflect-"+loadingType+" "+now+"/"+max);
    }
    public void progress(int now,int max){
        progressBar.setMaximum(max);
        progressBar.setValue(now);
        progressBar.updateUI();
        try {
            if (Reflect.injector != null)
                Reflect.injector.getOutputStream().write(String.valueOf((int)(now / max)).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void finishLoading(){
        progressBar.setMaximum(50);
        progressBar.setMinimum(0);
        progressBar.setString("Finish :)");
        new Thread(new Runnable() {
            @Override
            public void run() {
                int s = 0;
                while (s != 50){
                    s++;
                    progressBar.setValue(50 - s);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                frame.setVisible(false);
                frame.dispose();
            }
        }).start();
    }
    public void FailLoading(String reason){
        progressBar.setMaximum(50);
        progressBar.setMinimum(0);
        progressBar.setString("Fail :)");
        message.setText(reason);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int s = 0;
                while (s != 50){
                    s++;
                    progressBar.setValue(50 - s);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                frame.setVisible(false);
                frame.dispose();
            }
        }).start();
    }
}