package fdmc.util;

import java.io.*;

public class HtmlReaderImpl implements HtmlReader {

    @Override
    public String readHtmlFile(String filePath) throws IOException {
        StringBuilder httpContent = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        String line;
        while ((line = reader.readLine()) != null){
            httpContent.append(line).append(System.lineSeparator());
        }
        return httpContent.toString().trim();
    }
}
