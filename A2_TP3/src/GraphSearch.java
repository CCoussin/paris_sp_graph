import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class GraphSearch {

	// DFS algorythme
	public static ArrayList<Integer> DFS(GraphAdjList graph, int node) {
		ArrayList<Integer> visitedNodes = new ArrayList<Integer>(); // We remember the visited nodes in the specific order
		ArrayList<Integer> currentPath = new ArrayList<Integer>(); // We remember the path so we can go back
		List<Integer> currentNeighbors = new ArrayList<Integer>(); // Current neighbors so we can check them
		int currentNode = node;
		visitedNodes.add(currentNode);
		currentPath.add(currentNode);

		boolean allVisited = false;
		
		// While we still have nodes to visit in the connected part of the graph
		while(!allVisited && visitedNodes.size()<graph.getNodes()) {
			currentNeighbors = graph.Neighbors(currentNode);
			int minNode = graph.getNodes()+1;
			for(int vertex : currentNeighbors) {
				boolean ignoreNode = false;
				for(int visited : visitedNodes) {
					if(vertex == visited) {
						ignoreNode = true;
					}
				}
				if(!ignoreNode && vertex<minNode) {
					minNode = vertex;
				}
			}
			if(minNode<graph.getNodes()+1) {
				currentNode = minNode;
				visitedNodes.add(currentNode);
				currentPath.add(currentNode);
			}
			else if(currentPath.size() == 1) {
				allVisited = true;
			}
			else {
				currentPath.remove(currentPath.size()-1);
				currentNode = currentPath.get(currentPath.size()-1);
			}
		}
		
		return visitedNodes;
	}
	
	// Give the number of connected nodes
	public static int cc(GraphAdjList graph, int node) {
		ArrayList<Integer> results = DFS(graph,node);
		return results.size();
	}
	
	
	// Checks if all nodes are connected
	public static boolean isConnected(GraphAdjList graph, int node) {
		if(cc(graph,node)<graph.getNodes()) {
			return false;
		}
		return true;
	}
	
	
	// BFS algorythm
	public static ArrayList<Integer> BFS(GraphAdjList graph, int node) {
		ArrayList<Integer> visitedNodes = new ArrayList<Integer>(); // We remember the visited nodes in the specific order
		ArrayList<Integer> currentLevel = new ArrayList<Integer>(); // Current level we're at
		ArrayList<Integer> nextLevel = new ArrayList<Integer>(); // Next level of nodes
		visitedNodes.add(node);
		currentLevel.add(node);
		List<Integer> neighbors = new ArrayList<Integer>();
		boolean newNodes = true;
		// While there still are nodes to visit
		while(newNodes) {
			for(int c : currentLevel) {
				neighbors = graph.Neighbors(c);
				for(int n : neighbors) {
					if(!nextLevel.contains(n) && !visitedNodes.contains(n)) {
						nextLevel.add(n);
					}
				}		
			}
			if(nextLevel.size() == 0) {
				newNodes = false;
			}
			Collections.sort(nextLevel);
			visitedNodes.addAll(nextLevel);
			currentLevel.removeAll(currentLevel);
			currentLevel.addAll(nextLevel);
			nextLevel.removeAll(nextLevel);
		}
		
		return visitedNodes;
	}
}
