package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.util.ArrayList;

/**
 * creates all objects in game abstract: there must be an other factory to implement the visual part of the game
 */
abstract public class AbstractFactory {

    // create objects

    /**
     * creates a {@link PlayerShip}
     * @param playerShipHeight height of playership
     * @param gameWidth width of game (for setting ship at right place)
     * @param gameHeight height of game (for setting ship at right place)
     * @return {@link PlayerShip} : returns created {@link PlayerShip}
     */
    public abstract PlayerShip createPlayerShip(int playerShipHeight, int gameWidth, int gameHeight);

    /**
     * creates a {@link PlayerBullet}
     * @param damage the damage of the {@link Bullet}
     * @param x x-coordinate for setting {@link MovementComponent}
     * @param y y-coordinate for setting {@link MovementComponent}
     * @param dx dx for setting {@link MovementComponent}
     * @param dy dy for setting {@link MovementComponent}
     * @return {@link PlayerBullet}: returns created {@link PlayerBullet}
     */
    public abstract PlayerBullet createPlayerBullet(int damage, int x, int y, int dx, int dy);

    /**
     * creates a {@link EnemyShip}
     * @param hp hp of {@link Ship}
     * @param x x-coordinate for setting {@link MovementComponent}
     * @param y y-coordinate for setting {@link MovementComponent}
     * @param dx dx for setting {@link MovementComponent}
     * @param dy dy for setting {@link MovementComponent}
     * @param level level of {@link EnemyShip}
     * @return {@link EnemyShip}: returns created {@link EnemyShip}
     */
    public abstract EnemyShip createEnemyShip(int hp, int x, int y, int dx, int dy, int level);

    /**
     * creates an {@link EnemyBullet}
     * @param damage damage of {@link Bullet}
     * @param x x-coordinate for setting {@link MovementComponent}
     * @param y y-coordinate for setting {@link MovementComponent}
     * @param dx dx for setting {@link MovementComponent}
     * @param dy dy for setting {@link MovementComponent}
     * @return {@link EnemyBullet} : returns created {@link EnemyBullet}
     */
    public abstract EnemyBullet createEnemyBullet(int damage, int x, int y, int dx, int dy);

    /**
     * creates a {@link Friendly}
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link Friendly}: returns created {@link Friendly}
     */
    public abstract Friendly createFriendly(int xCoord);

    /**
     * creates a {@link Bulletx}
     * @param number number of bullets 2 or 3
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link Bulletx}: returns created {@link Bulletx}
     */
    public abstract Bulletx createBulletx(int number, int xCoord);

    /**
     * creates a {@link BoxDamageBullet}
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link BoxDamageBullet}: returns created  {@link BoxDamageBullet}
     */
    public abstract BoxDamageBullet createBoxDamageBullet(int xCoord);

    /**
     * creates a {@link BoxHp}
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link BoxHp}: returns created  {@link BoxHp}
     */
    public abstract BoxHp createBoxHp(int xCoord,int hp);


    // other functions

    /**
     * returns factorX
     * @return factorX : factorx = horizontal factor = (ScreenWidth/GameWidth)
     */
    public abstract double getFactorX();

    /**
     * returns factorY
     * @return  factorY: factorY = vertical factor = (ScreenHeight/GameHeight)
     */
    public abstract double getFactorY();

    /**
     * creates inputclass: {@link Input}
     * @return {@link Input}: returns created {@link Input}
     */
    public abstract Input createInput();

    /**
     * Set all game dimensions:
     * @param gameWidth set GameWidth
     * @param gameHeight set GameHeight
     * @param gamePlayerShipWidth set GamePlayerShipWidth
     * @param gamePlayerShipHeight set GamePlayerShipHeigth
     * @param bulletWidth set BulletWidth
     * @param bulletHeight set BulletHeight
     * @param enemyShipWidth set EnemyShipWidth
     * @param enemyShipHeight set EnemyShipHeight
     * @param boxWidth set boxWidth
     * @param boxHeight set boxHeight
     * @param score set score
     */
    public abstract void setGameDimensions(int gameWidth, int gameHeight, int gamePlayerShipWidth, int gamePlayerShipHeight, int bulletWidth, int bulletHeight, int enemyShipWidth, int enemyShipHeight, int boxWidth, int boxHeight, int score);

    /**
     * render screen
     */
    public abstract void render();

    /**
     * set Isrunning
     * @param ir: boolean isrunning
     */
    public abstract void setIsRunning(boolean ir);

    /**
     * updates name to screen
     * @param name : string name
     */
    public abstract void updateName(String name);

    /**
     * updates score to screen
     * @param score : int score
     */
    public abstract void updateScore(int score);

    /**
     * updates HP to screen
     * @param HP : int HP of {@link PlayerShip}
     */
    public abstract void updateHP(int HP);

    /**
     * Updates level to screen
     * @param level : int level
     */
    public abstract void updateLevel(int level);

    // create screens

    /**
     * creates first screen
     */
    public abstract void first();

    /**
     * creates game-over screen
     */
    public abstract void gameover();

    /**
     * creates screen to readName
     */
    public abstract void readName();

    /**
     * creates scorebord
     * @param scoreList list of names and scores
     */
    public abstract void scorebord(ArrayList<String> scoreList);

}
