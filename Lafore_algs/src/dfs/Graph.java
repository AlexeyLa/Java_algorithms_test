package dfs;

class StackX{
	
	private final int SIZE = 20;
	private int top;
	private int [] st;
	
	StackX(){
		top = -1;
		st = new int[SIZE];
	}
	
	public void push(int j){
		st[++top] = j;
	}
	public int pop(){
		return st[top--];
	}
	public int peek(){
		return st[top];
	}
	public boolean isEmpty(){
		return (top == -1);
	}	
}

class Queue {
	
	private final int SIZE = 20;
	private int[] queArray;
	private int front;
	private int rear;
	
	public Queue(){
		queArray = new int[SIZE];
		front = 0;
		rear = -1;
	}
	public void insert(int j){
		if (rear == SIZE - 1)
			rear = -1;
		queArray[++rear] = j;
	}
	public int remove(){
		int temp = queArray[front++];
		if (front == SIZE)
			front = 0;
		return temp;
	}
	public boolean isEmpty(){
		return (rear + 1 == front || (front + SIZE - 1 == rear));
	}
	
}


class Vertex {
	
	public char label;
	public boolean wasVisited;
	
	Vertex(char lab){
		label = lab;
		wasVisited = false;
	}
}

public class Graph {
	private final int MAX_VERTS = 20;
	private Vertex[] vertexList;
	private int[][] adjMat;
	private int nVerts;
	private StackX theStack;
	private Queue theQueue;
	private char[] sortedArray;
	
	public Graph(){
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int i = 0; i< MAX_VERTS; i++)
			for (int j = 0; j < MAX_VERTS; j++)
				adjMat[i][j] = 0;
		theStack = new StackX();
		theQueue = new Queue();
		sortedArray = new char[MAX_VERTS];
	}
	
	public void addVertex(char lab){
		vertexList[nVerts++] = new Vertex(lab);
	}
	public void addEdge(int start, int end){
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	public void displayVertex(int v){
		System.out.print(vertexList[v].label);
	}
	public void dfs(){ // ОБХОД В ГЛУБИНУ
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theStack.push(0);
		
		while (!theStack.isEmpty()){
			int v = getAdjUnvisitedVertex(theStack.peek());
			if (v == -1) theStack.pop();
			else {
				vertexList[v].wasVisited = true;
				displayVertex(v);
				theStack.push(v);
			}
		}
		for (int j = 0; j <nVerts; j++)
			vertexList[j].wasVisited = false;
	}
	
	public void bfs(){
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theQueue.insert(0);
		int v2;
		while (!theQueue.isEmpty()){
			int v1 = theQueue.remove();
			
			while ((v2 = getAdjUnvisitedVertex(v1))!=-1){
				vertexList[v2].wasVisited = true;
				displayVertex(v2);
				theQueue.insert(v2);
			}
		}
		for (int j=0; j<nVerts; j++)
			vertexList[j].wasVisited = false;
	}
	public void mst() {// Minimum Spanning Tree
		vertexList[0].wasVisited = true;
		theStack.push(0);
		while (!theStack.isEmpty()){
			int currentVertex = theStack.peek();
			int v = getAdjUnvisitedVertex(currentVertex);
			if(v == -1)
				theStack.pop();
			else {
				vertexList[v].wasVisited = true;
				theStack.push(v);
				displayVertex(currentVertex);
				displayVertex(v);
				System.out.print(" ");
			}
		}
		
		for(int j = 0; j<nVerts; j++)
			vertexList[j].wasVisited = false;
	}
	
	public void topo(){
		int orig_nVerts = nVerts; // Сохранение количества вершин
		
		while(nVerts > 0) {
			int currentVertex = noSuccessors(); // получение вершины без преемников
			if (currentVertex == -1) {
				System.out.println("ERROR: Graph has cycles");
				return;
			}
			// вставка метки вершины в массив начиная с конца
			sortedArray[nVerts - 1] = vertexList[currentVertex].label;
			deleteVertex(currentVertex); // удаление вершины
		}
		
		System.out.print("Topologically sorted order: ");
		for (int j=0; j <orig_nVerts; j++)
			System.out.print(sortedArray[j]);
		System.out.println("  ");
	}
	
	
	private void deleteVertex(int delVert) {
		// TODO Auto-generated method stub
		if (delVert != nVerts - 1){   // вершина не последняя
									  // удаление из vertexList
			for (int j = delVert; j <nVerts - 1; j++)
				vertexList[j] = vertexList[j+1];
									  // удаление строки из adjMat	
			for (int row = delVert;row<nVerts; row++)
				moveRowUp(row, nVerts);
									  // удаление столбца из adjMat
			for (int col = delVert; col<nVerts; col++)
				moveColLeft(col, nVerts - 1);
		}
	nVerts--;
	}	

	private void moveColLeft(int col, int length) {
		// TODO Auto-generated method stub
		for(int row = 0; row<length; row++)
			adjMat[row][col] = adjMat[row + 1][col];
	}

	private void moveRowUp(int row, int length) {
		// TODO Auto-generated method stub
		for (int col = 0; col<length; col++)
			adjMat[row][col] = adjMat[row][col + 1];
	}

	private int noSuccessors() { // Метод возвращает вершину, не имеющую преемников или -1 если ее нет
		// TODO Auto-generated method stub
		boolean isEdge;    // ребро в матрица adjMat ведущее от row в column
		for (int row = 0; row < nVerts; row++) {// для каждой вершины
			isEdge = false;
			for (int col = 0; col<nVerts; col++){
				if (adjMat[row][col]>0){
					isEdge = true;
					break;
				}
			}
			if (!isEdge)
				return row;
		}
		return -1;
	}

	private int getAdjUnvisitedVertex(int v) {   // Метод возвращает непомещенную вершину, смежную по отношению к v
		// TODO Auto-generated method stub
		for (int j = 0; j<nVerts; j++)
			if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
				return j;    // возвращает первую найденную вершину
		return -1;           // таких вершин нет
	}
}
