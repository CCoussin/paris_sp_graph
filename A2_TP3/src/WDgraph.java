import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Weighted and directed graphs from basic text file
 */

public class WDgraph {
	
	private int N;  // number of nodes
	private int M;  // number of edges
	private ArrayList<Integer> degrees = new ArrayList<Integer>(); // keep degrees of each node
	private ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>(); // adj list
	
	
	// initialize graph
	public WDgraph(String fileName) {
		try {
			File adjFile = new File(fileName);
			FileReader fr = new FileReader(adjFile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			this.N = 0;
			this.M = 0;
			
			// For each line of the .txt
			while((line = br.readLine()) != null) {
				
				
				String[] splitLine = line.split(" ");
				int firstNode = Integer.parseInt(splitLine[0]);
				int secondNode = Integer.parseInt(splitLine[1]);
				double weight = Double.parseDouble(splitLine[2]);
				
				if(secondNode>this.N) {
					this.N = secondNode;
				}
				if(firstNode>this.N) {
					this.N = firstNode;
				}
				this.edges.add(new DirectedEdge(firstNode, secondNode, weight));
											
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println("Error : " + e);
		}
		calculateDegrees();
	}
	
	// Methods
	
	// Adds a new edge to the graph
	public void addEdge(int u, int v, double weight) { 
		if (u>this.N || v>this.N || u<0 || v<0) {
			System.out.println("Node does not exist.");
		}
		else {
			edges.add(new DirectedEdge(u,v,weight));
		}
		calculateDegrees();
	}
	
	// Gives all neighbors of a given node
	public List<Integer> Neighbors(int v) {
		List<Integer> neighbors = new ArrayList<Integer>();
		
		for(DirectedEdge edge : this.edges) {
			int firstNode = edge.from();
			int secondNode = edge.to();
			if(firstNode != secondNode && firstNode == v) {
				int count = 0;
				for(int i : neighbors) {
					if(i == secondNode) {
						break;
					}
					else {
						count++;
					}
				}
				if (count == neighbors.size()) {
					neighbors.add(secondNode);
				}
			}
		}
		return neighbors;
	}
	
	// Gives each edge departing from the given node
	public ArrayList<DirectedEdge> getNodeEdges(int v){
		ArrayList<DirectedEdge> nodeEdges = new ArrayList<DirectedEdge>();
		for(DirectedEdge edge : edges) {
			if(edge.from() == v) {
				nodeEdges.add(edge);
			}
		}
		return nodeEdges;
	}
	
	
	// Gives the max weight of a single edge in the graph
	public double maxEdgeWeight(){
		double maxWeight = 0;
		for(DirectedEdge edge : edges) {
			if (edge.weight()>maxWeight) {
				maxWeight = edge.weight();
			}
		}
		return maxWeight;
	}
	
	// Degree of a node
	public int Degree(int v) {
		int degree = 0;
		for(DirectedEdge edge : edges) {
			if(edge.from() == v) {
				degree++;
			}
		}	
		return degree;
	}
	
	// Calculates the degrees (used after the creation of the graph or when we add an edge
	public void calculateDegrees() {
		while(degrees.size()<N) {
			degrees.add(0);
		}
		for(int i=0; i<N; i++) {
			degrees.set(i, Degree(i+1));
		}
	}
	
	// Minimum degree of the graph
	public int minDegree() {
		int min = edges.size();
		int currentDegree;
		for(int j=0; j<N; j++) {
			currentDegree = degrees.get(j);
			if(currentDegree < min) {
				min = currentDegree;
			}
		}
		return min;
	}
	
	// Maximum degree of the graph
	public int maxDegree() {
		int max = 0;
		int currentDegree;
		for(int j=0; j<N; j++) {
			currentDegree = degrees.get(j);
			if(currentDegree > max) {
				max = currentDegree;
			}
		}
		return max;
	}
	
	// Average degree of the graph
	public int avgDegree() {
	int avg;
		int sum = 0;
		for(int j=0; j<N; j++) {
			sum += degrees.get(j);
		}
		avg = Math.round(sum/N);
		return avg;
	}
	
	// Getters
	public int getNodes() {
		return N;
	}
	
	public int getEdges() {
		return M;
	}
	
	public ArrayList<Integer> getDegrees(){
		return degrees;
	}
	
	public ArrayList<DirectedEdge> getEdgesList(){
		return edges;
	}
}
