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
            ArrayList<Supporter> supporters = new ArrayList<Supporter>();
            System.out.println("nSupr: " + nSupporters);
            for(i = 0; i < nSupporters; i++) {

                String name = stdin.next();
                String candidate = stdin.next();
                supporters.add(new Supporter(name, candidate));

            }

            ArrayList<Supporter> writeIn = new ArrayList<Supporter>();
            int j, k;
            for(i = 0; i < nCandidates; i++) {
                
                for(j = 0; j < nSupporters; j++) {
                    
                    boolean hasCandidate = false;
                    for(k = 0; k < nCandidates; k++) {

                        if(supporters.get(j).getCandidate().equals(candidates.get(k))) {

                            hasCandidate = true;
                            break;

                        }

                    }

                    if(hasCandidate == false) {

                        writeIn.add(supporters.get(j));

                    } else if(supporters.get(j).getCandidate().equals(candidates.get(i))) {

                        System.out.println(supporters.get(j).getName());

                    }

                }

            }

            nCandidates = stdin.nextInt();
            nSupporters = stdin.nextInt();

        }

        stdin.close();
    }
}

class Supporter {
    
    private String name;
    private String candidate;

    public Supporter(String name, String candidate) {
        this.name = name;
        this.candidate = candidate;
    }

    public String getCandidate() {return candidate;}
    public String getName() {return name;}

}
