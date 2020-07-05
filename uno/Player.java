package uno;

import java.util.ArrayList;
import java.util.Random;

import static uno.Ground.middleCard;


public abstract class Player {
    ArrayList<Card> cards ;
    String name;

    /**
     * set name and card's list
     * @param name
     */
    public Player(String name){
        this.name = name;
        cards = new ArrayList<>();
    }

    /**
     * card to put if allowed
     */
    public abstract void act();

    /**
     * call when cannot put card
     */
    public void takeCard(){
        System.out.println(this.name + " must take card");
        int rand;
        Random random = new Random();
        rand = random.nextInt(Ground.storage.size());
        cards.add(Ground.storage.get(rand));
        Card card = Ground.storage.get(rand);
        Ground.removeCardFromStorage(Ground.storage.get(rand));
        if (card.check()){
            Ground.storage.add(card);
            middleCard = card;
            showCard();
            System.out.println();
            cards.remove(card);

            if (card instanceof WildDraw){
                ((WildDraw) card).wildAction(this);
            }
            else {
                card.action();
            }
        }
        else {
            if (Ground.side) {
                Ground.turn++;
            } else {
                Ground.turn--;
            }
            Ground.turn();
        }
    }

    /**
     * fine for Draw2 or wildDraw
     */
    public void penalty(){
        int rand;
        Random random = new Random();
        rand = random.nextInt(Ground.storage.size());
        cards.add(Ground.storage.get(rand));
        Ground.removeCardFromStorage(Ground.storage.get(rand));
    }

    /**
     * print current cards
     */
    public void showCard(){
        for (Card i : cards) {
            if(!(i instanceof WildDraw) && !(i instanceof ColorCard)) {
                if (i.color.equals("red")) {
                    if (i instanceof NormalCard) {
                        System.out.print(" " + Main.ANSI_RED + i.number + Main.ANSI_RESET + " ");
                    } else {
                        System.out.print(" " + Main.ANSI_RED + i.shape + Main.ANSI_RESET + " ");
                    }
                }
                if (i.color.equals("green")) {
                    if (i instanceof NormalCard) {
                        System.out.print(" " + Main.ANSI_GREEN + i.number + Main.ANSI_RESET + " ");
                    } else {
                        System.out.print(" " + Main.ANSI_GREEN + i.shape + Main.ANSI_RESET + " ");
                    }
                }
                if (i.color.equals("yellow")) {
                    if (i instanceof NormalCard) {
                        System.out.print(" " + Main.ANSI_YELLOW + i.number + Main.ANSI_RESET + " ");
                    } else {
                        System.out.print(" " + Main.ANSI_YELLOW + i.shape + Main.ANSI_RESET + " ");
                    }
                }
                if (i.color.equals("blue")) {
                    if (i instanceof NormalCard) {
                        System.out.print(" " + Main.ANSI_BLUE + i.number + Main.ANSI_RESET + " ");
                    } else {
                        System.out.print(" " + Main.ANSI_BLUE + i.shape + Main.ANSI_RESET + " ");
                    }
                }
            }
            else {
                if (i instanceof ColorCard){
                    System.out.print(" ColorCard ");
                }
                if (i instanceof WildDraw){
                    System.out.print(" WildDraw ");
                }
            }
        }
    }

    /**
     * @return true if have draw2
     */
    public boolean haveDraw2OrNot(){
        for (Card i : cards){
            if (i instanceof Draw2){
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if have wild draw
     */
    public boolean haveWildDrawOrNot(){
        for (Card i : cards){
            if (i instanceof WildDraw){
                return true;
            }
        }
        return false;
    }

}
