package com.lukazc.gui;
import javax.swing.*;

public class Splash extends JFrame{

    private static String vid;

    public Splash(){

        vid = "./././././assets/video.gif";
        JFrame splash = new JFrame();
        JPanel panel = new JPanel();
        ImageIcon video = new ImageIcon(vid);
        splash.setSize(500, 500);
        splash.setLocationRelativeTo(null); // Center window
        splash.setResizable(false);
        splash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splash.getContentPane().add(panel);
        splash.setVisible(true);
        JLabel label = new JLabel(video);
        label.setIcon(video);
        panel.add(label);

    }
}