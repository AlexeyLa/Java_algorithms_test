package LeeMaze;

public class Maze {

	static int [] startPoint = new int[]{1,1};
	static int [] endPoint1 = new int[]{6,7};
	static int [] endPoint2 = new int[]{2,5};
	static int [] endPoint3 = new int[]{4,6};
    static int [][] maze = new int[][]{{1, 1, 1, 1, 0, 1, 0, 1},
							           {0, 1, 0, 0, 0, 1, 1, 1},
							           {0, 1, 1, 1, 1, 1, 0, 1},
							           {0, 1, 0, 1, 0, 1, 0, 1},
							           {1, 1, 1, 1, 0, 1, 1, 0},
							           {0, 0, 0, 1, 0, 1, 0, 1},
							           {1, 1, 1, 1, 0, 1, 1, 1}};

	
	
void startWave()
{
	
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] waveMap = new int[maze.length][maze[0].length];
		for (int i=0; i< maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++){
					waveMap[i][j] = 0;
				}
			}
		
		
		System.out.println(maze.length);
		System.out.println(maze[0].length);
		
		for (int i=0; i<8; i++){
		System.out.print(maze[0][i]);
		}
	}

}
