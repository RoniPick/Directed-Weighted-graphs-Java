# OOP Task 2 - Design and implementation of directed and weighted graphs in Java
## _presented by: Almog David - 207749441 & Roni Pick - 206794075_

## _introduction_:

Before we start to talk about our task, let's talk about graphs.

**Graph** is a structure amounting to a set of objects in which some pairs of the objects are in some sense "related". The objects called vertices (also called nodes or points) and each of the related pairs of vertices is called an edge (also called link or line) Typically, a graph is depicted in diagrammatic form as a set of dots or circles for the vertices, joined by lines or curves for the edges.

**Directed Graph** is a graph in which the edges have orientations. The edge (x,y) directed from x (the tail) to y (the head – the ->).

**Weighted Graph** is a graph in which a number (the weight) is assigned to each edge. Such weights might represent for example costs, lengths or capacities, depending on the problem at hand.

In our task we need to represent a directed and weighted graphs and run on them some graph's algorithms problems in programming (such as tsp, finding the shortest path from one vertex to another, etc..) + a visual design of the graph.

 For more information about graph: [Graph](https://en.wikipedia.org/wiki/Graph_(discrete_mathematics))
 
![phpkbUERY](https://user-images.githubusercontent.com/93771702/145432021-e7ebba8f-6332-42a3-b801-464a6a4c651e.png)

## _The classes_:

- **Geo class:** This class implements the interface of GeoLocation. Geo represents a geo location <x,y,z>, aka Point3D of node in the graph. Each geoLocation has value of X coordinate, value of Y coordinate and value of Z coordinate.

- **Node class:** This class is implements the interface of Nodedata and it designed to create a vertex in the graph. Each node in the graph has a unique key, location- that represents the geo location <x,y,z> of the node in 3D space, weight, tag and info. The last two used to define properties of the node through which it will be possible to check whether the graph is connected and in addition to calculate path weights in the graph.
In every node we created 2 linkedlists of integers: one represents the id of all the nodes that have an edge to this specific node and one represents the id of all the nodes that have an edge from this specific node.

- **Edge class:** This class is implements the interface of Edgedata and it designed to create an edge in the graph. Each edge in the graph has key of src node, key of dest node, weight, tag and info.

- **Graph class:** This class implements the interface of DirectedWeightedGraph. each graph has a counter (counter) that update everytime we chang something in the graph, second counter (itercounter) saves the last iterate MC data in order to compare between the iterates, a 2 collections in the form of hash map: the first (nodes) for keeping the nodes of the graph <integer,NodeData> nodes , the second (edges) for keeping the edges of the graphan. in our edges HashMap we used an HashMap that has an inner hashmap. the first key is the id of the source node. For everry key we create a HashMap that contains the key - the id of the destination node and the value is the Edge from the source to the destination:<integer,Hashmap<integer,Edgedata>>.
The class have functions such as: changes in the graph (add/remove a node/edge), checking if there is an edge between two nodes etc..

 **Hash Map:** In the hash map data structure, each member in the collection has a unique key, in this way i can access the member, add an member or delete an member with an O(1). Hence, I chose this data structure so that graph changes would be made quickly, even when it comes to a graph with lots of vertices.
 
 For more information about graph: [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
 
- **GraphAlgorithms class:** This class implements the interface of DirectedWeightedGraphAlgorithms, which has algorithms that can be run on the graph:
  - **Isconnected():** This algorithm checks whether the graph is connected or not. in order to check if the graph is connected or not we will use the BFS algorithm for every Node in the graph.
   
    For more information about DFS Algorithm: [DFS algorithm](https://en.wikipedia.org/wiki/Depth-first_search) OR [DFS algorithm](https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/)
  - **shortestPathDist():** this algorithm return the length of the shortest path between two vertices. in order to check the shortest path length we will use a veriant of Dijkstra that return an Hashmap of all the nodes that in the graph . the key is the node ID and the value is the length between the source Id (the id we sent to the DijkstraLength algorithm) to this node.
    
    for more information about Dijkstra Algorithm: [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) or watch this video [video](https://www.youtube.com/watch?v=XB4MIexjvY0)
  - **shortestPath():** this algorithm return a list of the nodes we visited in the shortest path between two vertices. in order to check the shortest path length we will use a veriant of Dijkstra that return an Hashmap of all the nodes that we visited in the path . the key is the node ID and the value is the node that we came from to this node. For example: if we went from node A to node B, in the HashMap at the node B id we will add node A because A is his previous.
  - **center():** this algorithm return the node that represent the center of the graph. a center is determined by the minimum length of the group that contains the longest path of every node. in order to find the center we will use a veriant of Dijkstra that return an Hashmap of all the nodes that in the graph (just like in shortestPathLength).
  - **Tsp(list cities):** this algorithm return a list of the shortest path that contains all the cities/nodes that given to us. 
    
    for more information about the TSP problem: [TSP problem](https://newbedev.com/how-to-draw-a-directed-arrow-line-in-java)
  - **copy():** deep copying of a graph.
  - **save()/load():** saving the graph to Json object and loading the graph from Json String.

- **JsonToEdges class:** This class is using to converts an edge_data object to Json String and vice versa.
- **JsonToNodes class:** This class is using to converts an node object to Json String and vice versa.
- **JsonToGraph class:** This class is using to converts an node object to Json String and vice versa.

![Ex2_](https://user-images.githubusercontent.com/93771702/145724966-e119c7c5-aa32-42f7-94f2-22a226ceb63b.png)

## _Results Table:_

| Number of Nodes/ Function |  Isconnected   |  ShortestPath  |   shortestPathDist   |   Center   |      Tsp     |
|---------------------------|----------------|----------------|----------------------|------------|--------------|
|     1,000 Nodes           |  500-700 ms    |  200-250 ms    |     400-700 ms       |      X     |  250-350 ms  |
|     10,000 Nodes          |   45-50 sec    |  600-700 ms    |     40-45 sec        |      X     |     1 sec    |
|     100,000 Nodes         |       X        |     5 sec      |          X           |      X     |     10 sec   |
|     1,000,000 Nodes       |       X        |       X        |          X           |      X     |       X      |

X - more then 5 minuets to run. 

## _How to run:_
In order to run the program, all you need to do is to download the jar file from github. Then, open the terminal/cmd and write the following:

 java -jar Ex2.jar "your json file name"  - you can enter any graph writen in json you would like. 
 
 ## _the GUI_:
 
 <img width="583" alt="1" src="https://user-images.githubusercontent.com/93771702/145841359-dc76ca74-2c71-4d9a-b8ae-e1de8488838a.PNG">

 **_File:_** In the File menu we have the following:
 - **load:** In the load option you can select a json file from your computer in order to run the algorithm functions on it.
 - **save:** In the save options you can save the graph that presented to you after the changes you made.
 
 **_Edit:_** In the Edit menu we have the following:
 - **Add Node:** You can enter a new node to the graph by giving an ID, X and Y values that represent the location fo the node. The location is based on GPS cordinates (x,y,z). Because we are using a 2D graph, the z parameter is allways 0. If the node is already exist - nothing will happend. 
 - **Add Edge:** You can connect between to nodes in the graph (create a new edge in the graph) by giving the source node ID, the destination node ID and the weight of the new edge you just created. If the edge already exist - it will change its weight.   
 - **Remove Node:** You can remove a node from the graph by giving the ID of the node you would like to remove. by removing the node you will also remove all the edges that going to/from this node. If the node doesn't exist - nothing will happend. 
 - **Remove Edge:** You can remove an edge from the graph by giving the source node ID and the destination node ID. if the edge doesn't exist - nothing will happend. 
    
 **_Algorithms_**: In the Algorithms menu we have the following:
 - **Find shortest path:** You can search for the shortest path from one node to another by giving the source node ID and the destination node ID. the function will mark the path from one node to another and will show the weight of every edge in the path.
 - **Find center:** when clicking on the "Find center" button you will get the center of the graph. The center of the graph is the node with the smallest path from all the nodes in the graph.  
 - **Find if connected:** when clicking on the "Find if connected" button you will get an answer if the graph is connected or not. A connected graph is a graph you can reach from every node to all the nodes in the graph. 
 




