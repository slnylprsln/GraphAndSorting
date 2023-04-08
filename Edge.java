import java.util.*;

public class Edge {
	public int source;
	public int destination; 
	public int weight;
	public List<Node> nodelist = new ArrayList<Node>();
	
    public Edge(int s, int d, int w) {
    	this.source = s;
        this.destination = d;
        this.weight = w;
    }
    
}
