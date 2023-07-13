package com.rgt.client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatClient2 extends JFrame {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9999;

    private final JTextArea chatTextArea = new JTextArea(20, 40);
    private final JTextField messageTextField = new JTextField(40);
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ChatClient2() {
		
		/*
		 * JPanel mainPanel = new JPanel(); chatTextArea.setEditable(false); JScrollPane
		 * scrollPane = new JScrollPane(chatTextArea); mainPanel.add(scrollPane);
		 * mainPanel.add(messageTextField); messageTextField.addActionListener(e ->
		 * sendMessage());
		 */
		 

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0x075e54)); // Set the background color similar to WhatsApp
        chatTextArea.setEditable(false);
        chatTextArea.setBackground(Color.WHITE); // Set the chat text area background color
        chatTextArea.setForeground(Color.BLACK); // Set the chat text area text color
        chatTextArea.setLineWrap(true); // Enable line wrapping for long messages
        chatTextArea.setWrapStyleWord(true); // Wrap words at whitespace boundaries

        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        mainPanel.add(scrollPane);
        mainPanel.add(messageTextField);
        messageTextField.addActionListener(e -> sendMessage());
        
        
        
        this.setContentPane(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("RGT Chat App");
        this.setVisible(true);
    }

    public void start() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to chat server");
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            new Thread(new MessageReceiver()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = messageTextField.getText();
        messageTextField.setText("");
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MessageReceiver implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    String message = (String) input.readObject();
                    System.out.println("Received message: " + message);
                    SwingUtilities.invokeLater(() -> chatTextArea.append(message + "\n"));
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Connection closed by server");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatClient2 client = new ChatClient2();
            client.start();
        });
    }
}
