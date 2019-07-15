package com.himanshu.interview;

import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 13/06/19.
 */

public class TicTacToe implements Game {
    private int boardSize;
    private char[][] board;
    private int rowCount[][];
    private int columnCount[][];
    private int diagonalCount[][];
    private int chance;//person who has next change
    private ArrayList<Player> players;
    private Move lastMove = null;
    private Player lastMovePlayer = null;
    private int totalNumMoves = 0;

    @java.beans.ConstructorProperties({"boardSize", "board", "rowCount", "columnCount", "diagonalCount", "Chance"})
    public TicTacToe(int boardSize, int chance) {
        this.boardSize = boardSize;
        this.chance = chance;
        this.board = new char[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '.';
            }
        }

        printBoard();
        this.rowCount = new int[boardSize][2];//for two players
        this.columnCount = new int[boardSize][2];//for two players
        this.diagonalCount = new int[2][2];//for two players
        this.players = new ArrayList<>();
    }

    public void registerPlayers(Player player1, Player player2) {
        players.add(player1);
        players.add(player2);//second could be computer in future
    }

    @Override
    public boolean isValidMove(Move move, Player player) {
        if (player.getPlayerIndex() == chance) {
            if (board[move.getRow()][move.getColumn()] == '.') {
                return true;
                //currently a person can enter other person's sign. We will try to improve it later
            }
        }
        return false;
    }

    @Override
    public boolean insertMove(Move move, Player player) {
        if (isValidMove(move, player)) {
            lastMove = move;
            lastMovePlayer = player;
            totalNumMoves++;
            board[move.getRow()][move.getColumn()] = move.getSign();
            rowCount[move.getRow()][player.getPlayerIndex()]++;
            columnCount[move.getColumn()][player.getPlayerIndex()]++;
            chance = (chance + 1) % 2;
            if (move.getRow() == move.getColumn()) { //diagonal
                diagonalCount[0][player.getPlayerIndex()]++;
            }
            if ((move.getRow() + move.getColumn()) == (boardSize - 1)) {
                diagonalCount[1][player.getPlayerIndex()]++;
            }
            return true;
        }

        return false;
    }

    @Override
    public Player getWinner() {
        if (lastMove != null) {
            //the person
            if (rowCount[lastMove.getRow()][lastMovePlayer.getPlayerIndex()] == boardSize) {
                return lastMovePlayer;
            }
            if (columnCount[lastMove.getColumn()][lastMovePlayer.getPlayerIndex()] == boardSize) {
                return lastMovePlayer;
            }
            if (diagonalCount[0][lastMovePlayer.getPlayerIndex()] == boardSize || diagonalCount[1][lastMovePlayer.getPlayerIndex()] == boardSize) {
                return lastMovePlayer;
            }
            if (totalNumMoves == boardSize * boardSize) {//beaware of overflow
                return new Player(2); //index 2 represents draw
            }
        }
        return null;
    }

    @Override
    public Player whoseNextMove() {
        if (totalNumMoves < (boardSize * boardSize)) {
            return players.get(chance); //chance is either 0 or 1
        } else {
            return null;
        }
    }


    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
