package app.util;

import java.io.*;

public class HtmlReader {

    public String readHtmlFile(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));

        String line;
        while ((line = reader.readLine()) != null){
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
