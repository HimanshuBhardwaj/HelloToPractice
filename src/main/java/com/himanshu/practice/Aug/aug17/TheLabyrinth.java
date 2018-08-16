package com.himanshu.practice.Aug.aug17;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by himanshubhardwaj on 16/08/18.
 * Statement: https://codeforces.com/contest/616/problem/C
 * Algo: DFS
 * Submission: https://codeforces.com/contest/616/submission/41652653
 */
public class TheLabyrinth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph g = new Graph(n, m);
        str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = br.readLine();
        }
        g.insert(str);
        str = g.getString();
        for (int i = 0; i < n; i++) {
            System.out.print(str[i]);
            if(i != (n-1)) {
                System.out.println();
            }
        }

    }
}


class Graph {
    int arr[][];
    int row;
    int column;
    static Map<Integer, ConnectedComponent> connectedComponentSize = new HashMap<>();

    public Graph(int r, int c) {
        this.row = r;
        this.column = c;
        arr = new int[row][column];
    }

    void insert(String[] str) {
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length(); j++) {
                arr[i][j] = (str[i].charAt(j) == '.') ? 0 : -1;
            }
        }
    }

    private void DFS() {
        int connectedCompnentIndex = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] == 0) {
                    ConnectedComponent connectedComponent = new ConnectedComponent();
                    connectedComponent.index = connectedCompnentIndex;
                    DFSC(i, j, connectedComponent);
                    connectedCompnentIndex++;
                    connectedComponentSize.put(connectedComponent.index, connectedComponent);
                }
            }
        }

        //System.out.println(connectedComponentSize);
    }


    public String[] getString() {
        DFS();
        //System.out.println(connectedComponentSize);
        String str[] = new String[row];

        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < column; j++) {
                if (arr[i][j] == -1) {
                    sb.append(mergeConnectedClusters(i, j));
                } else {
                    sb.append('.');
                }

            }
            str[i] = sb.toString();
        }
        return str;
    }

    private char mergeConnectedClusters(int i, int j) {
        HashSet<ConnectedComponent> connectedComponentSet = new HashSet<>();
        getSize(i, j + 1, connectedComponentSet);
        getSize(i, j - 1, connectedComponentSet);
        getSize(i + 1, j, connectedComponentSet);
        getSize(i - 1, j, connectedComponentSet);

        int size = 1;
        for (ConnectedComponent c : connectedComponentSet) {
            size += c.size;
        }
        size = size%10;

        return (char) (size + '0');
    }

    private void getSize(int row, int column, HashSet<ConnectedComponent> connectedComponents) {
        if (row < 0 || row >= this.row || column < 0 || column >= this.column || arr[row][column] == -1) {
            return;
        }
        connectedComponents.add(connectedComponentSize.get(arr[row][column]));
    }

    private void DFSC(int i, int j, ConnectedComponent connectedComponent) {
        if (i < 0 || j < 0 || i >= row || j >= column || arr[i][j] != 0) {
            return;
        }
        arr[i][j] = connectedComponent.index;
        connectedComponent.size++;
        DFSC(i, j + 1, connectedComponent);
        DFSC(i, j - 1, connectedComponent);
        DFSC(i + 1, j, connectedComponent);
        DFSC(i - 1, j, connectedComponent);
    }
}


class ConnectedComponent {
    int index;
    int size;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ConnectedComponent)) return false;
        final ConnectedComponent other = (ConnectedComponent) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.index != other.index) return false;
        if (this.size != other.size) return false;
        return true;
    }

    public int hashCode() {
        int result = this.index;
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConnectedComponent;
    }
}
