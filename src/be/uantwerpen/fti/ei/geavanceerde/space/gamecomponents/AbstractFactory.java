package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class AbstractFactory {
    public abstract Playership createPlayership(int playershipHeight);
    public abstract PlayerBullet createPlayerBullet(int dammage, int x, int y, int dx, int dy);
    public abstract EnemyShip createEnemyship();
    public abstract EnemyShip createEnemyship(int HP, int x, int y, int dx, int dy);
    public abstract EnemyBullet createEnemyBullet(int dammage, int x, int y, int dx, int dy);
    public abstract Friendly createFriendly();
    public abstract Bulletx2 createBulletx2();


    public abstract int getPlayershipWidth();
    public abstract int getPlayershipHeigth();
    public abstract int getEnemyshipWidth();

    public abstract int getGameWidth();
    public abstract int getGameHeight();


    public abstract double getFactorx();
    public abstract double getFactory();


    public abstract JFrame getFrame(); //-> is van Java2Dfactory
    public abstract void setGameDimensions(int GameWidth, int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int BulletWidth, int BulletHeight, int EnemyshipWidth, int EnemyshipHeight, int boxWidth, int boxHeight,     int score);
    public abstract void render();
    public abstract void updatescore(int score);

}
