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

    public void setPaint(int x,int y,int id){
        board[x][y]=id;
    }
    public synchronized int[][] getBoard(){
        return board;
    }



}
