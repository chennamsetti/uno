/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nus.uno.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Kishore
 */
public class Deck {

    private String id;

    private List<Card> deck;

    public void createNewDeck(String id) {
        this.id = id;
        this.deck = new ArrayList<>();

        deck.addAll(createColorSet(Color.BLUE));
        deck.addAll(createColorSet(Color.GREEN));
        deck.addAll(createColorSet(Color.YELLOW));
        deck.addAll(createColorSet(Color.RED));

        Collections.shuffle(deck);
    }

    private List<Card> createColorSet(Color color) {
        //numbers of all colors

        List<Card> cards = new ArrayList<>();
        Card card = null;

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                card = new Card(color, Type.NORMAL, 0);
                cards.add(card);
                card = new Card(color, Type.TAKE4, 50);
                cards.add(card);
                card = new Card(color, Type.WILD, 50);
                cards.add(card);
            }
            for (int j = 1; j <= 12; j++) {
                if (j <= 9) {
                    card = new Card(color, Type.NORMAL, j);
                }
                if (j == 10) {
                    card = new Card(color, Type.SKIP, 20);
                }
                if (j == 11) {
                    card = new Card(color, Type.REVERSE, 20);
                }
                if (j == 12) {
                    card = new Card(color, Type.TAKE2, 20);
                }
                cards.add(card);
            }
        }
        return cards;
    }

    // take a card from deck
    public Card takeACard() {
        Card card = (Card) deck.get(deck.size() - 1);
        deck.remove(deck.size() - 1);
        return card;
    }

    @Override
    public String toString() {
        String str = "Deck {" + "id=" + id;

        str = str + "\nCards on Deck: " + deck.size();
        return str;
    }

}
