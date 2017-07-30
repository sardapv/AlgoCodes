/**
 * Created by pranav on 13/07/17.
 */
public class Nqueen {
    int N = 8;
    public static void main(String[] args) {
        Nqueen nq = new Nqueen();
        nq.solveNQ();
    }
    boolean solveNQ(){
        int board[][] = new int[N][N];

        if(solveNQUtil(board,0) == false){
            System.out.println("Solution doesn't exist!");
            return false;
        }
        printSolution(board);
        return true;
    }
    boolean solveNQUtil(int board[][], int col){
        //if all queens are placed
        if(col>=N)
            return true;
        //take this column and try every place in this col
        for (int i = 0; i < N ; i++) {
            //check if queen is safe at particular position
            if(isSafeBoard(board, i, col)) {
                board[i][col] = 1;
                //continue for rest of the queens and columns
                if (solveNQUtil(board, col + 1) == true)
                    return true;
                //after this is a condition of backtracking so put the 0 back to the place where 1 was put
                board[i][col] = 0;
            }
        }
        //if no place possible
        return false;
    }
    boolean  isSafeBoard(int board[][], int row, int col){
        //check row on left side(no need to check complete row
        int i = 0,j = 0;
        for (i = 0; i < col ; i++)
            if(board[row][i] == 1)
                return false;
        //check upper diagonal on left side
        for (i = row, j = col; i >=0 && j >=0 ; i--,j--)
            if(board[i][j] == 1)
                return false;
        //check lower diagonal on left side
        for (i = row, j = col; i < N && j >=0 ; i++,j--)
            if(board[i][j] == 1)
                return false;
        //safe?fianlly..hushhh..
        return true;
    }
    void printSolution(int board[][]){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                if(board[i][j] == 1)
                    System.out.printf("X"+"\t");
                else
                    System.out.printf("."+"\t");
            System.out.println();
        }
    }
}
