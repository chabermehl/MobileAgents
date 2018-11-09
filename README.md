# Project: Fire Agents
## Students:  Alexander Booher, Charles Habermehl

## Introduction
This program runs the "Fire Agent" simulation, where a fire starts on a node in a directed graph
and spreads throughout it.

- A single agent is created at the start and navigates toward nodes that are close to catching fire.
- It clones itself to all neighbors once it reaches a node.
- As the fire spreads, agents keep cloning themselves to try and surround the fire
- The simulation runs until the fire eventually spreads to the base station and destroys it.

## Contributions
- Charles worked on the front-end
- Alex Worked on the backend.

## Usage
Double-click the jar file.

Node Display is the entry point for the GUI.
Main has some test code in it, uncomment for threading or cheching node initialization

## Project Assumptions
Fire spreads evenly based on time. Agents are not bound to a time restraint for movement. 
The config file will have a reasonalbe amount of nodes in it. Less than 50.


## Jar file 
The jar file is located in the root directory of the project.

## Docs
Check out the [documentation](../doc/doc.MD) file for that sweet, sweet info.

## Status
### Implemented Features
GUI displays spread of fire, movement of agents and death of nodes. 
Node list with neighbors placed on right hand side.
Button that starts the simulation placed on bottom of screen. 

### Known Issues
Agents sometimes don't surround the fire properly.
Agent sometimes gets stuck and stops on first node encountered. 
Only able to change config file manually in the source code. 

### Features We Would Improve
Loading multiple config files to be able to pick and chose without changing source
code.
Better GUI/thread updating.