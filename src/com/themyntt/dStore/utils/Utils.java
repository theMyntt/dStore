package com.themyntt.dStore.utils;

import com.themyntt.dStore.entity.UserEntity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utils {
    public UserEntity parseUserEntity(String response) {
        System.out.println("response: " + response);
        if (response.isEmpty() || response == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        String[] parts = response.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].replaceAll("\"", "").trim();
            String value = keyValue[1].replaceAll("\"", "").trim();
            switch (key) {
                case "id":
                    user.id = value;
                    break;
                case "name":
                    user.name = value;
                    break;
                case "cellphone":
                    user.cellphone = value;
                    break;
                case "email":
                    user.email = value;
                    break;
                case "password":
                    user.password = value;
                    break;
                case "company":
                    user.company = value;
                    break;
                default:
                    // Outro campo não reconhecido
                    break;
            }
        }
        return user;
    }

    public UserEntity getUser(String email, String password) throws IOException {
        if (email.isEmpty() || password.isEmpty()) {
            return null;
        }

        String requestBody = "{\"email\":\"" + email.toLowerCase() + "\",\"password\":\"" + password + "\"}";

        HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:8080/api/user/get/").openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json"); // Defina o tipo de mídia aqui

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        conn.disconnect();

        return parseUserEntity(response.toString());
    }
}
