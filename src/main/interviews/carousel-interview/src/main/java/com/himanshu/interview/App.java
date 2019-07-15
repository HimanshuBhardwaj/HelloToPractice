package com.himanshu.interview;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 */

@Slf4j
public class App {
    public static void main(String[] args) throws IOException {
        int boardSize = 3;
        int chance = 0;//player zero will have first chance
        Player player1 = new Player(0);
        Player player2 = new Player(1);
        TicTacToe ticTacToe = new TicTacToe(boardSize, chance);
        ticTacToe.registerPlayers(player1, player2);
        String str[] = null;

        Player nextMovePlayer = ticTacToe.whoseNextMove();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (nextMovePlayer != null) {
            log.info(String.format("%s please enter your move: ", (nextMovePlayer.getPlayerIndex() + 1)));

            try {
                str = br.readLine().split(",");
                if (str.length != 3) {
                    log.error("Wrong move! Please try again..");
                    continue;
                }
            } catch (Exception e) {
                log.error("Wrong move! Please try again..");
            }


            int row = Integer.parseInt(str[0]);
            int column = Integer.parseInt(str[1]);
            char charSign = str[2].charAt(0);
            Move move = new Move(row, column, charSign);
            if (!ticTacToe.insertMove(move, nextMovePlayer)) {
                log.error("Wrong move! Please try again");
                continue;
            }

            Player winner = ticTacToe.getWinner();

            if (winner != null) {
                if (winner.getPlayerIndex() == 2) {
                    log.info("Game Draw");
                } else {
                    log.info(String.format("Player %s has won the game", (winner.getPlayerIndex() + 1)));
                }
                break;
            } else {
                nextMovePlayer = ticTacToe.whoseNextMove();
            }

            ticTacToe.printBoard();
            System.out.println();

        }
    }
}


// row[n][2]
// column[n][2]
//diagonal[2][2]


