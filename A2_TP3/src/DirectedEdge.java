
/*
 * Class used for each edge of a directed and weighted graph
 */

public class DirectedEdge {

	private final int v;
	private final int w;
	private final double weight;
	
	public DirectedEdge(int k, int l, double value) {
		this.v = k;
		this.w = l;
		this.weight = value;
	}
	
	public int from() {
		return v;
	}
	public int to() {
		return w;
	}
	public double weight() {
		return weight;
	}
}
