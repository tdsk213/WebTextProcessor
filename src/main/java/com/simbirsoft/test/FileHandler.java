package com.simbirsoft.test;

import java.io.*;
import java.net.URL;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileHandler {

    /**
     * Download html page save it in user's directory
     * @param url - a URL
     * @return java.io.File
     */
    public static File getFileFromURL(URL url) {

        File textFile = new File(System.getProperty("user.dir") + File.separator + "test_file.html");
        try (BufferedInputStream inputStream = new BufferedInputStream(url.openStream())) {
            if (!textFile.exists()) {
                if (textFile.getParentFile().mkdirs())
                    textFile.createNewFile();
            }

            FileOutputStream fileWriter = new FileOutputStream(textFile);
            int c;
            while ((c = inputStream.read()) != -1) fileWriter.write(c);

            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Malformed URL!");
        }
        return textFile;
    }

    /**
     * Save Map Collection to file
     * @param map - Map<String, Integer>
     */
    public static void saveStatsToFile(Map<String, Integer> map, String url)  {

        File textFile = new File(System.getProperty("user.dir") + File.separator + "statistics.log");

        if (!textFile.exists()) {
            if (textFile.getParentFile().mkdirs()) {
                try {
                    textFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(textFile, false), UTF_8)) {

            osw.write(url + ":\n\n");

         map.forEach((key, value) -> {
             try {
                 osw.write(key + ": " + value + "\n");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
