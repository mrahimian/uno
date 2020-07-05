package uno;

import java.util.Scanner;

import static uno.Ground.middleCard;

public class Human extends Player {
    /**
     * set name
     * @param name
     */
    public Human(String name) {
        super(name);
    }

    /**
     * choose the card to put
     */
    @Override
    public void act() {
        if (Main.cOrP == 1) {
            System.out.println("your turn");
        }
        else{
            System.out.println(name);
        }
        boolean flag = false ;
        showCard();
        System.out.println();
        boolean wildOrNot = false ;
        for (Card item : cards) {
            if (item.check()) {
                flag = true;
            }
        }
        for (Card value : cards) {
            if (value instanceof WildDraw) {
                wildOrNot = true;
                break;
            }
        }
        if (!flag && !wildOrNot ){
            System.out.println("you don't have choice.");
            takeCard();
            return;
        }
        System.out.println("choose number of card in respect to above order :");
        Scanner scanner = new Scanner(System.in);
        int cardNumber = scanner.nextInt();
        if (cardNumber > cards.size()){
            while (cardNumber > cards.size() || cardNumber < 1){
                System.out.println("invalid input. try again.");
                cardNumber = scanner.nextInt();
            }
        }
        cardNumber--;
        if( cards.get(cardNumber) instanceof WildDraw){
            if (flag){
                System.out.println("you can't throw this card till you have another card to throw. take card by inserting \"take\" or choose another by entering any key");
                String input = scanner.next();
                if (input.equals("take")){
                    takeCard();
                }
            }
            else {
                Ground.storage.add(middleCard);
                middleCard = cards.get(cardNumber);
                ((WildDraw) cards.get(cardNumber)).wildAction(this);
                cards.remove(cards.get(cardNumber));
            }
        }
        else {
            if(cards.get(cardNumber).check()) {
                Ground.storage.add(middleCard);
                middleCard = cards.get(cardNumber);
                Card card = cards.get(cardNumber);
                cards.remove(cardNumber);
                card.action();
            }
            else {
                    System.out.println("it doesn't match with middleCard. please try again.");
                }
            }
        }


    }



