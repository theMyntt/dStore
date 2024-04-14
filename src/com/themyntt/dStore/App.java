package com.themyntt.dStore;

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
                        JOptionPane.showMessageDialog(null, "Bem-vindo, " + user.name + "!");

                        EmailField.setText("");
                        PasswordField.setText("");
                        MainPanel.removeAll();
                        MainPanel.add(HomePage);
                        MainPanel.revalidate();
                        MainPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciais inválidas.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao obter detalhes do usuário. Por favor, tente novamente mais tarde.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new App();
    }
}
