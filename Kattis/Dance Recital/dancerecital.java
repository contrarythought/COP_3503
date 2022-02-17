import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dancerecital {
    public static boolean[] used;
    public static String[] routinePerms;
    public static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int numRoutines = Integer.parseInt(reader.readLine());

        String[] routines = new String[numRoutines];
        String input;
        for(int i = 0; i < numRoutines; i++) {
            input = reader.readLine();
            routines[i] = input;
        }
        
        used = new boolean[numRoutines];
        routinePerms = new String[numRoutines];
        System.out.println(findMin(routines, numRoutines, 0, numRoutines - 1, 0));
    }

    public static int findMin(String[] routines, int numRoutines, int start, int end, int count) {
        if(start == numRoutines) {
            return count;
        }

        // stores count from prev function call, so that i don't have to call countAdj() again to decrement count popping from stack
        int tmpCount = 0;
        for(int i = 0; i < numRoutines; i++) {
            if(used[i]) continue;
            routinePerms[start] = routines[i];
            used[i] = true;
            if(start > 0) tmpCount = countAdjacent(routinePerms, start - 1, start);
            count += tmpCount; 

            // finds the minimum between MAX_INT and a final permutation
            min = Math.min(min, findMin(routines, numRoutines, start + 1, end, count));
            
            used[i] = false;
            if(start > 0) count -= tmpCount; 
        }

        return min;
    }

    // counts number of repetitions between two adjacent routines
    public static int countAdjacent(String[] routines, int before, int current) {
        int result = 0;
        for(int i = 0; i < routines[before].length(); i++) {
            if(routines[current].indexOf(routines[before].charAt(i)) != -1) result++;
        }

        return result;
    }
}
