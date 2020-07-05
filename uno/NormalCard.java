package uno;

public class NormalCard extends Card {
    /**
     * set color and number
     * @param color
     * @param number
     */
    public NormalCard(String color , int number){
        super(color,number);

    }

    /**
     * @return true if match the middle card
     */
    @Override
    public boolean check() {
        if (Ground.middleCard instanceof NormalCard){
            if (Ground.middleCard.number == this.number ){
                return true;
            }
        }
        return super.check();
    }

    /**
     * update turn
     */
    @Override
    public void action() {
        super.action();
    }
}
