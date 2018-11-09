# Design and Program Flow

- The program starts with a node being set "On Fire." 
- The Base Station creates an agent that traverses the graph of nodes until it reaches yellow ones. 
- Once it does, it gets duplicated to the others. 
- This process continues until the base station is destroyed.

<img src="https://i.postimg.cc/NFzRCpYx/Messaging-Diagram.png" alt="drawing" width="700"/>

All nodes run on their own threads, and so do agents.
The Base Station inherits from node and has some extra functionality.

### Node Class Structure
- The Node class runs on its own thread, and is always in one of three states: **Fire**, **Danger**, and **Safe**.
- Each Node holds a list of neighbors that it can send messages to and query for information

<img src=https://i.postimg.cc/L5YTznZ5/Node-Diagram.png alt="drawing" width="600"/>


##### Methods
- **void setStartState**: Sets the state that this node should switch to once it's thread is started.
- **State getStartState**: Gets the state that the node will switch to once its thread is started.
- **boolean addNeighbor(Node)**: Adds the given node as a neighbor. That node does the same.
- **LinkedList<String> getNeighborNames**: Returns a list of names of each neighbor to this node.

### HomeBase Class Structure
- The HomeBase class extends Node with some extra functionality.
- When its thread is started, HomeBase creates an agent that starts randomly traversing nodes until it reaches a fire.
- It holds a HashMap of Agent names corrosponding with node names to keep track of agents that have been created. 

<img src=https://i.postimg.cc/43FJxbjr/HomeBase.png alt="drawing" width="250"/>
