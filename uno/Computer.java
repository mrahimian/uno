package uno;

import static uno.Ground.middleCard;
import static uno.Ground.storage;

public class Computer extends Player {
    /**
     * set name
     * @param name
     */
    public Computer(String name) {
        super(name);
    }

    /**
     * find the appropriate card to put
     */
    @Override
    public void act() {
        System.out.println(this.name + " :");
        //showCard();
        System.out.println();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).check()){
                storage.add(Ground.middleCard);
                Ground.middleCard = cards.get(i);
                Card card = cards.get(i);
                cards.remove(i);
                card.action();
                return;
            }
        }
        for (int i = 0; i < cards.size() ; i++) {
            if(cards.get(i) instanceof WildDraw){
                storage.add(middleCard);
                Ground.middleCard = cards.get(i);
                Card card = cards.get(i);
                cards.remove(cards.get(i));
                ((WildDraw) card).wildAction(this);
                return;
            }
        }
        takeCard();
    }


}
