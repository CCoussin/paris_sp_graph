import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class GraphAdjList {

	private int N;  // number of nodes
	private int M;  // number of edges
	private List<List<Integer>> adj = new ArrayList<List<Integer>>(); // adjacency list
	private ArrayList<Integer> degrees = new ArrayList<Integer>(); // keep degrees of each node
	
	
	// Constructors
	
	public GraphAdjList(int n) {
		this.N = n;
		this.M = 0;
		calculateDegrees();
	}
	
	public GraphAdjList(int n, List<List<Integer>> connections) {
		this.N = n;
		this.M = connections.size();
		calculateDegrees();
	}
	
	public GraphAdjList(String fileName) {
		try {
			File adjFile = new File(fileName);
			FileReader fr = new FileReader(adjFile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			this.N = 0;
			this.M = 0;
			while((line = br.readLine()) != null) {
				String[] splitLine = line.split(" ");
				int firstNode = Integer.parseInt(splitLine[0]);
				int secondNode = Integer.parseInt(splitLine[1]);
				
				if(secondNode>this.N) {
					this.N = secondNode;
				}
				if(firstNode>this.N) {
					this.N = firstNode;
				}
				
				this.addEdge(firstNode, secondNode);
											
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println("Error : " + e);
		}
		calculateDegrees();
	}
	
	//Methods
	
	public void addEdge(int u, int v) { 
		if (u>this.N || v>this.N || u<0 || v<0) {
			System.out.println("Node does not exist.");
		}
		else {
			List<Integer> newLink = new ArrayList<Integer>();
			newLink.add(u);
			newLink.add(v);
			this.adj.add(newLink);
			this.M++;
		}
		calculateDegrees();
	}
	
	public List<Integer> Neighbors(int v) {
		List<Integer> neighbors = new ArrayList<Integer>();
		
		for(List<Integer> edge : this.adj) {
			int firstNode = edge.get(0);
			int secondNode = edge.get(1);
			if(firstNode != secondNode && (firstNode == v || secondNode == v)) {
				int otherNode;
				if(firstNode == v) {
					otherNode = secondNode;
				}
				else {
					otherNode = firstNode;
				}
				int count = 0;
				for(int i : neighbors) {
					if(i == otherNode) {
						break;
					}
					else {
						count++;
					}
				}
				if (count == neighbors.size()) {
					neighbors.add(otherNode);
				}
			}
		}
	    //System.out.print("The neighbors of the vertex " + v + " are the vertices : ");
		for(int u : neighbors) {
			//System.out.print(u + " ");
		}
		//System.out.println();
		return neighbors;
	}
	
	public int Degree(int v) {
		int degree = 0;
		for(List<Integer> edge : adj) {
			for(int node : edge) {
				if(node == v) {
					degree++;
				}
			}
		}	
		return degree;
	}
	
	public void calculateDegrees() {
		while(degrees.size()<N) {
			degrees.add(0);
		}
		for(int i=0; i<N; i++) {
			degrees.set(i, Degree(i+1));
		}
	}
	
	public int minDegree() {
		int min = adj.size();
		int currentDegree;
		for(int j=0; j<N; j++) {
			currentDegree = degrees.get(j);
			if(currentDegree < min) {
				min = currentDegree;
			}
		}
		return min;
	}
	
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
}
