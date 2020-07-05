package uno;


import java.util.Random;
import java.util.Scanner;

import static uno.Ground.*;

public class WildDraw extends Card {
    static int fine = 4 ;

    /**
     * set black to be different from other cards
     */
    public WildDraw(){
        this.color = "black";
    }

    /**
     * @return false because of its special laws
     */
    @Override
    public boolean check() {
        return false;
    }

    /**
     * set new color
     * @param player
     */
    public void chooseColor(Player player){
        String[] colors = {"red" , "green" , "yellow" , "blue"};
        int color ;
        if (player instanceof Computer){
            Random col = new Random();
            color = col.nextInt(4);
            Ground.storage.add(Ground.middleCard);
            Ground.middleCard.color = colors[color];
        }
        if (player instanceof Human){
            Scanner scan = new Scanner(System.in);
            System.out.println("enter color :");
            String midColor = scan.next();
            Ground.storage.add(Ground.middleCard);
            Ground.middleCard.color = midColor;
        }
        System.out.println("now the color is : " + Ground.middleCard.color);
    }

    /**
     * do the action of wild draw card
     * @param player
     */
    public void wildAction(Player player){
        chooseColor(player);
        Card card = null;
        fine = 4;
        System.out.println(players.get(turn).name);
        showGround();
        super.action();
        Player p = players.get(turn);
        while (p.haveWildDrawOrNot()){
            if (p instanceof Computer){
                for (Card i : p.cards){
                    if (i instanceof WildDraw){
                        card = i ;
                    }
                }
                storage.add(middleCard);
                middleCard = card;
                System.out.println(p.name);
                showGround();
                p.cards.remove(card);
                fine+=4;
                chooseColor(p);
                super.action();
                p = players.get(turn);
            }
            else if (p instanceof Human){
                System.out.println("take cards by entering 1 or throw WildDraw by entering 2");
                Scanner scanner = new Scanner(System.in);
                int scan = scanner.nextInt();
                if (scan == 2){
                    for (Card i : p.cards){
                        if (i instanceof WildDraw){
                            card = i ;
                        }
                    }
                    storage.add(middleCard);
                    middleCard = card;
                    System.out.println(p.name);
                    showGround();
                    p.cards.remove(card);
                    fine+=4;
                    chooseColor(p);
                    super.action();
                    p = players.get(turn);
                }
                else {
                    break;
                }
            }
        }
        while (fine!=0){
            p.penalty();
            fine--;
        }
        super.action();
    }
}
