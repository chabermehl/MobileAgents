# Design and Program Flow

- The program starts with a node being set "On Fire." 
- The Base Station creates an agent that traverses the graph of nodes until it reaches yellow ones. 
- Once it does, it gets duplicated to the others. 
- This process continues until the base station is destroyed.

<img src="https://i.postimg.cc/NFzRCpYx/Messaging-Diagram.png" alt="drawing" width="700"/>

All nodes run on their own threads, and so do agents.
- The NodeDisplay class queries nodes for their state periodically and updates the canvas
- Uses gui to instruct when the InitializeGraph class should start running the simulation via button press

<img src=https://i.postimg.cc/0yRSx43K/noice.png alt="drawing" width="700"/>

### Node Class Structure
- The Node class runs on its own thread, and is always in one of three states: **Fire**, **Danger**, and **Safe**.
- Each Node holds a list of neighbors that it can send messages to and query for information

<img src=https://i.postimg.cc/L5YTznZ5/Node-Diagram.png alt="drawing" width="600"/>


##### Methods
- **void setStartState**: Sets the state that this node should switch to once it's thread is started.
- **State getStartState**: Gets the state that the node will switch to once its thread is started.
- **boolean addNeighbor(Node)**: Adds the given node as a neighbor. That node does the same.
- **LinkedList<String> getNeighborNames**: Returns a list of names of each neighbor to this node.
- **State getState**: Gets the current state that this node is in.
- **int getX**: Gets the X coordinate of this node.
- **int getY**: Gets the Y coordinate of this node.
- **void setX**: Sets the X coordinate of this node.
- **void setY**: Sets the Y coordinate of this node.
- **String getName**: Returns the name of this node.

### HomeBase Class Structure
- The HomeBase class extends Node with some extra functionality.
- When its thread is started, HomeBase creates an agent that starts randomly traversing nodes until it reaches a fire.
- It holds a HashMap of Agent names corrosponding with node names to keep track of agents that have been created. 

<img src=https://i.postimg.cc/43FJxbjr/HomeBase.png alt="drawing" width="250"/>

### Agent Class Strucure
- The agent is created to traverse nodes, searching for nodes that are in danger.
- It is cloned to all surrounding nodes once it reaches a valid target.

<img src=https://i.postimg.cc/6pCwC4Tm/Agent-Diagram.png alt="drawing" width="250"/>

##### Methods
- **void setCurrnetNode**: Sets the agent's node reference
- **getName**: Returns the name of the agent
- **String getLastNodeVisited**: Returns the name of the last node that this agent traversed

## Sending Messages (MessageProcessor)
- Node (and by extension HomeBase) and Agent all extend the **MessageProcessor** class.
- MessageProcessors can send messages back and forth to each other with different message types.
- Messages can optionally include data that needs to be cast correctly when being read.
- MessageProcessors all use a LinkedBlockingQueue to store messages. They are processed within the run method.

## Loading Graphs and Initializing Nodes
- Graphs are loaded using the **TextFileReader** class in conjunction with the **GraphLoader** class.
- Nodes are initialized by utilizing the **InitializeGraph** class and **InitializeNodes** classes.
- **void initializeGraph**: Creates all of the nodes with their initial states as well as the edges.
- **void buildNeighbors**: Creates the neighbor lists of each node.
- **void graphInitialization**: Gets used as the initial point for calling **initializeGraph** and passing **InitializeNodes** a config file.
- **void startThreads**: Threads all of the nodes and starts them. 

## GUI and Dislplay
- Gives a visual representation of the graph of nodes and their states as the simulation progresses.
- Uses an animation timer to update the screen to display state changes of the nodes.
- **void neighborLabel**: Creates a label that displays all of the nodes with their neighbors.
- **void makeGraph**: Initializes and draws the circles for the nodes and lines for the edges.
- **void startScene**: Function that is called to put all the elements onto the stage.
- **void updateGUI**: Used to change the color of nodes when their state changes.
