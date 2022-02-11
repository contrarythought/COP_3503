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
            input[i] = reader.readLine();
            routine = new ArrayList<String>();
            routine.add(input[i]);
            routines.add(routine);
        }

        pQueue = new PriorityQueue<Integer>();
        used = new boolean[numRoutines];
        findMin(routines, new String[numRoutines], numRoutines, 0, 0);
        int result = pQueue.poll();
        System.out.println(result);
    }

    public static void findMin(ArrayList<ArrayList<String>> routines, String[] routPerm, int numRoutines, int k, int count) {
        if(k == numRoutines) {
            pQueue.add(count);
            return;
        }
        
        int tmpCount = 0;
        for(int i = 0; i < numRoutines; i++) {
            if(used[i]) continue;
            
            // count duplicate dancers in adjacent routines
            if(k > 0) {
                int z;
                for(z = 0; z < routPerm[k - 1].length(); z++) {
                    for(int j = 0; j < routines.get(k).size(); j++) {
                        if(routPerm[k - 1].charAt(z) == routines.get(k).get(j).charAt(j)) {
                            tmpCount++;
                        }
                    }
                }
            }
            
            // place a routine in the array of routines
            for(int j = 0; j < routines.get(i).size(); j++) {
                routPerm[k] = routines.get(i).get(j);
            }

            used[k] = true;

            findMin(routines, routPerm, numRoutines, k + 1, count + tmpCount);

            // not sure if right
            used[k] = false;

            
        }
    }
}
