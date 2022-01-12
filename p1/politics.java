import java.util.ArrayList;
import java.util.Scanner;

public class politics {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int nCandidates = stdin.nextInt();
        int nSupporters = stdin.nextInt();

        while(nCandidates != 0) {

            ArrayList<String> candidates = new ArrayList<String>();
            
            // read in the candidate names
            int i;
            for(i = 0; i < nCandidates; i++) {

                candidates.add(stdin.next());

            }

            // read in supporters and the candidates they support
            ArrayList<String[]> supporters = new ArrayList<String[]>();
            System.out.println("nSupr: " + nSupporters);
            for(i = 0; i < nSupporters; i++) {

                String[] SupCan = new String[2];
                SupCan[0] = stdin.next(); // read in supporter
                SupCan[1] = stdin.next(); // read in candidate 

                supporters.add(SupCan);

            }

            nCandidates = stdin.nextInt();
            nSupporters = stdin.nextInt();

        }

        stdin.close();
    }
}
