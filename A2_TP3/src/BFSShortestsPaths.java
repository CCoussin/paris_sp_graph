import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BFSShortestsPaths {

	public boolean[] marked;
	public int[] previous;
	public int[] distance;
	
	//Methods
	
	public void BFS(DigraphAdjList graph, int node) {
		ArrayList<Integer> visitedNodes = new ArrayList<Integer>();
		ArrayList<Integer> currentLevel = new ArrayList<Integer>();
		ArrayList<Integer> nextLevel = new ArrayList<Integer>();
		visitedNodes.add(node);
		currentLevel.add(node);
		int nbNodes = graph.getNodes();
		marked = new boolean[nbNodes];
		previous = new int[nbNodes];
		distance = new int[nbNodes];
		marked[node-1] = true;
		previous[node-1] = 0;
		distance[node-1] = 0;
		
		List<Integer> neighbors = new ArrayList<Integer>();
		boolean newNodes = true;
		while(newNodes) {
			for(int c : currentLevel) {
				neighbors = graph.Neighbors(c);
				for(int n : neighbors) {
					if(!nextLevel.contains(n) && !visitedNodes.contains(n)) {
						nextLevel.add(n);
						marked[n-1] = true;
						previous[n-1] = c;
						distance[n-1] = distance[c-1] + 1;
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

	}
	
	public boolean hasPathTo(int v) {
		return marked[v-1];
	}
	
	public int distTo(int v) {
		return distance[v-1];
	}
	
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
		System.out.println(v);
	}
}
