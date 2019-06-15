package com.himanshu.practice.y2019.june.june10;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 10/06/19.
 */
public class DestroyIt {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Turn[] turns = new Turn[n];
        String[] str;

        for (int i = 0; i < n; i++) {
            int numCards = Integer.parseInt(br.readLine());
            turns[i] = new Turn(numCards);
            for (int j = 0; j < numCards; j++) {
                str = br.readLine().split(" ");
                int c = Integer.parseInt(str[0]);
                long d = Long.parseLong(str[1]);
                turns[i].setCard(j, new Card(c, d));
            }
            turns[i].calculateMaxDamage();
        }


    }
}


class Turn {
    Card[] cards;
    long maxDamage; //max damage with <=3 cost
    int numCardPlayed;

    public Turn(int numOfCards) {
        cards = new Card[numOfCards];
    }

    public void setCard(int index, Card card) {
        cards[index] = card;
    }

    public void calculateMaxDamage() {

    }
}

@AllArgsConstructor
class Card {
    int cost;
    long damage;
}
