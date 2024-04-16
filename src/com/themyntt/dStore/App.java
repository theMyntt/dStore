package com.themyntt.dStore;

import com.themyntt.dStore.entity.UserEntity;

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
    private JPanel NewProductPage;
    private JButton backToHomeButton;
    private JPanel NewUserPage;
    private Api util;
    private String name;

    public App() {
        setTitle("dStore");
        setSize(800,500);
        setContentPane(MainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        util = new Api();

        LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = EmailField.getText();
                String password = new String(PasswordField.getPassword());

                try {
                    UserEntity user = util.getUser(email, password);

                    if (user != null) {
                        JOptionPane.showMessageDialog(null, "Welcome " + user.name + "!");

                        name = user.name;
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
        newProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.removeAll();
                MainPanel.add(NewProductPage);
                MainPanel.revalidate();
                MainPanel.repaint();
            }
        });
        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.remove(NewProductPage);
                MainPanel.add(HomePage);
                MainPanel.revalidate();
                MainPanel.repaint();
            }
        });
        newClientUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.equals(null) || !name.toLowerCase().equals("admin")) {
                    JOptionPane.showMessageDialog(null, "You dont have permission to this.");
                } else {
                    MainPanel.removeAll();
                    MainPanel.add(NewUserPage);
                    MainPanel.revalidate();
                    MainPanel.repaint();
                }
            }
        });
    }

    public static void main(String[] args) {
        new App();
    }
}
