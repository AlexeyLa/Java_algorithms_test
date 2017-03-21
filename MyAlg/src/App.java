
public class App {
	

	
	public static void main(String[] args){
		
		int [][] adjMatrix = {{0,1,1,0,0},
							  {0,0,1,0,0},
							  {0,0,0,1,0},
							  {0,0,0,0,1},
							  {0,0,0,0,0}};
		
		Alg alg = new Alg(adjMatrix);
		System.out.println("start");
		alg.solveMat(0, 0);
	}
}
