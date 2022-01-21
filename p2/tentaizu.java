import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class tentaizu {
    private String[][] board;
    private final int SIZE = 7;
    private final int NUM_SQUARES = SIZE * SIZE;
    private int numBoards;
    private boolean[] possible;
    
    public static void main(String[] args) throws IOException {
        tentaizu t = new tentaizu();
    }

    // read in and generate the finished tentaizu boards
    public tentaizu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String numB = reader.readLine();
        numBoards = Integer.parseInt(numB);
        possible = new boolean[NUM_SQUARES];
        
        // set all elements in possible to true
        Arrays.fill(possible, true);

        // loop through each board
        for(int i = 0; i < numBoards; i++) {

            board = new String[SIZE][SIZE];

            for(int j = 0; j < SIZE; j++) {

                String[] tmp = reader.readLine().split("");
                board[j] = tmp;

            }

            // TODO - ANNOUNCE BOARD #

            // analyze and generate completed board
            solve(board, 0);               

            // read in the separator
            String sep = reader.readLine();

        }

    }

    // TODO
    public void solve(String[][] board, int k) {
        if(k == NUM_SQUARES) 
            return;

        int i;
        for(i = 0; i < NUM_SQUARES; i++) {

            if(possible[i] && canPlaceBomb(k)) {

                board[k / SIZE][k % SIZE] = "*";
                possible[i] = false;

            }

        }

    }

    // TODO
    public boolean canPlaceBomb(int k) {

        // TODO - PLACEHOLDER, NEED TO CHANGE
        return true;

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
