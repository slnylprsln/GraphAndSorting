import java.util.*;

public class Graph {
	public long size;
    public LinkedList<Edge> [] list;

	@SuppressWarnings("unchecked")
	public Graph(long ver) {
        this.size = ver;
        list = new LinkedList[(int)ver];
        for (int i=0; i<ver; i++) {
            list[i] = new LinkedList<>();
        }
    }
	
    public void addEdge(int source, int destination, int weight) {
    	Node nodeSource = new Node(source);
    	Node nodeDest = new Node(destination);
        Edge edge = new Edge(source, destination, weight);
        edge.nodelist.add(nodeSource);
        edge.nodelist.add(nodeDest);
        list[source].addFirst(edge);
    }

    //Printing out the graph
    public void printGraph(){
        for (int i=0; i<size; i++) {
            for (int j=0; j<list[i].size(); j++) {
                System.out.println("(" + i + "," + list[i].get(j).destination + ")  " + list[i].get(j).weight);
            }
        }
    }
    
    
    //Find adjacents of given ID
    public void askForAdjacents(int search) {
    	for(int i=0; i<list[search].size(); i++) {
			System.out.println("("+search+","+list[search].get(i).destination+")  "+list[search].get(i).weight);
		}
    }

    
    //Return arraylist of adjacents of given ID
    public ArrayList<Integer> adjacentList(int search) {
    	ArrayList<Integer> adj = new ArrayList<Integer>();
    	for(int i=0; i<list[search].size(); i++) {
    		adj.add(list[search].get(i).destination);
    	}
    	return adj;
    }
    
    //Find common friends of two given IDs
    public int findCommon(int first,int second) {
    	int counter = 0;
    	
    	for(int i=0; i<list[first].size(); i++) {
    		for(int j=0; j<list[second].size(); j++) {
    			if(list[first].get(i).destination==list[second].get(j).destination) {
    				counter++;
    			}
    			else continue;
    		}
    	}
    	return counter;
    }
    
}
