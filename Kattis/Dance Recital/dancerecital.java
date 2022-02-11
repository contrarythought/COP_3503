import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class dancerecital {
    public static PriorityQueue<Integer> pQueue;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int numRoutines = Integer.parseInt(reader.readLine());

        ArrayList<ArrayList<String>> routines = new ArrayList<ArrayList<String>>();
        ArrayList<String> routine;
        String[] input = new String[numRoutines];
        for(int i = 0; i < numRoutines; i++) {
            input = reader.readLine().split("");
            routine = new ArrayList<String>();
            for(int j = 0; j < input.length; j++) {
                routine.add(input[j]);
            }
            routines.add(routine);
        }

        pQueue = new PriorityQueue<Integer>();
        int result = findMin(routines, numRoutines);
    }

    public static int findMin(ArrayList<ArrayList<String>> routines, int numRoutines) {

    }
}
