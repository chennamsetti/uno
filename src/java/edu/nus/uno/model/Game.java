/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nus.uno.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/*
 * Define an enumeration called Type
 */
enum Status {
    WAITING, STARTED, ENDED
}

/**
 *
 * @author Kishore
 */
public class Game {

    private String id;

    private Deck deck;

    private List<Player> players;

    private List<Card> discardPile;

    private Status status;

    public void createNewGame(String id) {
        this.id = id;
        this.deck = new Deck();
        deck.createNewDeck("Deck#1");
        
        this.players = new ArrayList<>();
        this.discardPile = new ArrayList<>();
    }

    /**
     * Add a new player
     *
     */
    public void addPlayer(String id, String name) {
        if (status == Status.WAITING) {
            this.players.add(new Player(id, name));
        }
    }

    public void changeGameStatus() {
        // for the first time invocation of changeGameStatus
        if (status == null) {
            status = Status.WAITING;
        } else if (status == Status.WAITING) {
            for (int i = 0; i < 7; i++) {
                for (Player player : players) {
                    player.addACard((Card) deck.takeACard());
                }
            }
            discardPile.add((Card) deck.takeACard());
            status = Status.STARTED;
        } else if (status == Status.STARTED) {
            status = Status.ENDED;
        }
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        String str = "Game {" + "id=" + id + "\n";
        Card discard = (Card) discardPile.get(discardPile.size() - 1);
        str = str + "Discard Pile: " + discard.toString() + "\n";
        str = str + deck.toString();
        for (Player player : players) {
            str = str + "\n" + player.toString();
        }
        return str;
    }
    
}
