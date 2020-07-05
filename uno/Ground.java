package uno;

import java.util.ArrayList;
import java.util.Random;

public class Ground {
    static ArrayList<Card> storage;
    static ArrayList<Player> players;
    static int n;
    static int turn;
    static boolean side;
    static Card middleCard = null;

    /**
     * set the beginning of the game
     * @param n number of players
     * @param cOrP playing with computer or person
     */
    public Ground(int n , int cOrP){
        side = true;
        Ground.n = n;
        storage = new ArrayList<>();
        players = new ArrayList<>();
        if (cOrP == 1){
            Human p1 = new Human("you");
            players.add(p1);
            for (int i = 0; i < n-1 ; i++) {
                Computer computer = new Computer("computer"+(i+1));
                players.add(computer);
            }
        }
        else {
            for (int i = 0; i < n ; i++) {
                Human human = new Human("player"+(i+1));
                players.add(human);
            }
        }

        start();
        firstDivisionOfCards();
        // first card on the ground
        Random first = new Random();
        int randomCard = first.nextInt(108-(7*n));
        while (storage.get(randomCard) instanceof WildDraw || storage.get(randomCard) instanceof ColorCard){
            randomCard = first.nextInt(108-(7*n));
        }
        middleCard = storage.get(randomCard);
        turn = first.nextInt(n);
        middleCard.action();
        //first player to start
        showGround();
        players.get(turn).act();
        showGround();
    }

    /**
     * set 108 cards to storage
     */
    public void start(){
        // add 108 cards to storage
        storage.add(new NormalCard("red",0));
        storage.add(new NormalCard("green",0));
        storage.add(new NormalCard("yellow",0));
        storage.add(new NormalCard("blue",0));
        String color = "red";
        for (int i = 0; i < 72 ; i++) {
            if (i == 18){
                color = "green";
            }
            if (i == 36){
                color = "yellow";
            }
            if (i == 54){
                color = "blue";
            }
            storage.add(new NormalCard(color,(i%9)+1));
        }
        storage.add(new Skip("red" , "Skip" ));
        storage.add(new Skip("red" , "Skip" ));
        storage.add(new Skip("green" , "Skip" ));
        storage.add(new Skip("green" , "Skip" ));
        storage.add(new Skip("yellow" , "Skip" ));
        storage.add(new Skip("yellow" , "Skip" ));
        storage.add(new Skip("blue" , "Skip" ));
        storage.add(new Skip("blue" , "Skip" ));
        storage.add(new Reverse("red" , "Reverse" ));
        storage.add(new Reverse("red" , "Reverse"));
        storage.add(new Reverse("green" , "Reverse"));
        storage.add(new Reverse("green" , "Reverse"));
        storage.add(new Reverse("yellow" , "Reverse"));
        storage.add(new Reverse("yellow" , "Reverse"));
        storage.add(new Reverse("blue" , "Reverse"));
        storage.add(new Reverse("blue" , "Reverse"));
        storage.add(new Draw2("red" , "Draw2"));
        storage.add(new Draw2("red" , "Draw2"));
        storage.add(new Draw2("green" , "Draw2"));
        storage.add(new Draw2("green" , "Draw2"));
        storage.add(new Draw2("yellow" , "Draw2"));
        storage.add(new Draw2("yellow" , "Draw2"));
        storage.add(new Draw2("blue" , "Draw2"));
        storage.add(new Draw2("blue" , "Draw2"));
        storage.add(new WildDraw());
        storage.add(new WildDraw());
        storage.add(new WildDraw());
        storage.add(new WildDraw());
        storage.add(new ColorCard());
        storage.add(new ColorCard());
        storage.add(new ColorCard());
        storage.add(new ColorCard());
    }

    /**
     * divide 7 cards to each player
     */
    public static void firstDivisionOfCards(){
        int k = n ;
        int i = 0 ;
        while (k!=0){
            for (int j = 0; j < 7; j++) {
                Random number = new Random();
                int division = number.nextInt(storage.size());
                players.get(i).cards.add(storage.get(division));
                removeCardFromStorage(storage.get(division));
            }
            i++;
            k--;
        }
    }

    /**
     * @param card to remove
     */
    public static void removeCardFromStorage(Card card){
        storage.remove(card);
    }

    /**
     * @param player
     * @return number of player cards
     */
    public static int numberOfPlayerCards(Player player){
        return player.cards.size();
    }

    /**
     * manage the turn
     */
    public static void turn(){
        turn = (turn+n) % n;
    }

    static String[][] middle = new String[10][40];

