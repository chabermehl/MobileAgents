# Design and Program Flow

- The program starts with a node being set "On Fire." 
- The Base Station creates an agent that traverses the graph of nodes until it reaches yellow ones. 
- Once it does, it gets duplicated to the others. 
- This process continues until the base station is destroyed.

<img src="https://i.postimg.cc/NFzRCpYx/Messaging-Diagram.png" alt="drawing" width="700"/>

All nodes run on their own threads, and so do agents.
The Base Station inherits from node and has some extra functionality.

### Node Class
The Node class runs on its own thread, and is always in one of three states: **Fire**, **Danger**, and **Safe**.

<img src=https://i.postimg.cc/L5YTznZ5/Node-Diagram.png alt="drawing" width="600"/>


###### Methods
setStartingState