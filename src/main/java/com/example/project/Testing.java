package com.example.project;

import java.util.ArrayList;

public class Testing {
    public static void main(String[] args) {
        // ["♠","♥","♣","♦"]
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("10", "♠"));
        
        // Community Cards
        // ArrayList<Card> communityCards = new ArrayList<>();
        player.addCard(new Card("J", "♠"));
        player.addCard(new Card("Q", "♠"));
        player.addCard(new Card("K", "♠"));

        System.out.println(player.getAllCards());
        System.out.println(player.isFlush());
        System.out.println(player.isStraight());
        
        // System.out.println(player.playHand(communityCards));  
        
    }
}