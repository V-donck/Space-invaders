package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import javax.swing.*;
import java.util.ArrayList;

abstract public class AbstractFactory {
    public abstract Playership createPlayership(int playershipHeight,int playershipWidth, int GameWidth, int GameHeight);
    public abstract PlayerBullet createPlayerBullet(int damage, int x, int y, int dx, int dy);
    public abstract EnemyShip createEnemyship();
    public abstract EnemyShip createEnemyship(int HP, int x, int y, int dx, int dy);
    public abstract EnemyBullet createEnemyBullet(int damage, int x, int y, int dx, int dy);
    public abstract Friendly createFriendly(int xcoord);
    public abstract Bulletx createBulletx(int number, int xcoord);
    public abstract BoxDamageBullet createBoxDamageBullet(int xcoord);


    public abstract int getPlayershipWidth();
    public abstract int getPlayershipHeight();
    public abstract int getEnemyshipWidth();

    public abstract int getGameWidth();
    public abstract int getGameHeight();


    public abstract double getFactorx();
    public abstract double getFactory();


    //public abstract JFrame getFrame(); //-> is van
    public abstract Input createinput();


    public abstract void setGameDimensions(int GameWidth, int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int BulletWidth, int BulletHeight, int EnemyshipWidth, int EnemyshipHeight, int boxWidth, int boxHeight,     int score);
    public abstract void render();
    public abstract void updatescore(int score);
    public abstract void updatehp(int hp);
    public abstract void first();
    public abstract void scorebord(ArrayList<String> scorelist);

    public abstract void setIsrunning(boolean ir);

    public abstract void updatename(String name);
    public abstract void gameover();
    public abstract void readname();

}