    /**
     * set the middle card
     */
    public static void showMiddleCard(){
        if(!(middleCard instanceof WildDraw) && !(middleCard instanceof ColorCard)) {
            switch (middleCard.color) {
                case "red":
                    if (middleCard instanceof NormalCard) {
                        middle[5][18] = " ";
                        middle[5][17] = " ";
                        middle[5][19] = Main.ANSI_RED + middleCard.number + Main.ANSI_RESET;
                    } else {
                        middle[5][19] = " ";
                        middle[5][17] = " ";
                        middle[5][18] = Main.ANSI_RED + middleCard.shape + Main.ANSI_RESET;
                    }
                    break;
                case "green":
                    if (middleCard instanceof NormalCard) {
                        middle[5][18] = " ";
                        middle[5][17] = " ";
                        middle[5][19] = Main.ANSI_GREEN + middleCard.number + Main.ANSI_RESET;
                    } else {
                        middle[5][19] = " ";
                        middle[5][17] = " ";
                        middle[5][18] = Main.ANSI_GREEN + middleCard.shape + Main.ANSI_RESET;
                    }
                    break;
                case "yellow":
                    if (middleCard instanceof NormalCard) {
                        middle[5][18] = " ";
                        middle[5][17] = " ";
                        middle[5][19] = Main.ANSI_YELLOW + middleCard.number + Main.ANSI_RESET;
                    } else {
                        middle[5][19] = " ";
                        middle[5][17] = " ";
                        middle[5][18] = Main.ANSI_YELLOW + middleCard.shape + Main.ANSI_RESET;
                    }
                    break;
                case "blue":
                    if (middleCard instanceof NormalCard) {
                        middle[5][18] = " ";
                        middle[5][17] = " ";
                        middle[5][19] = Main.ANSI_BLUE + middleCard.number + Main.ANSI_RESET;
                    } else {
                        middle[5][19] = " ";
                        middle[5][17] = " ";
                        middle[5][18] = Main.ANSI_BLUE + middleCard.shape + Main.ANSI_RESET;
                    }
                    break;
            }
            for (int i = 0; i < 10 ; i++) {
                middle[i][0] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
                middle[i][39] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
            }
            for (int j = 0; j < 40 ; j++) {
                middle[0][j] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
                middle[9][j] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
            }
        }
        else {
            if (middleCard instanceof ColorCard){
                middle[5][19] = " ";
                middle[5][18] = " ";
                middle[5][17] = "ColorDraw";
            }
            if (middleCard instanceof WildDraw){
                middle[5][19] = " ";
                middle[5][18] = " ";
                middle[5][17] = "WildDraw";
            }
        }
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 40 ; j++) {
                if (middle[i][j] == null){
                    middle[i][j] = " ";
                }
            }
        }
        middle[5][39] = " ";
        middle[5][35] = " ";
        middle[5][33] = " ";
        middle[5][36] = " ";
        middle[5][32] = " ";
        middle[5][31] = " ";
        if (middleCard instanceof Draw2){
          middle[5][35] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
        }
        else if (middleCard instanceof Reverse){
            middle[5][33] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
        }
        else if (middleCard instanceof Skip){
            middle[5][36] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
        }
        else if (middleCard instanceof WildDraw){
            middle[5][32] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
        }
        else if (middleCard instanceof ColorCard){
            middle[5][31] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
        }
        else if (middleCard instanceof NormalCard){
            middle[5][39] = Main.ANSI_CYAN + "$" + Main.ANSI_RESET;
        }

    }

    /**
     * print the information of table
     */
    public static void showGround(){
        int j = 1;
        if (Main.cOrP == 1){
            for (Player i : players) {
                if (i instanceof Human){
                    System.out.print("You : " + numberOfPlayerCards(i));
                }
                if (i instanceof Computer) {
                    System.out.printf(" Computer%d : %d",j,numberOfPlayerCards(i));
                    j++;
                }
            }
        }
        else {
            for (Player i : players){
                System.out.printf("player%d : %d ",j,numberOfPlayerCards(i));
                j++;
            }
        }

        System.out.println();
        showMiddleCard();
        for (int i = 0; i < 10 ; i++) {
            for (int k = 0; k < 40 ; k++) {
                System.out.print(middle[i][k]);
            }
            System.out.println();
        }
        if (side){
            System.out.println("clockwise");
        }
        else {
            System.out.println("counter clockwise");
        }
    }

    /**
     * @return true if the game is finished
     */
    public static boolean winner(){
        for(Player i : players){
            if (i.cards.size() == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * print scores
     */
    public static void scores(){
        int score ;
        for (Player i : players){
            score = 0;
            for (Card j : i.cards){
                if (j instanceof NormalCard){
                    score += j.number;
                }
                else if (j instanceof Skip || j instanceof Reverse || j instanceof Draw2 ){
                    score += 20;
                }
                else {
                    score += 50;
                }
            }
            System.out.println(i.name + " : " + (score) + " scores");
        }
    }
}
