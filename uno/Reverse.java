package uno;

public class Reverse extends Card {
    /**
     * set color and shape
     * @param color
     * @param shape
     */
    public Reverse(String color , String shape){
        super(color,shape);
    }

    /**
     * check if match middle card
     * @return
     */
    @Override
    public boolean check() {
        if (Ground.middleCard instanceof Reverse){
            return true;
        }
        return super.check();
    }

    /**
     * change turn
     */
    @Override
    public void action(){
        if (Ground.side){
            Ground.side = false;
            Ground.turn--;
        }
        else {
            Ground.side = true;
            Ground.turn++;
        }
        Ground.turn();
    }
}
