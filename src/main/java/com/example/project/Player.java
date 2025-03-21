package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        // adding community cards
        allCards.clear();
        for (int i = 0; i < communityCards.size(); i++) {
            allCards.add(communityCards.get(i));
        }

        // adding hand cards
        for (int i = 0; i < hand.size(); i++) {
            allCards.add(hand.get(i));
        }

        if (isRoyalFlush()) {
            return "Royal Flush";

            // if it's a straight flush
        } else if (isStraight() && isFlush()) {
            return "Straight Flush";

        } else if (isQuad()){
            return "Four of a Kind";

        // if full house
        } else if (numPairs() == 1 && isTrio()) {
            return "Full House";

        } else if (isFlush()) {
            return "Flush";

        } else if (isStraight()) {
            return "Straight";

        } else if (isTrio()) {
            return "Three of a Kind";

        } else if (numPairs() == 2){
            return "Two Pair";

        } else if (numPairs() == 1) {
            return "A Pair";

        // if high card is contained in hand
        } else if (highCard(hand) > highCard(communityCards)) {
            return "High Card";
        }
        
        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 0; i < allCards.size(); i++) {
            int minRank = Utility.getRankValue(allCards.get(i).getRank());
            int minIdx = i;

            for (int j = i + 1; j < allCards.size(); j++) {
                int currRank = Utility.getRankValue(allCards.get(j).getRank());
                if (currRank < minRank) {
                    minRank = currRank;
                    minIdx = j;
                }
            }
            Card temp = allCards.set(minIdx, allCards.get(i));
            allCards.set(i, temp);
        }

    } 

    // freq is the number of occurances of a given rank, with the postion matching up with:
    // ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]
    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            freq.add(0);
        }

        for (int i = 0; i < allCards.size(); i++){
            // incrementing the index of which is the rank of each card 
            int idx = Utility.getRankValue(allCards.get(i).getRank()) - 1;
            freq.set(idx, freq.get(idx) + 1);
        }
        return freq; 
    }

    // freq is the number of occurances of a given suit, with the position matching up with:
    // ["♠","♥","♣","♦"]
    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> freq = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            freq.add(0);
        }
        for (int i = 0; i < allCards.size(); i++) {
            // incrementing the index of which is the suit of each card
            int idx = Utility.getSuitPos(allCards.get(i).getSuit());
            freq.set(idx, freq.get(idx) + 1);
        }

        return freq;
    }

    // takes a Arraylist of Cards
    public int highCard(ArrayList<Card> c) {
        int highestCard = Utility.getRankValue(c.get(0).getRank());
       // goes through every card in the input 
       for (int i = 1; i < c.size(); i++) {
            int rankOfCard = Utility.getRankValue(c.get(i).getRank());
            // if that card has a higher rank, 
            // set highestCard equal to the card of the higher rank
            if (rankOfCard > highestCard) {
                highestCard = rankOfCard;
            }
        }
        // returns the card which has the highest rank in the input
        return highestCard;
    }

    private int numPairs() {
        int count = 0;
        sortAllCards();
        ArrayList<Integer> freq = findRankingFrequency();
        for (int i = 0; i < freq.size(); i++) {
            // checks to see if the rank frequency list contains a 2
            // increments count by 1 when that occurs
            // returning count after iterating through the rank whole frequency list
            if (freq.get(i) == 2) {
                count++;
            }
        }
        return count;
    }

    private boolean isTrio() {
        sortAllCards();
        ArrayList<Integer> freq = findRankingFrequency();
        for (int i = 0; i < freq.size(); i++) {
            // checks to see if the rank frequency list contains a 3
            // returns true if a rank of 3 is found
            if (freq.get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isQuad() {
        sortAllCards();
        ArrayList<Integer> freq = findRankingFrequency();
        for (int i = 0; i < freq.size(); i++) {
            if (freq.get(i) == 4) {
                return true;
            }
        }
        return false;
    }


    public boolean isStraight() {
        sortAllCards();
        for (int i = 0; i < allCards.size() - 1; i++){ 
            // given a sorted list, ,
            // if the rank value of the current card + 1 is not equal to the rank value of the next card
            // return false
            if (Utility.getRankValue(allCards.get(i).getRank()) + 1 != Utility.getRankValue(allCards.get(i + 1).getRank())){
                return false; 
            }
        }
        return true;
    }

    public boolean isFlush() {
        sortAllCards();
        for (int i = 0; i < allCards.size() - 1; i++) {
            // given a sorted list
            // if the current card's suit isn't the same as the next card's suit
            // return false.
            if (allCards.get(i).getSuit() != allCards.get(i + 1).getSuit()) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isRoyalFlush() {
        // A royal flush has to be both a flush and a straight
        if (!isFlush()) {
            return false;

        } if (!isStraight()) {
            return false;

        }

        // A royal flush has to have it's lowest rank (which is first in how my program sorts) be 10
        if (!allCards.get(0).getRank().equals("10")) {
            return false;
        }

        return true;
    }

    @Override
    public String toString(){
        return hand.toString();
    }
}
