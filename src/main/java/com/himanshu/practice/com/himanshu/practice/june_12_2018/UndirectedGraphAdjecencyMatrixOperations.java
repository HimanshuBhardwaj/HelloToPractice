package com.himanshu.practice.com.himanshu.practice.june_12_2018;


public class UndirectedGraphAdjecencyMatrixOperations {
    static int adjMatrix[][] = {{0, 1, 0, 0, 1},

            {
                    0, 0, 1, 0, 0
            },

            {
                    0, 0, 0, 1, 0
            },

            {
                    0, 1, 0, 0, 0
            },

            {
                    0, 0, 0, 1, 0
            }
    };
    static int size;

    public static void main(String[] args) {
        //System.out.println("Enter number of nodes");
        //Scanner sc=new Scanner(System.in);
        //if(sc.hasNext()){
        //size=Integer.parseInt(sc.next());
        //}
        size = 5;
        //adjMatrix=new int[size][size];

//        System.out.println("Enter adjecency matrix values");

//        for(int i=0;i<size;i++){
//        for(int j=0;j<size; j++){
//        adjMatrix[i][j]=Integer.parseInt(sc.next());
//        }
//        }
//
        System.out.println("Adjecency matrix values");


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Going to do dfs: ");
        Graph.dfs(adjMatrix, 0, new int[size]);
        System.out.println();
        System.out.println("hasCycle: "+Graph.hasCycle(adjMatrix, 0, new int[size]));
    }
}


class Graph {
    static boolean dfs(int adjMatrix[][], int root, int visited[]) {
        if (visited[root] > 0) {
            return true;
        } else {
            //could be made boolean also
            visited[root] = 1;
        }


        System.out.print(root+ "-->");

        for (int i = 0; i < adjMatrix[root].length; i++) {
            if (adjMatrix[root][i] == 1 && visited[i] == 0) {
                dfs(adjMatrix, i, visited);
            }
        }
        return true;
    }


    static boolean hasCycle(int adjMatrix[][], int root, int visited[]) {
        boolean result = false;
        if (visited[root] == 1) {
            return true;
        } else if (visited[root] == 2) {
            return false;
        }
        visited[root] = 1;

        for(int i=0;i<adjMatrix[root].length;i++) {
            if(adjMatrix[root][i]>0) {
                result = result | hasCycle(adjMatrix, i, visited);
            }
        }

        visited[root] = 2;
        return result;
    }

}
