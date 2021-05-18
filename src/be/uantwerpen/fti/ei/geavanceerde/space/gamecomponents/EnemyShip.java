package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * the PlayerShip must shoot these EnemyShips
 */
abstract public class EnemyShip extends Ship{
    protected int level;

    /**
     * creates EnemyShip
     * @param HP set HP of {@link Ship}
     * @param x x-coordinate for setting {@link MovementComponent}
     * @param y y-coordinate for setting {@link MovementComponent}
     * @param dx dx for setting {@link MovementComponent}
     * @param dy dy for setting {@link MovementComponent}
     * @param level level of {@link EnemyShip}
     */
    public EnemyShip(int HP, int x, int y, int dx, int dy, int level) {
        super(HP);
        this.setMovementComponent(x,y,dx,dy);
        this.level = level;
    }

    /**
     * get level of EnemyShip
     * @return int level
     */
    public int getLevel() {
        return level;
    }

    /**
     * set level of EnemyShip
     * @param level level of EnemyShip
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * visualises EnemyShip
     */
    public abstract void visualise();

}
