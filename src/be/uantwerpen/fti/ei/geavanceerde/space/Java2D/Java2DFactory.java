package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import javax.imageio.ImageIO;
import javax.swing.*;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Java2DFactory extends AbstractFactory {
    private int ScreenWidth;
    private int ScreenHeight;

    private int PlayershipWidth;
    private int PlayershipHeigth;


    private int GameWidth;
    private int GameHeight;


    private double factorx;
    private double factory;



    public JFrame frame;
    public JPanel panel;

    public BufferedImage g2dimage;
    public BufferedImage backgroundIm;
    public BufferedImage PlayerShipIm;
    public BufferedImage EnemyShipIm;
    public BufferedImage PlayerBulletIm;

    private Graphics2D g2d;

    private JLabel statusbar;


    public Java2DFactory() {
        ScreenHeight = 500; // hier inlezen
        ScreenWidth = 500;


        System.out.println("jframe gemaakt");
        frame = new JFrame();
        panel = new JPanel(true){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        frame.setFocusable(true);
        frame.add(panel);
        frame.setTitle("space invaders Thijs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ScreenWidth, ScreenHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        statusbar = new JLabel(" 0");
        frame.add(statusbar, BorderLayout.SOUTH);
        //statusbar.setText(String.valueOf(Game.getScore())); -> kan niet hier, score bestaat nog niet, game is nog niet geinitialiseerd.


    }

    public void setGameDimensions(int GameWidth,int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int GameBulletWidth, int GameBulletHeight, int GameEnemyshipWidth, int GameEnemyshipHeight){
        this.GameWidth = GameWidth;
        this.GameHeight = GameHeight;
        factorx = ((double)ScreenWidth/GameWidth); // game / screen
        factory = ((double)ScreenHeight/GameHeight);
        System.out.println(factorx+ "   "+ factory);
        PlayershipWidth = (int) (GamePlayershipWidth*factorx);
        PlayershipHeigth = (int) (GamePlayershipHeight*factory);

        int PlayerBulletWidth =  (int) (GameBulletWidth*factorx);
        int PlayerBulletHeight = (int)(GameBulletHeight* factory);





        frame.setLocation(100,50);
        frame.setSize(ScreenWidth, ScreenHeight);
        loadImages();
        try {
            backgroundIm = resizeImage(backgroundIm, frame.getWidth(), frame.getHeight());
            PlayerShipIm = resizeImage(PlayerShipIm, PlayershipWidth, PlayershipHeigth);
            EnemyShipIm = resizeImage(EnemyShipIm, (int)(GameEnemyshipWidth * factorx), (int)(GameEnemyshipHeight*factory));
            PlayerBulletIm = resizeImage(PlayerBulletIm, PlayerBulletWidth, PlayerBulletHeight);
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        g2d.drawImage(backgroundIm,0, 0, null);

    }



    private void loadImages(){
        backgroundIm = null;
        try {
            backgroundIm = ImageIO.read(new File("src/resource/background.png"));
            PlayerShipIm = ImageIO.read(new File("src/resource/playership.png"));
            EnemyShipIm =  ImageIO.read(new File("src/resource/enemyship3.png"));
            PlayerBulletIm = ImageIO.read(new File("src/resource/playerbullet2.png"));
        } catch (IOException e) {
            System.out.println("Unable to load images!");
        }
    }


    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (g2d != null)
            g2d.drawImage(backgroundIm,0, 0, null);
    }



    public void render() {
        panel.repaint();
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }


    public BufferedImage getPlayerShipIm(){
        return PlayerShipIm;
    }

    public BufferedImage getEnemyShipIm(){
        return EnemyShipIm;
    }

    public BufferedImage getPlayerBulletIm(){
        return PlayerBulletIm;
    }






    public Playership createPlayership(){
        System.out.println(frame);
        return new Java2DPlayership(this);
    }
    public Java2DPlayerBullet createPlayerBullet(int dammage, int x, int y, int dx, int dy){
        return new Java2DPlayerBullet(this, dammage, x,y,dx,dy);
    }

    public EnemyShip createEnemyship(){
        return new Java2DEnemyShip(this);
    }

    public EnemyShip createEnemyship(int HP, int x, int y, int dx, int dy) {
        return new Java2DEnemyShip(this, HP,x, y, dx, dy);
    }

    public JFrame getFrame(){
        return frame;
    }

    public Graphics2D getG2d() {
        return g2d;
    }


    public int getPlayershipWidth() {
        return PlayershipWidth;
    }


    public int getPlayershipHeigth() {
        return PlayershipHeigth;
    }

    public int getGameWidth() {
        return GameWidth;
    }

    public int getGameHeight(){
        return GameHeight;
    }

    public double getFactorx(){
        return factorx;
    }
    public double getFactory(){
        return factory;
    }
}
