package uno;

import static uno.Ground.middleCard;


public class Card {
    int number;
    String color;
    String shape;
    /**
     * this constructor is for Skip and reverse and draw2 cards
     * @param color
     * @param shape
     */
    public Card(String color, String shape) {
        this.color = color;
        this.shape = shape;
    }

    /**
     * for normal cards
     * @param color
     * @param number
     */
    public Card(String color, int number) {
        this.color = color;
        this.number = number;
    }
    /**
     * for wild cards
     */
    public Card() {
    }

    /**
     * check player's card with middle card
     * @return true if equal
     */
    public boolean check() {
        return middleCard.color.equals(this.color);
    }

    /**
     * increase or decrease turn through side
     */
    public void action() {
        if (Ground.side) {
            Ground.turn++;
        } else {
            Ground.turn--;
        }
        Ground.turn();
    }
}
