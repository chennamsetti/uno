/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nus.uno.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kishore
 */
public class Player {

    private String id;

    private String name;

    private List<Card> cardsOnHand = null;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.cardsOnHand = new ArrayList<>();
    }

    /**
     * adds a card from list
     *
     */
    public void addACard(Card card) {
        //add a card to the list
        cardsOnHand.add(card);
    }

    /**
     * remove the card from list
     *
     */
    public void removeACard() {
        // remove the card from list
    }

    @Override
    public String toString() {
        String str = "  Player {" + "id=" + id + ", name=" + name + "\n    cardsOnHand=";
        for (Card card : cardsOnHand) {
            str = str + "\n" + card.toString();
        }
        return str;
    }
}
