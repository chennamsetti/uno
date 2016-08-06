/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nus.uno.model;

/*
 * Define an enumeration called Color
 */
enum Color {
    BLUE, RED, GREEN, YELLOW
}

/*
 * Define an enumeration called Type
 */
enum Type {
    NORMAL, SKIP, REVERSE, TAKE2, TAKE4, WILD
}

/**
 *
 * @author Kishore
 */
public class Card {

    private Color color;

    private Type type;

    private int cardValue;

    private String cardImage;

    public Card(Color color, Type type, int cardValue) {
        this.color = color;
        this.type = type;
        this.cardValue = cardValue;
        this.cardImage = "" + color + type + "" + cardValue;
    }

    /**
     * Get the value of cardImage
     *
     * @return the value of cardImage
     */
    public String getCardImage() {
        return cardImage;
    }

    /**
     * Set the value of cardImage
     *
     * @param cardImage new value of cardImage
     */
    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    /**
     * Get the value of cardValue
     *
     * @return the value of cardValue
     */
    public int getCardValue() {
        return cardValue;
    }

    /**
     * Set the value of cardValue
     *
     * @param cardValue new value of cardValue
     */
    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return "\tCard {" + "color=" + color + ", type=" + type + ", cardValue=" + cardValue + ", cardImage=" + cardImage + '}';
    }

}
