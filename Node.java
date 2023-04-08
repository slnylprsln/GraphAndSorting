import java.util.*;

public class Node {
	public int value;
	public ArrayList<Integer> adjacents = new ArrayList<Integer>();
	
    public Node(int value)  {
    	this.value = value;
    }
}
