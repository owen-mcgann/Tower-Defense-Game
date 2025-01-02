public abstract class TowerProperties {
    private int damage;
    private String SpecialEffect;
    private int cost;
    // No longer having range limits on towers
    //private int range;
    private int x;
    private int y;

    /**
     * Gets x coordinate of tower
     * @return x coordinate of tower
     */
    public int getX(){
        return this.x;
    }
    /**
     * Gets y coordinate of tower
     * @return y coordinate of tower
     */
    public int getY(){
        return this.y;
    }

    /**
     * Set the x coordinate of tower
     * @param value x coordinate to be set
     */
    public void setX(int value){
        this.x = value;
    }
    /**
     * Set the y coordinate of tower
     * @param value y coordinate to be set
     */
    public void setY(int value){
        this.y = value;
    }
    /**
     * Gets tower damage value
     * @return amount of damage tower deals
     */
    public int getDamage() {
        return this.damage;  // Ensure damage is set correctly in the tower's constructor
    }

    /**
     * Sets tower damage value
     * @param value amount to set towers damage to
     */
    public void setDamage(int value){this.damage = value;}

    /**
     * Gets tower cost
     * @return cost of tower
     */
    public int getCost(){
        return this.cost;
    }

    /**
     * Sets tower cost
     * @param value amount to set towers cost to
     */
    public void setCost(int value) {
        this.cost = value;
    }

    //public abstract void Fire(Wave enemyWave);
}
