package uno;

import java.util.Random;
import java.util.Scanner;

import static uno.Ground.players;
import static uno.Ground.turn;


public class ColorCard extends Card {
    /**
     * set white to be different from colored cards
     */
    public ColorCard(){
        this.color = "white";
    }

    /**
     * always true because it has no condition to throw
     * @return
     */
    @Override
    public boolean check() {
        return true;
    }

    /**
     * set new color
     */
    @Override
    public void action() {
        String[] colors = {"red" , "green" , "yellow" , "blue"};
        int color ;
        Player player = players.get(turn);
        if (player instanceof Computer){
            Random col = new Random();
            color = col.nextInt(4);
            Ground.storage.add(Ground.middleCard);
            Ground.middleCard.color = colors[color];
            player.cards.remove(this);
        }
        if (player instanceof Human){
            Scanner scan = new Scanner(System.in);
            System.out.println("enter color :");
            String midColor = scan.next();
            Ground.storage.add(Ground.middleCard);
            Ground.middleCard.color = midColor;
            player.cards.remove(this);
        }
        System.out.println("now the color is : " + Ground.middleCard.color);
        super.action();
    }
}
