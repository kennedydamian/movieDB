package com.damian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtils {

    public static String loadStringFromFile (String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        String convertedString = getStringFromInputStream(inputStream);
        inputStream.close();

        return convertedString;
    }

    public static String getStringFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String nextLine;

        boolean isFirstLine = true;
        nextLine = bufferedReader.readLine();

        while (nextLine != null) {
            if(isFirstLine){
                builder.append(nextLine);
                isFirstLine = false;
            } else {
                builder.append("\n").append(nextLine);
            }
            nextLine = bufferedReader.readLine();
        }
        bufferedReader.close();
        return builder.toString();
    }
}
