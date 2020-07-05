package uno;

import java.util.Scanner;

import static uno.Ground.*;

public class Draw2 extends Card {
    static int fine2 = 0;
    static int t = 0;

    /**
     * set color and shape
     * @param color
     * @param shape
     */
    public Draw2(String color , String shape){
        super(color,shape);
    }

    /**
     * @return true if match the middle card or have the same color
     */
    @Override
    public boolean check() {
        if (Ground.middleCard instanceof Draw2){
            return true;
        }
        return super.check();
    }

    /**
     * do the action of draw2 card
     */
    @Override
    public void action(){
        Card card = null;
        fine2 = 2;
        System.out.println(players.get(turn).name);
        showGround();
        super.action();
        Player player = players.get(turn);
        while (player.haveDraw2OrNot()){
            if (player instanceof Computer){
                for (Card i : player.cards){
                    if (i instanceof Draw2){
                        card = i ;
                    }
                }
                storage.add(middleCard);
                middleCard = card;
                System.out.println(player.name);
                showGround();
                player.cards.remove(card);
                fine2+=2;
                super.action();
                player = players.get(turn);
            }
            else if (player instanceof Human){
                System.out.println("take cards by entering 1 or throw Draw2 by entering 2");
                Scanner scanner = new Scanner(System.in);
                int scan = scanner.nextInt();
                if (scan == 2){
                    for (Card i : player.cards){
                        if (i instanceof Draw2){
                            card = i ;
                        }
                    }
                    storage.add(middleCard);
                    middleCard = card;
                    System.out.println(player.name);
                    showGround();
                    player.cards.remove(card);
                    fine2+=2;
                    super.action();
                    player = players.get(turn);
                }
                else {
                    break;
                }
            }
        }
        while (fine2!=0){
            player.penalty();
            fine2--;
            t=1;
        }
        super.action();
    }

}
