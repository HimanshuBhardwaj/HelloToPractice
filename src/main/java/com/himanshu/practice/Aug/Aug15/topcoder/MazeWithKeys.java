package com.himanshu.practice.Aug.Aug15.topcoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 15/08/18.
 * this is heavy light decomposition
 * TODO: Finish it
 * Statement: https://arena.topcoder.com/#/u/practiceCode/17244/65412/14944/2/331608
 */
public class MazeWithKeys {
    int startingRow;
    int startingColumn;
    int arr[][];
    String[] a;

    public static void main(String[] args) {

    }

    public int startingPoints(String[] a) {
        setStartingPos(a);
        ArrayList<ConnectedComponent> connectedComponents = new ArrayList<>();
        int clusternumber = 1;
        arr = new int[a.length][a[0].length()];
        this.a = a;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length(); j++) {
                if ((a[i].charAt(j) == '.' || ((a[i].charAt(j) - 'a') >= 0 && (a[i].charAt(j) - 'a' <= 25))) && (arr[i][j] == 0)) {
                    ConnectedComponent connectedComponent = new ConnectedComponent();
                    connectedComponent.number = clusternumber;
                    DFS(i, j, connectedComponent, 0);
                    connectedComponents.add(connectedComponent);
                }
            }
        }

        List<Character> requiredDoors = getRequiredDoors(/*startingRow, startingColumn*/);
        LinkedList<ConnectedComponent> connectedComponents1 = mergeClusters(connectedComponents);

        int size = 0;


        for (ConnectedComponent c : connectedComponents1) {
            for (Character key : requiredDoors) {
                if (connectedComponents.contains(key)) {
                    size += c.size;
                    break;
                }
            }
        }


        return size;
    }

    private LinkedList<ConnectedComponent> mergeClusters(ArrayList<ConnectedComponent> connectedComponents) {
        return null;
    }

    private void DFS(int row, int column, ConnectedComponent connectedComponent, int size) {
        if (row < 0 || column < 0 || row >= a.length || column >= a[0].length() || arr[row][column] > 0 || a[row].charAt(column) == '#') {
            return;
        }
        if (isDoor(a[row].charAt(column))) {
            connectedComponent.keys.add(a[row].charAt(column));
            return;
        }

        if (isKey(a[row].charAt(column))) {
            connectedComponent.keys.add(a[row].charAt(column));
        }


        arr[row][column] = connectedComponent.number;
        size++;
        connectedComponent.size = Math.max(connectedComponent.size, size);

        DFS(row, column + 1, connectedComponent, size + 1);
        DFS(row, column - 1, connectedComponent, size + 1);
        DFS(row - 1, column, connectedComponent, size + 1);
        DFS(row + 1, column, connectedComponent, size + 1);
    }

    private boolean isDoor(char c) {
        return false;
    }

    private boolean isKey(char c) {
        return false;
    }


    public void setStartingPos(String[] startingPos) {
        boolean posNotFound = true;
        for (int i = 0; posNotFound && (i < startingPos.length); i++) {
            for (int j = 0; posNotFound && (j < startingPos[i].length()); j++) {
                if (startingPos[i].charAt(j) == '*') {
                    startingRow = i;
                    startingColumn = j;
                    posNotFound = true;
                }
            }
        }
    }

    public List<Character> getRequiredDoors() {
        return null;
    }
}


class ConnectedComponent {
    int number;
    int size;
    HashSet<Character> keys;
    HashSet<Character> Doors;
}
