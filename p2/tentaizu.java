import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class tentaizu {
    private String[][] board;
    private final int NUM_ROWS = 7;
    private final int NUM_COL = 7;
    private int numBoards;
    public static void main(String[] args) throws IOException{
        tentaizu t = new tentaizu();
    }

    // read in and generate the finished tentaizu boards
    public tentaizu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String numB = reader.readLine();
        numBoards = Integer.parseInt(numB);

        // loop through each board
        for(int i = 0; i < numBoards; i++) {

            board = new String[NUM_ROWS][NUM_COL];

            for(int j = 0; j < NUM_ROWS; j++) {

                String[] tmp = reader.readLine().split("");
                board[j] = tmp;

            }

            // analyze and generate completed board
            solve(board, 0, 0);               

            // read in the separator
            String sep = reader.readLine();

        }

    }
    
    // TODO
    public void solve(String[][] board, int row, int col) {

    }

    public void printBoard() {
        for(int i = 0; i < board.length; i++) {

            for(int j = 0; j < board[i].length; j++) {

                System.out.print(board[i][j]);

            }

            System.out.println();

        }
    }
}
