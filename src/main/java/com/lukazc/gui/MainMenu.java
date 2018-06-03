package com.lukazc.gui;

import javax.swing.*;
import java.awt.*;

import static com.lukazc.Main.chessboard;
import static com.lukazc.Main.mainMenu;

public class MainMenu extends JFrame {

    private final JButton playButton;
    private final JButton quitButton;
    private final JLabel background;

    public MainMenu()

    {

        // Size and display

        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Center window
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Window deleted from memory

        // Initialize Layout

        setLayout(new BorderLayout());

        // Insert image to the background

        setContentPane(new JLabel(new ImageIcon("./././././assets/mainMenu.jpg")));
        setLayout(new FlowLayout());


        // Push buttons vertical

        add(Box.createVerticalStrut(280)); // Box height 280px

        // Create background and playButton, center button

        background = new JLabel("");
        playButton = new JButton("PLAY");
        playButton.setAlignmentX(CENTER_ALIGNMENT);

        // Add ActionListener to swap windows

        playButton.addActionListener(e -> {
            // When play button is pressed hide main menu window and show game window
            mainMenu.setVisible(false);
            chessboard.setVisible(true);
        });

        // Create spacing between buttons

        add(Box.createVerticalStrut(40));

        // Create quitButton, center button

        quitButton = new JButton("QUIT");
        quitButton.setAlignmentX(CENTER_ALIGNMENT);

        // Add ActionListener to quit on click

        quitButton.addActionListener(e -> {
            // When quit button is pressed hide main menu window and show game window
            System.exit(0);
        });

        // Glue box contents

        add(Box.createVerticalGlue());

        // Insert background and buttons

        add(background);
        add(playButton);
        add(quitButton);

        // For constant refresh of the screen

        setSize(499,499);
        setSize(500,500);
    }
}

