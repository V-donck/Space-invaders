package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class AbstractFactory {
    public abstract Playership createPlayership();
    public abstract PlayerBullet createPlayerBullet(int dammage, int x, int y, int dx, int dy);
    public abstract EnemyShip createEnemyship();
    public abstract EnemyShip createEnemyship(int HP, int x, int y, int dx, int dy);


    public abstract int getPlayershipWidth();
    public abstract int getPlayershipHeigth();

    public abstract JFrame getFrame(); //-> is van Java2Dfactory
    public abstract void setGameDimensions(int GameWidth, int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int BulletWidth, int BulletHeight);
    public abstract void render();

}
