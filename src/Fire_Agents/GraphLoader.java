package Fire_Agents;

import java.io.*;
import java.util.LinkedList;

public class GraphLoader {
    public LinkedList<String> graphList(String path) {
        LinkedList<String> list  = new LinkedList<>();
        String resource;
        try{
            InputStream in = getResourcesAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            while((resource = bufferedReader.readLine()) != null) {
                list.add(resource);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private InputStream getResourcesAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
