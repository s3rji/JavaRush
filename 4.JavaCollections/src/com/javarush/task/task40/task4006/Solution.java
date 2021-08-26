package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.SocketOption;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket socket = new Socket(url.getHost(), url.getDefaultPort());

            String path = url.getPath();
            String request = "GET " + path + " HTTP/1.0\n\n";

            OutputStream os = socket.getOutputStream();
            os.write(request.getBytes());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}