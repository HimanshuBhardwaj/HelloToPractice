package com.himanshu.interview;

/**
 * Created by himanshubhardwaj on 13/06/19.
 */


public interface Game {
    public boolean isValidMove(Move move, Player player);

    public boolean insertMove(Move move, Player player);

    public Player getWinner();

    public Player whoseNextMove();
}
