package dfs;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph theGraph = new Graph();
		theGraph.addVertex('A');
		theGraph.addVertex('B');
		theGraph.addVertex('C');
		theGraph.addVertex('D');
		theGraph.addVertex('E');
		
		theGraph.addEdge(0,1);   // AB
		theGraph.addEdge(1,2);   // BC
		theGraph.addEdge(0,3);   // AD
		theGraph.addEdge(3,4);   // DE
		
		System.out.print("Visits: ");
		theGraph.dfs();
		System.out.println();
		
		System.out.print("Visits: ");
		theGraph.bfs();
		System.out.println();
		
		System.out.print("Minimum spanning tree: ");
		theGraph.mst();
		System.out.println();
		
		System.out.print("Topological sorting: ");
		theGraph.topo();
		System.out.println();
		
		
	}

}
