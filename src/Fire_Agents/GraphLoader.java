package Fire_Agents;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class GraphLoader {
    public LinkedList<String> graphList(final File folder) {
        LinkedList<String> list = new LinkedList<>();
        //add new config files here
        //list.add(<config file>
        list.add("default");

        return list;
    }
}
