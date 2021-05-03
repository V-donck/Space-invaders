package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.util.ArrayList;

abstract public class AbstractFactory {
    public abstract PlayerShip createPlayerShip(int playershipHeight, int playershipWidth, int GameWidth, int GameHeight);
    public abstract PlayerBullet createPlayerBullet(int damage, int x, int y, int dx, int dy);
    public abstract EnemyShip createEnemyShip();
    public abstract EnemyShip createEnemyShip(int HP, int x, int y, int dx, int dy, int level);
    public abstract EnemyBullet createEnemyBullet(int damage, int x, int y, int dx, int dy);
    public abstract Friendly createFriendly(int xcoord);
    public abstract Bulletx createBulletx(int number, int xcoord);
    public abstract BoxDamageBullet createBoxDamageBullet(int xcoord);


    public abstract double getFactorx();
    public abstract double getFactory();


    public abstract Input createInput();

    public abstract void setGameDimensions(int GameWidth, int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int BulletWidth, int BulletHeight, int EnemyshipWidth, int EnemyshipHeight, int boxWidth, int boxHeight,     int score);
    public abstract void render();
    public abstract void setIsrunning(boolean ir);
    public abstract void updateName(String name);
    public abstract void updateScore(int score);
    public abstract void updateHP(int HP);
    public abstract void updateLevel(int level);
    // create screens
    public abstract void first();
    public abstract void gameover();
    public abstract void readName();
    public abstract void scorebord(ArrayList<String> scorelist);

}
