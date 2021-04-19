package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import javax.imageio.ImageIO;
import javax.swing.*;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Java2DFactory extends AbstractFactory {
    private int ScreenWidth;
    private int ScreenHeight;

    private int PlayershipWidth;
    private int PlayershipHeight;


    private int GameWidth;
    private int GameHeight;
    private int GameEnemyshipWidth;



    private double factorx;
    private double factory;



    public JFrame frame;
    public JPanel panel;

    public BufferedImage g2dimage;
    public BufferedImage backgroundIm;
    public BufferedImage PlayerShipIm;
    public BufferedImage EnemyShipIm;
    public BufferedImage PlayerBulletIm;
    public BufferedImage EnemyBulletIm;
    public BufferedImage FriendlyIm;
    public BufferedImage Bulletx2Im;
    public BufferedImage Bulletx3Im;
    public BufferedImage DamageBulletIm;
    public BufferedImage BoxDamageBulletIm;

    private Graphics2D g2d;

    private JLabel statusbar;
    private Font fontscore;
    private Font fonthp;
    private int score;
    private int hp;

    public Java2DFactory() {
        ScreenHeight = 500;
        ScreenWidth = 500;
        // read screen config file, if no values present, default values are used
        try {
            Scanner in = new Scanner(new File("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\Screenconfig.txt"));
            while(in.hasNextLine()) {
                String currentLine = in.nextLine();
                String[] words = currentLine.split(" ");     // the delimiter inside the quotes
                System.out.println(currentLine);
                if(words[0].equals("Screen:")){
                    ScreenHeight = Integer.parseInt(words[2]);
                    ScreenWidth = Integer.parseInt(words[1]);
                    System.out.println("screen");
                }
            }
        } catch (IOException ex) {
            Thread.currentThread().interrupt();
        }


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
        fontscore = new Font("TimesRoman", Font.PLAIN, 20);



    }

    public void setGameDimensions(int GameWidth,int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int GameBulletWidth, int GameBulletHeight, int GameEnemyshipWidth, int GameEnemyshipHeight, int boxWidth, int boxHeight,        int score){
        statusbar.setText(String.valueOf(score));
        this.GameWidth = GameWidth;
        this.GameHeight = GameHeight;
        this.GameEnemyshipWidth = GameEnemyshipWidth;
        factorx = ((double)ScreenWidth/GameWidth); // game / screen
        factory = ((double)ScreenHeight/GameHeight);
        System.out.println(factorx+ "   "+ factory);
        PlayershipWidth = (int) (GamePlayershipWidth*factorx);
        PlayershipHeight = (int) (GamePlayershipHeight*factory);

        int BulletWidth =  (int) (GameBulletWidth*factorx);
        int BulletHeight = (int)(GameBulletHeight* factory);

        frame.setLocation(100,50);
        frame.setSize(ScreenWidth+15, ScreenHeight+37); // correctie om heel het beeld te behouden
        loadImages();
        try {
            System.out.println("background: "+ frame.getWidth()+ "  "+ frame.getHeight());
            backgroundIm = resizeImage(backgroundIm, frame.getWidth(), frame.getHeight());
            PlayerShipIm = resizeImage(PlayerShipIm, PlayershipWidth, PlayershipHeight);
            EnemyShipIm = resizeImage(EnemyShipIm, (int)(GameEnemyshipWidth * factorx), (int)(GameEnemyshipHeight*factory));
            PlayerBulletIm = resizeImage(PlayerBulletIm, BulletWidth, BulletHeight);
            EnemyBulletIm = resizeImage(EnemyBulletIm, BulletWidth, BulletHeight);
            FriendlyIm = resizeImage(FriendlyIm, (int)(GameEnemyshipWidth * factorx), (int)(GameEnemyshipHeight*factory));
            Bulletx2Im = resizeImage(Bulletx2Im, (int)(boxWidth * factorx), (int)(boxHeight*factory));
            Bulletx3Im = resizeImage(Bulletx3Im, (int)(boxWidth * factorx), (int)(boxHeight*factory));
            DamageBulletIm = resizeImage(DamageBulletIm, BulletWidth, BulletHeight);
            BoxDamageBulletIm = resizeImage(BoxDamageBulletIm, (int)(boxWidth * factorx),(int)(boxHeight*factory));
            System.out.println("breedte " + GameBulletWidth+" * "+factorx);
            System.out.println("bulletbreedte: " + BulletWidth);
            System.out.println("lengtebullet"+ BulletHeight);
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        //g2d.drawImage(backgroundIm,0, 0, null);

        g2d.setFont(fontscore);
        g2d.setColor(new Color (255, 255, 255));






    }



    private void loadImages(){
        backgroundIm = null;
        try {
            backgroundIm = ImageIO.read(new File("src/resource/background.png"));
            PlayerShipIm = ImageIO.read(new File("src/resource/playership.png"));
            EnemyShipIm =  ImageIO.read(new File("src/resource/enemyship3.png"));
            PlayerBulletIm = ImageIO.read(new File("src/resource/playerbullet2.png"));
            EnemyBulletIm = ImageIO.read(new File("src/resource/enemybullet3.png"));
            FriendlyIm = ImageIO.read(new File("src/resource/friendly.png"));
            Bulletx2Im = ImageIO.read(new File("src/resource/bulletx2.png"));
            Bulletx3Im = ImageIO.read(new File("src/resource/bulletx3.png"));
            DamageBulletIm = ImageIO.read(new File("src/resource/bulletblue.png"));
            BoxDamageBulletIm = ImageIO.read(new File("src/resource/boxbulletblue.png"));
        } catch (IOException e) {
            System.out.println("Unable to load images!");
        }
    }


    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (g2d != null) {
            g2d.drawImage(backgroundIm, 0, 0, null);
            g2d.drawString("score: " + score, 10, 30);
            g2d.drawString("HP: " + hp, 100, 30);
        }
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

    public BufferedImage getEnemyBulletIm(){
        return EnemyBulletIm;
    }

    public BufferedImage getFriendlyIm(){
        return FriendlyIm;
    }

    public BufferedImage getBulletx2Im(){
        return Bulletx2Im;
    }
    public BufferedImage getBulletx3Im(){
        return Bulletx3Im;
    }
    public BufferedImage getDamageBulletIm(){ return DamageBulletIm;}
    public BufferedImage getBoxDamageBulletIm(){return BoxDamageBulletIm;}






    public Playership createPlayership(int playershipheight){
        return new Java2DPlayership(this,playershipheight);
    }
    public Java2DPlayerBullet createPlayerBullet(int damage, int x, int y, int dx, int dy){
        return new Java2DPlayerBullet(this, damage, x,y,dx,dy);
    }

    public EnemyShip createEnemyship(){
        return new Java2DEnemyShip(this);
    }

    public EnemyShip createEnemyship(int HP, int x, int y, int dx, int dy) {
        return new Java2DEnemyShip(this, HP,x, y, dx, dy);
    }

    public Java2DEnemyBullet createEnemyBullet(int damage, int x, int y, int dx, int dy){
        return new Java2DEnemyBullet(this,damage,x,y,dx,dy);
    }

    public Java2DFriendly createFriendly(){
        return new Java2DFriendly(this);
    }

    public Java2DBulletx createBulletx(int number){
        return new Java2DBulletx(this,number);
    }

    public Java2DBoxDamageBullet createBoxDamageBullet(){return new Java2DBoxDamageBullet(this);}

    public JFrame getFrame(){
        return frame;
    }

    public Graphics2D getG2d() {
        return g2d;
    }


    public int getPlayershipWidth() {
        return PlayershipWidth;
    }


    public int getPlayershipHeight() {
        return PlayershipHeight;
    }

    public int getEnemyshipWidth(){return GameEnemyshipWidth;}


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

    public void updatescore(int score){
        this.score = score;
        statusbar.setText(String.valueOf(score));
    }

    public void updatehp(int HP){
        this.hp = HP;
    }

}
