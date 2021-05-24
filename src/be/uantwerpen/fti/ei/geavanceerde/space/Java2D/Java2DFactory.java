package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import javax.imageio.ImageIO;
import javax.swing.*;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Java2DFactory extends AbstractFactory {
    private int ScreenWidth;
    private int ScreenHeight;


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
    public BufferedImage EnemyShip2Im;
    public BufferedImage PlayerBulletIm;
    public BufferedImage EnemyBulletIm;
    public BufferedImage EnemyBullet2Im;
    public BufferedImage FriendlyIm;
    public BufferedImage Bulletx2Im;
    public BufferedImage Bulletx3Im;
    public BufferedImage DamageBulletIm;
    public BufferedImage BoxDamageBulletIm;

    private Graphics2D g2d;

    private Font fontscore;
    private int score;
    private int hp;
    private int level=1;
    private boolean isrunning = false;
    private String name = "";
    private double factorxscreen;
    private double factoryscreen;

    public Java2DFactory() {
        // default values
        ScreenHeight = 650;
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
        factorxscreen = ScreenWidth/500.0;
        factoryscreen= ScreenHeight/650.0;

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
        fontscore = new Font("TimesRoman", Font.PLAIN, 20);



    }

    public void setGameDimensions(int GameWidth,int GameHeight, int GamePlayershipWidth, int GamePlayershipHeight, int GameBulletWidth, int GameBulletHeight, int GameEnemyshipWidth, int GameEnemyshipHeight, int boxWidth, int boxHeight,        int score){
        this.GameWidth = GameWidth;
        this.GameHeight = GameHeight;
        this.GameEnemyshipWidth = GameEnemyshipWidth;
        factorx = ((double)ScreenWidth/GameWidth); // game / screen
        factory = ((double)ScreenHeight/GameHeight);
        System.out.println(factorx+ "   "+ factory);


        int BulletWidth =  (int) (GameBulletWidth*factorx);
        int BulletHeight = (int)(GameBulletHeight* factory);

        frame.setLocation(100,50);
        frame.setSize(ScreenWidth+15, ScreenHeight+37); // correctie om heel het beeld te behouden
        loadImages();
        try {
            System.out.println("background: "+ frame.getWidth()+ "  "+ frame.getHeight());
            backgroundIm = resizeImage(backgroundIm, frame.getWidth(), frame.getHeight());
            PlayerShipIm = resizeImage(PlayerShipIm, (int) (GamePlayershipWidth*factorx), (int) (GamePlayershipHeight*factory));
            EnemyShipIm = resizeImage(EnemyShipIm, (int)(GameEnemyshipWidth * factorx), (int)(GameEnemyshipHeight*factory));
            EnemyShip2Im = resizeImage(EnemyShip2Im, (int)(GameEnemyshipWidth * factorx), (int)(GameEnemyshipHeight*factory));
            PlayerBulletIm = resizeImage(PlayerBulletIm, BulletWidth, BulletHeight);
            EnemyBulletIm = resizeImage(EnemyBulletIm, BulletWidth, BulletHeight);
            EnemyBullet2Im = resizeImage(EnemyBullet2Im, BulletWidth, BulletHeight);
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
            EnemyShip2Im =  ImageIO.read(new File("src/resource/enemyship2.png"));
            PlayerBulletIm = ImageIO.read(new File("src/resource/playerbullet2.png"));
            EnemyBulletIm = ImageIO.read(new File("src/resource/enemybullet3.png"));
            EnemyBullet2Im = ImageIO.read(new File("src/resource/purplebullet.png"));
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
            if(isrunning) {
                g2d.setFont(fontscore);
                g2d.drawString("score: " + score, (int)(10*factorxscreen), (int)(30*factoryscreen));
                g2d.drawString("HP: " + hp, (int)(130*factorxscreen), (int)(30*factoryscreen));
                g2d.drawString("level: " + level, (int)(400*factorxscreen), (int)(30*factoryscreen));
            }
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
    public BufferedImage getEnemyShip2Im(){
        return EnemyShip2Im;
    }

    public BufferedImage getPlayerBulletIm(){
        return PlayerBulletIm;
    }

    public BufferedImage getEnemyBulletIm(){
        return EnemyBulletIm;
    }
    public BufferedImage getEnemyBullet2Im(){
        return EnemyBullet2Im;
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






    public PlayerShip createPlayerShip(int playershipHeight, int GameWidth, int GameHeight){
        return new Java2DPlayerShip(this,playershipHeight, GameWidth, GameHeight);
    }

    public Java2DPlayerBullet createPlayerBullet(int damage, int x, int y, int dx, int dy){
        return new Java2DPlayerBullet(this, damage, x,y,dx,dy);
    }
/*
    public EnemyShip createEnemyShip(){
        return new Java2DEnemyShip(this);
    }*/

    public EnemyShip createEnemyShip(int HP, int x, int y, int dx, int dy,int level) {
        return new Java2DEnemyShip(this, HP,x, y, dx, dy, level);
    }

    public Java2DEnemyBullet createEnemyBullet(int damage, int x, int y, int dx, int dy){
        return new Java2DEnemyBullet(this,damage,x,y,dx,dy);
    }

    public Java2DFriendly createFriendly(int xcoord){
        return new Java2DFriendly(this,xcoord);
    }

    public Java2DBulletx createBulletx(int number,int xcoord){
        return new Java2DBulletx(this,number,xcoord);
    }

    public Java2DBoxDamageBullet createBoxDamageBullet(int xcoord){return new Java2DBoxDamageBullet(this, xcoord);}

    public Java2DInput createInput(){
        return new Java2DInput(this);
    }

    public JFrame getFrame(){
        return frame;
    }

    public Graphics2D getG2d() {
        return g2d;
    }


    public double getFactorx(){
        return factorx;
    }
    public double getFactory(){
        return factory;
    }

    public void updateScore(int score){
        this.score = score;
    }

    public void updateHP(int HP){
        this.hp = HP;
    }

    public void setIsrunning(boolean ir){
        this.isrunning = ir;
    }


    public void first(){
        // waardes zijn gegeven in 500,650 scherm


        Graphics2D g2d = this.getG2d();
        g2d.drawImage(backgroundIm,0,0,null);
        Font font = new Font("TimesRoman", Font.PLAIN, (int)(factorxscreen*50));
        g2d.setFont(font);
        g2d.drawString("Space Invaders", (int)(factorxscreen*83),(int)(factoryscreen*50));
        font = new Font("TimesRoman", Font.PLAIN, (int)(factorxscreen*20));
        g2d.setFont(font);
        g2d.drawString("Thijs Vanhooydonck",(int)(factorxscreen*166),(int)(factoryscreen*72));
        g2d.drawString("Press enter to start",(int)(factorxscreen*166),(int)(factoryscreen*630));

        g2d.drawImage(PlayerBulletIm,(int)(factorxscreen*50),(int)(factoryscreen*90),null);
        g2d.drawImage(EnemyBulletIm,(int)(factorxscreen*33),(int)(factoryscreen*90),null);
        font = new Font("TimesRoman", Font.PLAIN, (int)(factorxscreen*15));
        g2d.setFont(font);
        g2d.drawString("bullets: 10 damage",(int)(factorxscreen*100),(int)(factoryscreen*110));
        g2d.drawImage(EnemyShipIm,(int)(factorxscreen*20),(int)(factoryscreen*140),null);
        g2d.drawString("Enemy: 50 HP, if killed 1 point",(int)(factorxscreen*100),(int)(factoryscreen*175));

        g2d.drawImage(PlayerShipIm,(int)(factorxscreen*20),(int)(factoryscreen*205),null);
        g2d.drawString("Playership; 100 HP",(int)(factorxscreen*100),(int)(factoryscreen*250));
        g2d.drawImage(FriendlyIm,(int)(factorxscreen*20),(int)(factoryscreen*300),null);
        g2d.drawString("Friendly enemyship: just let them pass, don't shoot them,",(int)(factorxscreen*100),(int)(factoryscreen*320));
        g2d.drawString("because then, they will fire back",(int)(factorxscreen*100),(int)(factoryscreen*340));

        g2d.drawImage(BoxDamageBulletIm,(int)(factorxscreen*25),(int)(factoryscreen*370),null);
        g2d.drawString("BoxDamageBullet: some time blue bullets",(int)(factorxscreen*100),(int)(factoryscreen*395));

        g2d.drawImage(DamageBulletIm,(int)(factorxscreen*39),(int)(factoryscreen*425),null);
        g2d.drawString("Blue bullet : 50 damage",(int)(factorxscreen*100),(int)(factoryscreen*450));

        g2d.drawImage(Bulletx2Im,(int)(factorxscreen*25),(int)(factoryscreen*480),null);
        g2d.drawString("2 bullets",(int)(factorxscreen*100),(int)(factoryscreen*505));
        g2d.drawImage(Bulletx3Im,(int)(factorxscreen*25),(int)(factoryscreen*540),null);
        g2d.drawString("3 bullets",(int)(factorxscreen*100),(int)(factoryscreen*560));

        this.render();
    }



    public void scorebord(ArrayList<String> scorelist){
        Graphics2D g2d = this.getG2d();
        g2d.drawImage(backgroundIm,0,0,null);


        //Font font = new Font("TimesRoman", Font.PLAIN, (int)(factorxF*50));
        //g2d.setFont(font);
        //g2d.drawString("Scorebord", (int)(factorxF*83),(int)(factoryF*50));
        Font font = new Font("Consolas", Font.PLAIN, (int)(factorxscreen*20));
        g2d.setFont(font);
        for(int i =0;i<scorelist.size();i++){
            String line = scorelist.get(i);
            String[] words =line.split(" ");
            String spaces="";
            for(int j=0;j<(10-words[0].length());j++){
                spaces= spaces+" ";
            }
            if(i!=9){
            g2d.drawString(" "+(i+1)+ ": "+words[0]+ spaces+words[1], (int)(factorxscreen*30),(int)(factoryscreen*50*(i+1)+50*factoryscreen));
            System.out.println(" "+(i+1)+ ": "+words[0]+ spaces+words[1]);
            }
            else{
                g2d.drawString((i+1)+ ": "+words[0]+ spaces+words[1], (int)(factorxscreen*30),(int)(factoryscreen*50*(i+1)+50*factoryscreen));
                System.out.println((i+1)+ ": "+words[0]+ spaces+words[1]);
            }
        }
        font = new Font("Consolas", Font.PLAIN, (int)(factorxscreen*50));
        g2d.setFont(font);
        g2d.drawString("Scorebord", (int)(factorxscreen*83),(int)(factoryscreen*50));
        g2d.drawImage(PlayerShipIm,(int)(factorxscreen*300),(int)(factoryscreen*500),null);
        g2d.drawImage(EnemyShipIm,(int)(factorxscreen*330),(int)(factoryscreen*200),null);
        g2d.drawImage(EnemyShipIm,(int)(factorxscreen*260),(int)(factoryscreen*200),null);
        g2d.drawImage(EnemyBulletIm,(int)(factorxscreen*340),(int)(factoryscreen*300),null);
        g2d.drawImage(EnemyBulletIm,(int)(factorxscreen*275),(int)(factoryscreen*350),null);
        g2d.drawImage(DamageBulletIm,(int)(factorxscreen*320),(int)(factoryscreen*430),null);
        g2d.drawImage(FriendlyIm,(int)(factorxscreen*400),(int)(factoryscreen*300),null);

        this.render();
    }


    public void updateName(String name){
        this.name = name;
        readName();
    }

    public void readName(){

        Graphics2D g2d = this.getG2d();

        g2d.drawImage(backgroundIm,0,0,null);

        Font font = new Font("Consolas", Font.PLAIN, (int)(factorxscreen*20));
        g2d.setFont(font);
        g2d.drawString("Type your name:", (int)(factorxscreen*50),(int)(factoryscreen*250));
        g2d.drawString(name, (int)(factorxscreen*50),(int)(factoryscreen*280));
        gameover();
    }



    public void gameover(){

        Graphics2D g2d = this.getG2d();

        Font font = new Font("Consolas", Font.PLAIN, (int)(factorxscreen*20));
        g2d.setFont(font);
        g2d.drawString("press enter to continue", (int)(factorxscreen*30),(int)(factoryscreen*450));

        font = new Font("Consolas", Font.PLAIN, (int)(factorxscreen*50));
        g2d.setFont(font);
        g2d.drawString("Game over", (int)(factorxscreen*83),(int)(factoryscreen*100));
        g2d.drawImage(PlayerShipIm,(int)(factorxscreen*300),(int)(factoryscreen*500),null);
        g2d.drawImage(EnemyShipIm,(int)(factorxscreen*330),(int)(factoryscreen*200),null);
        g2d.drawImage(EnemyShipIm,(int)(factorxscreen*260),(int)(factoryscreen*200),null);
        g2d.drawImage(EnemyBulletIm,(int)(factorxscreen*340),(int)(factoryscreen*300),null);
        g2d.drawImage(EnemyBulletIm,(int)(factorxscreen*275),(int)(factoryscreen*350),null);
        g2d.drawImage(DamageBulletIm,(int)(factorxscreen*320),(int)(factoryscreen*430),null);
        g2d.drawImage(FriendlyIm,(int)(factorxscreen*400),(int)(factoryscreen*300),null);

        this.render();
    }

    public void updateLevel(int level){
        this.level = level;
    }



}
