/*
 * Clément Coussin
 * Algo TP3
 * 08/04/2019
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		System.out.println("GRAPH 1");
		GraphAdjList graph1 = new GraphAdjList(5);
		graph1.addEdge(2,3);
		graph1.addEdge(1,3);
		graph1.addEdge(1,4);
		graph1.addEdge(5, 1);
		int nodes = graph1.getNodes();
		int edges = graph1.getEdges();
		System.out.println("There are " + nodes + " nodes and " + edges + " edges.");
		*/
		
		/* TESTS FOR DFS AND BFS ON UNIDRECTED AND UNWEIGHTED GRAPH */
		
		System.out.println();
		System.out.println("## DFS && BFS ##");
		System.out.println();
		String fileName = "graph-DFS-BFS.txt";
		GraphAdjList graph2 = new GraphAdjList(fileName);
		int nodes2 = graph2.getNodes();
		int edges2 = graph2.getEdges();
		System.out.println("There are " + nodes2 + " nodes and " + edges2 + " edges.");
		
		int vertexChecked = 3;
		int degree2 = graph2.Degree(vertexChecked);
		System.out.println("The degree of vertex " + vertexChecked + " is " + degree2);
		//graph2.Neighbors(3);
		
		int minDegree = graph2.minDegree();
		System.out.println("The minimum degree in this graph is " + minDegree);
		
		int maxDegree = graph2.maxDegree();
		System.out.println("The maximum degree in this graph is " + maxDegree);
		
		int avgDegree = graph2.avgDegree();
		System.out.println("The average degree in this graph is " + avgDegree);
		if(avgDegree < 2) {
			System.out.println("This graph is sparse");
		}
		else if(avgDegree < 3) {
			System.out.println("This graph is rather dense");
		}
		else {
			System.out.println("This graph is very dense");
		}
		
		int startingNode = 5;
		ArrayList<Integer> dfsPath = GraphSearch.DFS(graph2,startingNode);
		System.out.println("With the dfs algorythm, starting at the node " + startingNode + " the nodes are visited in the order : ");
		System.out.println(Arrays.toString(dfsPath.toArray()));
		
		ArrayList<Integer> bfsPath = GraphSearch.BFS(graph2,startingNode);
		System.out.println("With the bfs algorythm, starting at the node " + startingNode + " the nodes are visited in the order : ");
		System.out.println(Arrays.toString(bfsPath.toArray()));

		boolean connection = GraphSearch.isConnected(graph2,startingNode);
		System.out.println(connection);
		
		/*
		ArrayList<Integer> degrees2 = graph2.getDegrees();
		System.out.println("The degrees for the respective nodes are : ");
		for(int u : degrees2) {
			System.out.println(u + " ");
		}
		*/
		
		/* TESTS FOR DIRECTED GRAPH */
		
		System.out.println("");
		System.out.println("## Directed graph ##");
		
		int digraphStartingNode = 1;
		int testNode = 7;
		DigraphAdjList digraph = new DigraphAdjList(fileName);
		BFSShortestsPaths graphBFS1= new BFSShortestsPaths();
		graphBFS1.BFS(digraph, digraphStartingNode);
		System.out.println("There is a path to node " + testNode + " : " + graphBFS1.hasPathTo(testNode));
		System.out.println("The distance to node " + testNode + " is " + graphBFS1.distTo(testNode));
		graphBFS1.printSP(testNode);
		
		// Calculates excentricities
		int diameter = 0;
		int radius = digraph.getNodes();
		int[] excentricities = new int[digraph.getNodes()];
		for(int i = 0; i<digraph.getNodes(); i++) {
			BFSShortestsPaths graphBFS = new BFSShortestsPaths();
			graphBFS.BFS(digraph, i+1);
			int currentExcentricity = 0;
			for(int j = 0; j<digraph.getNodes(); j++) {
				if(graphBFS.distTo(j+1)>currentExcentricity) {
					currentExcentricity = graphBFS.distTo(j+1);
				}
			}
			excentricities[i] = currentExcentricity;
			if(currentExcentricity>diameter) {
				diameter = currentExcentricity;
			}
			if(currentExcentricity<radius) {
				radius = currentExcentricity;
			}
		}
		System.out.println("The diameter of the graph is : " + diameter);
		System.out.println("The radius of the graph is : " + radius);
		
		/*
		 * TESTS FOR WEIGHTED AND DIRECTED GRAPH
		 */
		
		System.out.println();
		System.out.println("## Weighted digraph ##");
				
		String WDgraphFilename = "graph-WDG.txt";
		WDgraph wgGraph = new WDgraph(WDgraphFilename);
		int WDstartingNode = 1;
		DijkstraSP graphDSP = new DijkstraSP(wgGraph,WDstartingNode);
		System.out.println("Is the graph usable ? (no negatives) " + graphDSP.verifyNonNegative(wgGraph));
		System.out.println("Is there a path to node 5 ? " + graphDSP.hasPathTo(5));
		System.out.println("What is the weight of the path ? " + graphDSP.distTo(5));
		for(int i=0;i<wgGraph.getNodes();i++) {
			graphDSP.printSP(i+1);
		}
		
	}

}
