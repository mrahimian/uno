package uno;

import java.util.Scanner;

import static uno.Ground.*;

public class Main {
    static int cOrP;
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args)  {
        System.out.println("to play with computer enter 1 and to play with your friends enter 2");
        Scanner scanner = new Scanner(System.in);
        cOrP = scanner.nextInt();
        System.out.println("now please enter number of players ");
        int n = scanner.nextInt();
        Ground ground = new Ground(n,cOrP);
        do {
            Ground.players.get(turn).act();
            if (Draw2.t == 0) {
                showGround();
            }
            Draw2.t = 0;
        } while (!winner());
        for (Player i : players){
            if (i.cards.size()==0){
                System.out.println(i.name + " wins");
            }
        }
        scores();
    }

}
