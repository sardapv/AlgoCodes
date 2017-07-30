/**
 * Created by pranav on 14/07/17.
 */
public class RatInMaze {
    int maze[][] = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
    };
    int N = 4;

    public static void main(String[] args) {
        RatInMaze rim = new RatInMaze();
        rim.solveMaze(rim.maze);
    }
    void solveMaze(int maze[][]){
        int solution[][] = new int[N][N];
        if (solveUtil(maze,0,0,solution))
            printSolution(solution);
        else {
            System.out.println("Solution doesn't exists!");
        }
    }
    boolean solveUtil(int maze[][],int x, int y, int solution[][]){
        if(x==N-1 && y==N-1) {
            solution[x][y] = 1;
            return true;
        }

        if (isSafe(maze, x , y)) {
            solution[x][y] = 1;
            if (solveUtil(maze, x + 1, y, solution) == true)
                return true;
            if (solveUtil(maze, x, y + 1, solution) == true)
                return true;
            solution[x][y] = 0;
            return false;
        }
            return false;
    }
    boolean isSafe(int maze[][], int x, int y){
        return (x>=0 && x<N && y>=0 && y<N && (maze[x][y] ==1));
    }
    void printSolution(int solution[][]){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (solution[i][j] == 0)
                    System.out.printf("-" + "\t");
                else
                    System.out.printf("X" + "\t");
            }
            System.out.println();
        }
    }
}
