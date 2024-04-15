package com.themyntt.dStore;

import com.sun.tools.javac.Main;
import com.themyntt.dStore.entity.UserEntity;
import com.themyntt.dStore.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class App extends JFrame {
    private JPanel MainPanel;
    private JPanel LoginPage;
    private JPanel LoginPanel;
    private JTextField EmailField;
    private JPasswordField PasswordField;
    private JButton LoginBtn;
    private JPanel HomePage;
    private JLabel HomeName;
    private JButton newProductButton;
    private JButton newClientUserButton;
    private JButton newCollaboratorButton;
    private JButton seeProductsButton;
    private JButton ExitButton;
    private JButton seeClientsUsersButton;
    private JButton seeCollaboratorsButton;
    private Utils util;

    public App() {
        setTitle("dStore");
        setSize(800,500);
        setContentPane(MainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        util = new Utils();

        LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = EmailField.getText();
                String password = new String(PasswordField.getPassword());

                try {
                    UserEntity user = util.getUser(email, password);

                    if (user != null) {
                        JOptionPane.showMessageDialog(null, "Welcome " + user.name + "!");

                        EmailField.setText("");
                        PasswordField.setText("");
                        HomeName.setText("Welcome, " + user.name);
                        MainPanel.removeAll();
                        MainPanel.add(HomePage);
                        MainPanel.revalidate();
                        MainPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect email or password.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Internal Server Error: 500");
                }
            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.removeAll();
                MainPanel.add(LoginPage);
                MainPanel.revalidate();
                MainPanel.repaint();
            }
        });
    }

    public static void main(String[] args) {
        new App();
    }
}
