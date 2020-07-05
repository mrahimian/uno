package uno;

public class Skip extends Card {
    /**
     * set color and shape
     * @param color
     * @param shape
     */
    public Skip(String color , String shape){
        super(color,shape);
    }

    /**
     * check if match middle card
     * @return
     */
    @Override
    public boolean check() {
        if (Ground.middleCard instanceof Skip){
            return true;
        }
        return super.check();
    }

    /**
     * skip next player
     */
    @Override
    public void action(){
        if (Ground.side){
            Ground.turn+=2;
        }
        else {
            Ground.turn-=2;
        }
        Ground.turn();
    }
}
