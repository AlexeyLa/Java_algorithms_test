
public class Alg {

	private final int MAX_VERTS = 20;
	private final int N_VERTS = 4;
	private boolean[] wasVisited;
	private int[][] adjMatrix;
	private int[] rowCoord;
	private int[] colCoord;
	private int counter = -1;
 	
	public Alg(int [][] adjMatrix){
		this.adjMatrix = adjMatrix;
		wasVisited = new boolean[N_VERTS];
	}
	
	
	public void solveMat(int rowInd, int colInd){
		
		if (adjMatrix[rowInd][colInd] == 0 && colInd == N_VERTS){
			System.out.println("DONE");
			return;
		} else {
			if (adjMatrix[rowInd][colInd] == 0){
				solveMat(rowInd, colInd + 1);
			} else {
				System.out.println("path " + rowInd + " - " + colInd);
				solveMat(colInd, colInd);
			}
		}
	}
}
