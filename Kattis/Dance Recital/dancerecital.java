import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;

public class dancerecital {
    public static PriorityQueue<Integer> pQueue;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int numRoutines = Integer.parseInt(reader.readLine());

        String[] routines = new String[numRoutines];
        String input;
        for(int i = 0; i < numRoutines; i++) {
            input = reader.readLine();
            routines[i] = input;
        }
        
        for(int i = 0; i < routines.length; i++) {
            System.out.println(routines[i]);
        }
        pQueue = new PriorityQueue<Integer>();
        findMin(routines, numRoutines, 0, routines.length - 1, 0);
       // int result = pQueue.poll();
        //System.out.println(result);
    }

    public static void findMin(String[] routines, int numRoutines, int start, int end, int count) {
        if(start == end) {
            for(int i = 0; i < routines.length; i++) {
                System.out.print(routines[i] + " ");
            }
            System.out.println();
            //pQueue.add(count);
            return;
        }        

        int tmpCount = 0;
        for(int i = start; i <= end; i++) {
           // if(start > 0) tmpCount = countAdjacent(routines, start, start - 1);
            swap(routines, start, i);
            findMin(routines, numRoutines, start + 1, end, count + tmpCount);
            swap(routines, start, i);
        }
    }

    public static int countAdjacent(String[] routines, int before, int current) {
        HashSet<Character> setBefore = new HashSet<Character>();
        
        int result = 0;
        for(int i = 0; i < routines[before].length(); i++) {
            setBefore.add(routines[before].charAt(i));
        }

        for(int i = 0; i < routines[current].length(); i++) {
            if(setBefore.contains(routines[current].charAt(i))) result++;
        }
        
        return result;
    }

    public static void swap(String[] routines, int start, int i) {
        String tmp;
        tmp = routines[i];
        routines[i] = routines[start];
        routines[start] = tmp;
    }
}
