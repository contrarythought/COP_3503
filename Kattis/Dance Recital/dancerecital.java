import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class dancerecital {
    public static PriorityQueue<Integer> pQueue;
    public static boolean[] used;
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
        used = new boolean[numRoutines];
        findMin(routines, new String[numRoutines], numRoutines, 0, 0);
        int result = pQueue.poll();
    }

    public static void findMin(ArrayList<ArrayList<String>> routines, String[] routPerm, int numRoutines, int k, int count) {
        if(k == numRoutines) {
            pQueue.add()
        }
        for(int i = 0; i < numRoutines; i++) {
            if(used[k]) continue;
            
            if(k > 0 && routPerm[k - 1] != "") {
                int z;
                for(z = 0; z < Integer.max(routPerm[k - 1].length(), routines.get(k).size()); z++) {

                }
            }
            for(int j = 0; j < routines.get(i).size(); j++) {
                routPerm[j] = routines.get(i).get(j);
            }

            used[k] = true;

        }
    }
}
