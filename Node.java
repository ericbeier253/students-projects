package comp511;

public class Node {
	// values and pointer
	public String key;
	public String value;
	public Node next;

	// Node constructor
	public Node(String key, String value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}//end Node
