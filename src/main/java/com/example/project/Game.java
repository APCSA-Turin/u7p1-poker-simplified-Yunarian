package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards){
        int player1Val = Utility.getHandRanking(p1Hand);
        int player2Val = Utility.getHandRanking(p2Hand);

        if (player1Val > player2Val) {
            return "Player 1 wins!";
        }

        if (player1Val < player2Val) {
            return "Player 2 wins!";
        }

        ArrayList<Card> p1Cards = p1.getHand();
        ArrayList<Card> p2Cards = p2.getHand();
        if (p1.highCard(p1Cards) > p2.highCard(p2Cards)) {
            return "Player 1 wins!";

        } else if (p1.highCard(p1Cards) < p2.highCard(p2Cards)) {
            return "Player 2 wins!";
        }
        return "Tie!";
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}