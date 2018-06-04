package com.lukazc.gui;
import javax.swing.*;

public class Splash extends JFrame{

    private final JFrame splash = new JFrame();
    private static String vid;

    public Splash(){

        vid = "./././././assets/video.gif";
        JPanel panel = new JPanel();
        ImageIcon video = new ImageIcon(vid);
        splash.setSize(500, 500);
        splash.setLocationRelativeTo(null); // Center window
        splash.setResizable(false);
        splash.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        splash.getContentPane().add(panel);
        splash.setAlwaysOnTop(true);
        JLabel label = new JLabel(video);
        panel.add(label);
        splash.setVisible(false);
    }
    void display(){
        splash.setVisible(true);
    }
    void exitSplash(){
        splash.setVisible(false);
    }
}