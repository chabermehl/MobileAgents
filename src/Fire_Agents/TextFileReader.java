package Fire_Agents;

import java.io.*;
import java.util.LinkedList;

/**
 * this class reads lines of a file into a linked list of strings
 */
public class TextFileReader {
    LinkedList<String> FileToList(String fileName) {
        LinkedList<String> fileList = new LinkedList<>();
        String line;
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream(fileName);
            InputStreamReader fileReader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                fileList.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No file found with name " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileList;
    }
}
