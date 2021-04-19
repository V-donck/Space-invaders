package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import javax.swing.*;

abstract public class AbstractFactory {
    public abstract Playership createPlayership(int playershipHeight);
    public abstract PlayerBullet createPlayerBullet(int damage, int x, int y, int dx, int dy);
    public abstract EnemyShip createEnemyship();
    public abstract EnemyShip createEnemyship(int HP, int x, int y, int dx, int dy);
    public abstract EnemyBullet createEnemyBullet(int damage, int x, int y, int dx, int dy);
    public abstract Friendly createFriendly();
    public abstract Bulletx createBulletx(int number);
    public abstract BoxDamageBullet createBoxDamageBullet();


    public abstract int getPlayershipWidth();
    public abstract int getPlayershipHeight();
    public abstract int getEnemyshipWidth();

    public abstract int getGameWidth();
    public abstract int getGameHeight();


    public abstract double getFactorx();
    public abstract double getFactory();


    public abstract JFrame getFrame(); //-> is van Java2Dfactory
    public abstract void setGameDimensions(int GameWidth, int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int BulletWidth, int BulletHeight, int EnemyshipWidth, int EnemyshipHeight, int boxWidth, int boxHeight,     int score);
    public abstract void render();
    public abstract void updatescore(int score);
    public abstract void updatehp(int hp);


}
