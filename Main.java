package com.company;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        String text = "";
        String key = "82e622ccc27b4ad0af0918182329a742";
        String region = "westeurope";

        Scanner l = new Scanner(System.in);
        System.out.println("Enter the code of language: ");
        String lang = l.nextLine();

        String API_URL = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&to="+lang;

        Scanner sc = new Scanner(new File("C:\\IdeaProjects\\translator\\src\\text.txt"));
        while (sc.hasNextLine()) {
            text = text + " " + sc.nextLine();
        }

        String POSTData = "[{'Text':'"+text+"'}]";

        URL url = new URL(API_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
        urlConnection.setRequestProperty("Ocp-Apim-Subscription-Region", region);
        urlConnection.setRequestProperty("Content-Type", "application/json");

        urlConnection.setDoOutput(true);
        OutputStream out = urlConnection.getOutputStream();
        out.write(POSTData.getBytes());

        Scanner in = new Scanner(urlConnection.getInputStream());
        if (in.hasNext()) {
            FileWriter writer = new FileWriter("output.txt", true);
            String outt = in.nextLine() + System.lineSeparator();
            System.out.println(outt);
            writer.write(outt);
            writer.flush();
        } else System.out.println("No output returned");
        urlConnection.disconnect();
    }
}