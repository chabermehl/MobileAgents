package Fire_Agents;

import java.io.File;
import java.util.LinkedList;


public class GraphLoader {
    /**
     * failed attempt to load multiple graphs at once
     *
     * @param folder folder holding multiple graphs
     * @return list containing graph files from directory
     */
    public LinkedList<String> graphList(final File folder) {
        LinkedList<String> list = new LinkedList<>();
        //add new config files here
        //list.add(<config file>
        list.add("default");

        return list;
    }
}
