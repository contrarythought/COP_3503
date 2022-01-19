/* 
    Anthony Thorpe
    COP_3503 Arup Guha
    1/18/2022
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class politics {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] numCandidatesSupporters = reader.readLine().split(" ");
        int nCandidates = Integer.parseInt(numCandidatesSupporters[0]);
        int nSupporters = Integer.parseInt(numCandidatesSupporters[1]);

        while(nCandidates != 0) {
            ArrayList<String> candidates = new ArrayList<String>();
        
            // read in the candidates
            int i;
            for(i = 0; i < nCandidates; i++) {
                String candidate = reader.readLine();
                candidates.add(candidate);
            }

            TreeMap<Supporter, String> supporters = new TreeMap<Supporter, String>();
            // read in supporter/candidate pairs
            for(i = 0; i < nSupporters; i++) {
                String[] supporterCandidate = reader.readLine().split(" ");
                supporters.put(new Supporter(supporterCandidate[0], supporterCandidate[1], i), supporterCandidate[1]);
            }
            
            // read out the supporters who support the listed candidates
            for(i = 0; i < candidates.size(); i++) {
                for(Supporter s : supporters.keySet()) {
                    if(candidates.get(i).equals(supporters.get(s))) {
                        System.out.println(s.getSupporter());
                    } 
                }
            }

            // read in the supporters who support written in candidates
            ArrayList<String> writeInCandidates = new ArrayList<String>();
            ArrayList<Supporter> writeInSupporters = new ArrayList<Supporter>(); 
            for(Supporter s : supporters.keySet()) {
                boolean in = false;
                for(i = 0; i < candidates.size(); i++) {
                    if(supporters.get(s).equals(candidates.get(i)))
                        in = true;
                }
                if(in == false) {
                    if(!writeInCandidates.contains(s.getCandidate())) {
                        writeInCandidates.add(supporters.get(s));
                    }
                    writeInSupporters.add(s);
                }
            }
            
            // read out the supporters who support written in candidates
            int j;
            for(i = 0; i < writeInCandidates.size(); i++) {
                for(j = 0; j < writeInSupporters.size(); j++) {
                    if(writeInSupporters.get(j).getCandidate().equals(writeInCandidates.get(i))) {
                        System.out.println(writeInSupporters.get(j).getSupporter());
                    }
                } 
            }

            numCandidatesSupporters = reader.readLine().split(" ");
            nCandidates = Integer.parseInt(numCandidatesSupporters[0]);
            nSupporters = Integer.parseInt(numCandidatesSupporters[1]);

        }
    }
}

class Supporter implements Comparable<Supporter> {
    private String supporter;
    private String candidate;
    private int ID;

    public Supporter(String supporter, String candidate, int ID) {
        this.supporter = supporter;
        this.candidate = candidate;
        this.ID = ID;
    }

    public int compareTo(Supporter other) {
        return this.ID - other.ID;
    }

    public String getSupporter() {return supporter;}
    public String getCandidate() {return candidate;}
    public int getID() {return ID;}
    public String toString() {return supporter;}
}
