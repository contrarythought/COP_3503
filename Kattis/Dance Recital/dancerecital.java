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
        
        /*
        for(int i = 0; i < routines.length; i++) {
            System.out.println(routines[i]);
        }
        */
        pQueue = new PriorityQueue<Integer>();
        findMin(routines, numRoutines, 0, routines.length - 1, 0);
        int result = pQueue.poll();
        System.out.println(result);
    }

    /*
    public static int countAdj(String[] routines) {
        HashSet<Character> set; 
        int total = 0;
        for(int i = 1; i < routines.length; i++) {
            set = new HashSet<Character>();
            for(int j = 0; j < routines[i - 1].length(); j++) {
                set.add(routines[i - 1].charAt(j));
            }
            for(int k = 0; k < routines[i].length(); k++) {
                if(set.contains(routines[i].charAt(k))) total++;
            }
        }
        return total;
    }
    */

    public static void findMin(String[] routines, int numRoutines, int start, int end, int count) {
        if(start == end) {
            System.out.println("\t*****ADDING " + count + " TO PQ*****");
            System.out.print("\t"); 
            for(int i = 0; i < routines.length; i++) {
                System.out.print(routines[i] + " ");
            }
            System.out.println();
            
            
            
            pQueue.add(count);
            return;
        }        

        int tmpCount = 0;
        for(int i = start; i <= end; i++) {
            if(start > 0) tmpCount = countAdjacent(routines, start, start - 1);
            swap(routines, start, i);
            //if(start > 0) tmpCount = countAdjacent(routines, start, start - 1);
            findMin(routines, numRoutines, start + 1, end, count + tmpCount);
            swap(routines, start, i); // i need this swap to restore original state of routines because the stack does NOT save the state of routines (changes in routines
            // are kept throughout the pushing and popping of stack frames)
        }
    }

    public static int countAdjacent(String[] routines, int before, int current) {
        HashSet<Character> setBefore = new HashSet<Character>();
        
        System.out.println("Comparing " + routines[before] + " and " + routines[current]);

        int result = 0;
        for(int i = 0; i < routines[before].length(); i++) {
            setBefore.add(routines[before].charAt(i));
        }

        for(int i = 0; i < routines[current].length(); i++) {
            if(setBefore.contains(routines[current].charAt(i))) {
                result++;        
            }
        }
        
        System.out.println(result + " in common");
        return result;
    }

    public static void swap(String[] routines, int start, int i) {
        String tmp;
        tmp = routines[i];
        routines[i] = routines[start];
        routines[start] = tmp;
    }
}
