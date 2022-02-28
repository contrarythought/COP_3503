/*
	Anthony Thorpe
	COP 3503
	Programming assignment #3 - destroying connectivity
	2/20/2022
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class destroy {
    public static long totalConnections;

    public destroy() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numComputers = 0, numConnections = 0, numToDestroy = 0;

        String[] input = reader.readLine().split(" ");
        if(input.length == 3) {
            numComputers = Integer.parseInt(input[0]);
            numConnections = Integer.parseInt(input[1]);
            numToDestroy = Integer.parseInt(input[2]);
        } 

        // array to store all connections to build djsets from
        int[][] connArray = new int[numConnections][2]; 

        // build the connections (aka sets)
        for(int i = 0; i < numConnections; i++) {
            int comp1, comp2;
            input = reader.readLine().split(" ");
            comp1 = Integer.parseInt(input[0]);
            comp2 = Integer.parseInt(input[1]);
            
            connArray[i][0] = comp1;
            connArray[i][1] = comp2;
        }

        HashMap<Integer, Integer> destroyed = new HashMap<Integer, Integer>();
        ArrayList<Integer> destroyIndex = new ArrayList<Integer>();
        
        // connections to destroy
        for(int i = 0; i < numToDestroy; i++) {
            int connToDes;
            input = reader.readLine().split(" ");
            connToDes = Integer.parseInt(input[0]);
        
            destroyed.put(connToDes - 1, connArray[connToDes - 1][0]);
            destroyIndex.add(connToDes - 1);
            
            // mark the connection that needs to be cut 
            connArray[connToDes - 1][0] = -1;
        }

        // re-open starting with all connections destroyed
        djset set = new djset(numComputers);
        long[] results = new long[numToDestroy + 1];

        // build initial with all closed
        for(int i = 0; i < connArray.length; i++) {
            if(connArray[i][0] != -1) {
                set.union(connArray[i][0], connArray[i][1]);
            }
        }

        // store first connectivity score
        results[0] = set.countConnections();
        totalConnections = results[0];

        // re-open
        for(int i = 0; i < numToDestroy; i++) {
            set.union(destroyed.get(destroyIndex.get(destroyIndex.size() - (i + 1))), connArray[destroyIndex.get(destroyIndex.size() - (i + 1))][1]);
            totalConnections = set.calcConnection(totalConnections);
            results[i + 1] = totalConnections;
        }

        for(int i = results.length - 1; i >= 0; i--) {
            System.out.println(results[i]);
        }
        
        
    }
    public static void main(String[] args) throws IOException {
        //Long currTime = System.currentTimeMillis();
        destroy des = new destroy();        
        //Long finalTiem = System.currentTimeMillis();
        //System.out.println(finalTiem - currTime);
    }
}


class Pair {
    private int parent;
    private long connections;

    public Pair(int parent) {
        this.parent = parent;
        this.connections = 1;
    }

    public int getParent() {return parent;}
    public long getConnections() {return connections;} 
    public void setParent(int parent) {this.parent = parent;}
    public void setConnections(long newConnections) {this.connections += newConnections;}
}


class djset {
    private Pair[] parents;
    private int numSets;
    private long oldSizeChild1;
    private long oldSizeChild2;
    private long newConn;

    public djset(int numComputers) {
        parents = new Pair[numComputers + 1];
        numSets = numComputers;

        int i;
        for(i = 0; i < numComputers + 1; i++) {
            parents[i] = new Pair(i);
        }
    }

    public int findRoot(int child) {
        if(parents[child].getParent() == child)
            return child;
        
        int root = findRoot(parents[child].getParent());
        
        // compress path
        parents[child].setParent(root);

        return root;
    }

    public boolean union(int child1, int child2) {
        int root1 = findRoot(child1);
        int root2 = findRoot(child2);

        oldSizeChild1 = parents[root1].getConnections() * parents[root1].getConnections();
        oldSizeChild2 = parents[root2].getConnections() * parents[root2].getConnections();

        

        if(root1 == root2) {
            newConn = parents[root1].getConnections() * parents[root1].getConnections();
            return false;
        }

        parents[root2].setParent(root1); 
        parents[root1].setConnections(parents[root2].getConnections());
        newConn = parents[root1].getConnections() * parents[root1].getConnections();
        numSets--;

        return true;
    }

    public long calcConnection(long prevConnection) {
        if(oldSizeChild1 == newConn) {
            return prevConnection - oldSizeChild1 + newConn;
        } else {
            if(oldSizeChild1 > oldSizeChild2) {
                return prevConnection - oldSizeChild1 - oldSizeChild2 + newConn;
            } else {
                return prevConnection - oldSizeChild2 - oldSizeChild1 + newConn;
            }
        }
    }

    // counts connections of each set
    public long countConnections() {
        boolean[] used = new boolean[parents.length];
        long connections = 0;
        int setCount = numSets;

        for(int i = 1; i < parents.length && setCount > 0; i++) {
            if(!used[parents[i].getParent()]) {
                
                if(parents[i].getParent() == i) {
                    connections += (parents[i].getConnections() * parents[i].getConnections());
                    used[parents[i].getParent()] = true;
                    setCount--;
                }
            }
        }
        
        return connections;
    }

    public void printTree() {
        for(int i = 0; i < parents.length; i++) {
            System.out.print(parents[i].getParent() + " ");
        }
        System.out.println();
    }
}
