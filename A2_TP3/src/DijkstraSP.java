import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Implementation of Dijkstra for weighted and directed graphs (no negative weights)
public class DijkstraSP {

	
	public boolean[] marked;
	public int[] previous;
	public double[] distance;
	
	//Methods
	
	DijkstraSP(WDgraph graph, int node) {

		int currentNode = node;
		ArrayList<Integer> toCheck = new ArrayList<Integer>(); // Nodes that aren't out yet

		int nbNodes = graph.getNodes();
		marked = new boolean[nbNodes];
		previous = new int[nbNodes];
		distance = new double[nbNodes];
		for(int i=0; i<distance.length; i++) {
			distance[i] = -1;
		}
		
		marked[node-1] = true;
		previous[node-1] = 0;
		distance[node-1] = 0;
		toCheck.add(currentNode);
		
		List<Integer> neighbors = new ArrayList<Integer>();
		boolean newNodes = true;
		int markedCounter = 1;
		
		// while there are still nodes to check
		while(newNodes) {
			// We get edges from the current node
			ArrayList<DirectedEdge> currentEdges = graph.getNodeEdges(currentNode);
			// We remember relevant neighbors
			for(int n : graph.Neighbors(currentNode)) {
				if(!marked[n-1] && !toCheck.contains(n)) {
					toCheck.add(n);
				}
			}
			double currentWeight = distance[currentNode-1];
			// For our edges
			for(DirectedEdge checkedEdge : currentEdges) {
				// If the node is neither marked or too "far" for this path :
				if(!marked[checkedEdge.to()-1] && ((checkedEdge.weight()+currentWeight)<distance[checkedEdge.to()-1] || distance[checkedEdge.to()-1]==-1)) {
					distance[checkedEdge.to()-1] = checkedEdge.weight()+currentWeight;
					previous[checkedEdge.to()-1] = checkedEdge.from();
				}
			}
			marked[currentNode-1] = true;
			markedCounter+=1;
			toCheck.remove(new Integer(currentNode));
			
			// Now we got to the shortest path in the graph that has a node that needs checking
			double shortestToCheck = graph.maxEdgeWeight()*graph.getNodes();
			for(int j=0; j<distance.length;j++) {
				if(!marked[j] && distance[j]!=-1 && distance[j]<shortestToCheck) {
					shortestToCheck = distance[j];
					currentNode = j+1;
				}
			}

			if(toCheck.size()==0) {
				newNodes = false;
			}
		}

	}	
	
	// Checks if there are negatives weights
	public boolean verifyNonNegative(WDgraph graph) {
		ArrayList<DirectedEdge> edgesList = graph.getEdgesList();
		for(DirectedEdge edge : edgesList) {
			if(edge.weight()<0) {
				return false;
			}
		}
		return true;
	}
	// Checks if there is a path to the given node
	public boolean hasPathTo(int v) {
		return marked[v-1];
	}
	// Gives the distance to the given node
	public double distTo(int v) {
		return distance[v-1];
	}
	// Prints the shortest path to the given node
	public void printSP(int v) {
		int currentNode = v;
		ArrayList<Integer> pathToNode = new ArrayList<Integer>();
		
		while (distance[currentNode-1] > 0) {
			currentNode = previous[currentNode-1];
			pathToNode.add(0,currentNode);

		}
		System.out.print("The shortest path to node " + v + " is ");
		for(int i : pathToNode) {
			System.out.print(i + " -> ");
		}
		System.out.println(v + " (distance : " + distance[v-1] + ")");
	}
	
}
