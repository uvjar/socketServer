import java.util.Arrays;

public class Game {
    int[][] board;
    int BOARD_X_SIZE = 4;
    int BOARD_Y_SIZE = 4;

    public Game () {
        board = new int[BOARD_X_SIZE][BOARD_Y_SIZE];
        for(int[] row: board){              //initialized with all elements 0
            Arrays.fill(row, 0);
        }
    }

    public void resetBoard(){
        for(int[] row: board){              //initialized with all elements 0
        Arrays.fill(row, 0);
    }}

    public boolean setPaint(int x,int y,int id){
        if(board[x][y] != 0)
            return false;
        board[x][y]=id;
        return true;
    }


    public synchronized int[][] getBoard(){
        return board;
    }



}
