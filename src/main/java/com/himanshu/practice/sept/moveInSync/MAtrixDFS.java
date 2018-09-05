package com.himanshu.practice.sept.moveInSync;


import lombok.AllArgsConstructor;
import lombok.ToString;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 05/09/18.
 * Print path from one nore to other node.
 */
public class MAtrixDFS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str[] = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

        String start[] = br.readLine().split(" ");
        Position startP = new Position(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
        String end[] = br.readLine().split(" ");
        Position endP = new Position(Integer.parseInt(end[0]), Integer.parseInt(end[1]));

        DFS dfs = new DFS(arr, n);
        LinkedList<Position> positions = new LinkedList<>();
        positions.addLast(startP);
        dfs.getShortestPAth(startP.row, startP.colum, startP, endP, positions);

        if(dfs.visitedPAth != null) {
            for (Position p : dfs.visitedPAth) {
                System.out.println(p);
            }
        } else {
            System.out.println("Path doesnot Exist");
        }
    }
}


class DFS {
    int[][] matrix;
    boolean[][] isvisited;
    int n;
    LinkedList<Position> visitedPAth;

    public DFS(int[][] matrix, int n) {
        this.matrix = matrix;
        this.n = n;
        this.isvisited = new boolean[n][n];
    }


    public void getShortestPAth(int row, int column, Position startP, Position endP, LinkedList<Position> positions) {
        if ((!isValid(row, column)) || isvisited[row][column] || visitedPAth != null || matrix[row][column] == 0) {
            return;
        }
        isvisited[row][column] = true;

        if (row == endP.row && column == endP.colum) {
            visitedPAth = new LinkedList<>(positions);
            return;
        }


        Position neighbour = new Position(row + 1, column);
        positions.addLast(neighbour);
        getShortestPAth(neighbour.row, neighbour.colum, startP, endP, positions);
        positions.removeLast();

        neighbour = new Position(row - 1, column);
        positions.addLast(neighbour);
        getShortestPAth(neighbour.row, neighbour.colum, startP, endP, positions);
        positions.removeLast();

        neighbour = new Position(row, column + 1);
        positions.addLast(neighbour);
        getShortestPAth(neighbour.row, neighbour.colum, startP, endP, positions);
        positions.removeLast();

        neighbour = new Position(row, column - 1);
        positions.addLast(neighbour);
        getShortestPAth(neighbour.row, neighbour.colum, startP, endP, positions);
        positions.removeLast();


    }

    private boolean isValid(int row, int column) {
        if (row < 0 || column < 0 || row >= n || column >= n) {
            return false;
        }
        return true;
    }


}


@AllArgsConstructor
@ToString
class Position {
    int row;
    int colum;
}

/*


3
1 1 0
0 1 0
0 0 0
0 0
1 1



5
1 1 0 1 1
0 1 1 1 1
0 0 0 1 0
0 0 1 1 1
1 1 1 1 1
0 0
4 0



5
1 1 0 1 1
0 1 0 1 1
0 0 0 1 0
0 0 1 1 1
1 1 1 1 1
3 2
1 1


*
* */