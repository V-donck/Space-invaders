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

/**
 * factory that creates all objects of Java2D
 */
public class Java2DFactory extends AbstractFactory {
    private int screenWidth;
    private int screenHeight;
    private double factorX;
    private double factorY;
    /**
     * frame to draw images
     */
    public JFrame frame;
    /**
     * panel to draw images
     */
    public JPanel panel;
    /**
     * image to create g2d
     */
    public BufferedImage g2dimage;
    /**
     * image of background
     */
    public BufferedImage backgroundIm;
    /**
     * image of PlayerShip
     */
    public BufferedImage playerShipIm;
    /**
     * image of EnemyShip
     */
    public BufferedImage enemyShipIm;
    /**
     * image of other EnemyShip
     */
    public BufferedImage enemyShip2Im;
    /**
     * image of PlayerBullet (1)
     */
    public BufferedImage playerBulletIm;
    /**
     * image of EnemyBullet (1)
     */
    public BufferedImage enemyBulletIm;
    /**
     * image of EnemyBullet (2)
     */
    public BufferedImage enemyBullet2Im;
    /**
     * image of Friendly
     */
    public BufferedImage friendlyIm;
    /**
     * image of bulletx2 box
     */
    public BufferedImage bulletx2Im;
    /**
     * image of bulletx3 box
     */
    public BufferedImage bulletx3Im;
    /**
     * image of PlayerBullet (2) : damageBullet
     */
    public BufferedImage damageBulletIm;
    /**
     * image of boxDamageBullet
     */
    public BufferedImage boxDamageBulletIm;
    public BufferedImage boxHpIm;
    private Graphics2D g2d;
    private Font fontScore;
    private int score;
    private int hp;
    private int level=1;
    private boolean isRunning = false;
    private String name = "";
    private double factorXScreen;
    private double factorYScreen;
    private Font text;
    private Font title;

    /**
     * creates Java2DFactory,
     * read screenconfigfile fore dimensions screen,
     * create frame
     */
    public Java2DFactory() {
        // default values
        screenHeight = 650;
        screenWidth = 500;
        // read screen config file, if no values present, default values are used
        try {
            Scanner in = new Scanner(new File("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\Screenconfig.txt"));
            while(in.hasNextLine()) {
                String currentLine = in.nextLine();
                String[] words = currentLine.split(" ");
                if(words[0].equals("Screen:")){
                    screenHeight = Integer.parseInt(words[2]);
                    screenWidth = Integer.parseInt(words[1]);
                }
            }
        } catch (IOException ex) {
            Thread.currentThread().interrupt();
        }
        factorXScreen = screenWidth/500.0;
        factorYScreen= screenHeight/650.0;
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
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fontScore = new Font("TimesRoman", Font.PLAIN, 20);
        text = new Font("Consolas", Font.PLAIN, (int)(factorXScreen*20));
        title = new Font("Consolas", Font.PLAIN, (int)(factorXScreen*50));
    }

    /**
     * set all dimensions right for visualising all components in right dimensions
     * @param gameWidth            set GameWidth
     * @param gameHeight           set GameHeight
     * @param gamePlayerShipWidth Width of playership in game
     * @param gamePlayerShipHeight Height of playership in game
     * @param gameBulletWidth width of bullet in game
     * @param gameBulletHeight height of bullet in game
     * @param gameEnemyShipWidth width of enemyship in game
     * @param gameEnemyShipHeight height of enemyship in game
     * @param boxWidth             width of bonusbox in game (Bulletx2, Bulletx3, BoxDamageBullet)
     * @param boxHeight            height of bonusbox in game (Bulletx2, Bulletx3, BoxDamageBullet)
     * @param score                set score
     */
    public void setGameDimensions(int gameWidth,int gameHeight, int gamePlayerShipWidth, int gamePlayerShipHeight, int gameBulletWidth, int gameBulletHeight, int gameEnemyShipWidth, int gameEnemyShipHeight, int boxWidth, int boxHeight, int score){
        factorX = ((double)screenWidth/gameWidth); // game / screen
        factorY = ((double)screenHeight/gameHeight);
        int BulletWidth =  (int) (gameBulletWidth*factorX);
        int BulletHeight = (int)(gameBulletHeight* factorY);
        frame.setLocation(100,50);
        frame.setSize(screenWidth+15, screenHeight+37); // correctie om heel het beeld te behouden
        loadImages();
        try {
            backgroundIm = resizeImage(backgroundIm, frame.getWidth(), frame.getHeight());
            playerShipIm = resizeImage(playerShipIm, (int) (gamePlayerShipWidth*factorX), (int) (gamePlayerShipHeight*factorY));
            enemyShipIm = resizeImage(enemyShipIm, (int)(gameEnemyShipWidth * factorX), (int)(gameEnemyShipHeight*factorY));
            enemyShip2Im = resizeImage(enemyShip2Im, (int)(gameEnemyShipWidth * factorX), (int)(gameEnemyShipHeight*factorY));
            playerBulletIm = resizeImage(playerBulletIm, BulletWidth, BulletHeight);
            enemyBulletIm = resizeImage(enemyBulletIm, BulletWidth, BulletHeight);
            enemyBullet2Im = resizeImage(enemyBullet2Im, BulletWidth, BulletHeight);
            friendlyIm = resizeImage(friendlyIm, (int)(gameEnemyShipWidth * factorX), (int)(gameEnemyShipHeight*factorY));
            bulletx2Im = resizeImage(bulletx2Im, (int)(boxWidth * factorX), (int)(boxHeight*factorY));
            bulletx3Im = resizeImage(bulletx3Im, (int)(boxWidth * factorX), (int)(boxHeight*factorY));
            damageBulletIm = resizeImage(damageBulletIm, BulletWidth, BulletHeight);
            boxDamageBulletIm = resizeImage(boxDamageBulletIm, (int)(boxWidth * factorX),(int)(boxHeight*factorY));
            boxHpIm = resizeImage(boxHpIm, (int)(boxWidth * factorX),(int)(boxHeight*factorY));
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        g2d.setFont(fontScore);
        g2d.setColor(new Color (255, 255, 255));
    }

    /**
     * load all images from their location
     */
    private void loadImages(){
        backgroundIm = null;
        try {
            backgroundIm = ImageIO.read(new File("src/resource/background.png"));
            playerShipIm = ImageIO.read(new File("src/resource/playership.png"));
            enemyShipIm =  ImageIO.read(new File("src/resource/enemyship.png"));
            enemyShip2Im =  ImageIO.read(new File("src/resource/enemyship2.png"));
            playerBulletIm = ImageIO.read(new File("src/resource/playerbullet.png"));
            enemyBulletIm = ImageIO.read(new File("src/resource/enemybullet.png"));
            enemyBullet2Im = ImageIO.read(new File("src/resource/purplebullet.png"));
            friendlyIm = ImageIO.read(new File("src/resource/friendly.png"));
            bulletx2Im = ImageIO.read(new File("src/resource/bulletx2.png"));
            bulletx3Im = ImageIO.read(new File("src/resource/bulletx3.png"));
            damageBulletIm = ImageIO.read(new File("src/resource/bulletblue.png"));
            boxDamageBulletIm = ImageIO.read(new File("src/resource/boxbulletblue.png"));
            boxHpIm = ImageIO.read(new File("src/resource/boxhp.png"));
        } catch (IOException e) {
            System.out.println("Unable to load images!");
        }
    }

    /**
     * draws background and some tekst
     * @param g Graphics
     */
    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (g2d != null) {
            g2d.drawImage(backgroundIm, 0, 0, null);
            if(isRunning) {
                g2d.setFont(fontScore);
                g2d.drawString("score: " + score, (int)(10*factorXScreen), (int)(30*factorYScreen));
                g2d.drawString("HP: " + hp, (int)(130*factorXScreen), (int)(30*factorYScreen));
                g2d.drawString("level: " + level, (int)(400*factorXScreen), (int)(30*factorYScreen));
            }
        }
    }

    /**
     * repaints panel
     */
    public void render() {
        panel.repaint();
    }

    /**
     * changes size of image
     * @param originalImage the original image
     * @param targetWidth the width of the new image
     * @param targetHeight height of new image
     * @return BufferedImage originalImage, but the size is changed
     */
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    /**
     * returns playerShipIm
     * @return BufferedImage playerShipIm
     */
    public BufferedImage getPlayerShipIm(){
        return playerShipIm;
    }

    /**
     * returns enemyShipIm
     * @return BufferedImage enemyShipIm
     */
    public BufferedImage getEnemyShipIm(){
        return enemyShipIm;
    }

    /**
     * returns enemyShipIm2
     * @return BufferedImage EnemyShipIm2
     */
    public BufferedImage getEnemyShip2Im(){
        return enemyShip2Im;
    }

    /**
     * returns playerBulletIm
     * @return BufferedImage playerBulletIm
     */
    public BufferedImage getPlayerBulletIm(){
        return playerBulletIm;
    }

    /**
     * returns enemyBulletIm
     * @return BufferedImage enemyBulletIm
     */
    public BufferedImage getEnemyBulletIm(){
        return enemyBulletIm;
    }

    /**
     * returns enemyBulletIm2
     * @return BufferedImage enemyBulletIm2
     */
    public BufferedImage getEnemyBullet2Im(){
        return enemyBullet2Im;
    }

    /**
     * returns friendlyIm
     * @return BufferedImage friendlyIm
     */
    public BufferedImage getFriendlyIm(){
        return friendlyIm;
    }

    /**
     * returns bulletx2Im
     * @return BufferedImage bulletx2Im
     */
    public BufferedImage getBulletx2Im(){
        return bulletx2Im;
    }

    /**
     * returns bulletx3Im
     * @return BufferedImage bulletx3Im
     */
    public BufferedImage getBulletx3Im(){
        return bulletx3Im;
    }

    /**
     * returns damageBulletIm
     * @return BufferedImage damageBulletIm
     */
    public BufferedImage getDamageBulletIm(){ return damageBulletIm;}

    /**
     * returns boxDamageBulletIm
     * @return BufferedImage boxDamageBulletIm
     */
    public BufferedImage getBoxDamageBulletIm(){return boxDamageBulletIm;}

    /**
     * returns boxHpIm
     * @return BufferedImage boxHpIm
     */
    public BufferedImage getBoxHpIm(){return boxHpIm;}

    /**
     * creates {@link Java2DPlayerShip}
     * @param playerShipHeight height of playership
     * @param gameWidth        width of game (for setting ship at right place)
     * @param gameHeight       height of game (for setting ship at right place)
     * @return {@link Java2DPlayerShip}
     */
    public PlayerShip createPlayerShip(int playerShipHeight, int gameWidth, int gameHeight){
        return new Java2DPlayerShip(this,playerShipHeight, gameWidth, gameHeight);
    }

    /**
     * creates {@link Java2DPlayerBullet}
     * @param damage the damage of the {@link Bullet}
     * @param x      x-coordinate for setting {@link MovementComponent}
     * @param y      y-coordinate for setting {@link MovementComponent}
     * @param dx     dx for setting {@link MovementComponent}
     * @param dy     dy for setting {@link MovementComponent}
     * @return {@link Java2DPlayerBullet}
     */
    public Java2DPlayerBullet createPlayerBullet(int damage, int x, int y, int dx, int dy){
        return new Java2DPlayerBullet(this, damage, x,y,dx,dy);
    }

    /**
     * creates {@link Java2DEnemyShip}
     * @param hp    hp of {@link Ship}
     * @param x     x-coordinate for setting {@link MovementComponent}
     * @param y     y-coordinate for setting {@link MovementComponent}
     * @param dx    dx for setting {@link MovementComponent}
     * @param dy    dy for setting {@link MovementComponent}
     * @param level level of {@link EnemyShip}
     * @return {@link Java2DEnemyShip}
     */
    public EnemyShip createEnemyShip(int hp, int x, int y, int dx, int dy,int level) {
        return new Java2DEnemyShip(this, hp,x, y, dx, dy, level);
    }

    /**
     * creates {@link Java2DEnemyBullet}
     * @param damage damage of {@link Bullet}
     * @param x      x-coordinate for setting {@link MovementComponent}
     * @param y      y-coordinate for setting {@link MovementComponent}
     * @param dx     dx for setting {@link MovementComponent}
     * @param dy     dy for setting {@link MovementComponent}
     * @return {@link Java2DEnemyBullet}
     */
    public Java2DEnemyBullet createEnemyBullet(int damage, int x, int y, int dx, int dy){
        return new Java2DEnemyBullet(this,damage,x,y,dx,dy);
    }

    /**
     * creates {@link Java2DFriendly}
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link Java2DFriendly}
     */
    public Java2DFriendly createFriendly(int xCoord){
        return new Java2DFriendly(this,xCoord);
    }

    /**
     * creates {@link Java2DBulletx}
     * @param number number of bullets 2 or 3
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link Java2DBulletx}
     */
    public Java2DBulletx createBulletx(int number,int xCoord){
        return new Java2DBulletx(this,number,xCoord);
    }

    /**
     * creates {@link Java2DBoxDamageBullet}
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link Java2DBoxDamageBullet}
     */
    public Java2DBoxDamageBullet createBoxDamageBullet(int xCoord){return new Java2DBoxDamageBullet(this, xCoord);}

    /**
     * creates {@link Java2DBoxHp}
     * @param xCoord x-coordinate for setting {@link MovementComponent}
     * @return {@link Java2DBoxHp}
     */
    public Java2DBoxHp createBoxHp(int xCoord,int hp){return new Java2DBoxHp(this, xCoord,hp);}

    /**
     * creates inputclass: {@link Java2DInput}
     * @return {@link Java2DInput}: returns created {@link Java2DInput}
     */
    public Java2DInput createInput(){
        return new Java2DInput(this);
    }

    /**
     * returns Frame
     * @return JFrame
     */
    public JFrame getFrame(){
        return frame;
    }

    /**
     * returns Graphics2D g2d
     * @return Graphics g2d
     */
    public Graphics2D getG2d() {
        return g2d;
    }

    /**
     * returns factorX
     * @return factorX
     */
    public double getFactorX(){
        return factorX;
    }

    /**
     * returns factorY
     * @return factorY
     */
    public double getFactorY(){
        return factorY;
    }

    /**
     * updates score
     * @param score : int score
     */
    public void updateScore(int score){
        this.score = score;
    }

    /**
     * updates hp
     * @param hp : int HP of PlayerShip
     */
    public void updateHP(int hp){
        this.hp = hp;
    }

    /**
     * set isRunning
     * @param ir: boolean isrunning
     */
    public void setIsRunning(boolean ir){
        this.isRunning = ir;
    }

    /**
     * creates startscreen
     */
    public void first(){
        // waardes zijn gegeven in 500,650 scherm
        Graphics2D g2d = this.getG2d();
        g2d.drawImage(backgroundIm,0,0,null);
        Font font = new Font("TimesRoman", Font.PLAIN, (int)(factorXScreen*50));
        g2d.setFont(font);
        g2d.drawString("Space Invaders", (int)(factorXScreen*83),(int)(factorYScreen*50));
        font = new Font("TimesRoman", Font.PLAIN, (int)(factorXScreen*20));
        g2d.setFont(font);
        g2d.drawString("Thijs Vanhooydonck",(int)(factorXScreen*166),(int)(factorYScreen*72));
        g2d.drawString("Press enter to start",(int)(factorXScreen*166),(int)(factorYScreen*630));
        g2d.drawImage(playerBulletIm,(int)(factorXScreen*50),(int)(factorYScreen*90),null);
        g2d.drawImage(enemyBulletIm,(int)(factorXScreen*33),(int)(factorYScreen*90),null);
        font = new Font("TimesRoman", Font.PLAIN, (int)(factorXScreen*15));
        g2d.setFont(font);
        g2d.drawString("bullets: 10 damage",(int)(factorXScreen*100),(int)(factorYScreen*110));
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*20),(int)(factorYScreen*140),null);
        g2d.drawImage(enemyShip2Im,(int)(factorXScreen*85),(int)(factorYScreen*140),null);
        g2d.drawString("Enemies: 30 HP, if killed 1 point",(int)(factorXScreen*150),(int)(factorYScreen*175));
        g2d.drawImage(playerShipIm,(int)(factorXScreen*20),(int)(factorYScreen*205),null);
        g2d.drawString("Playership; 100 HP",(int)(factorXScreen*100),(int)(factorYScreen*250));
        g2d.drawImage(friendlyIm,(int)(factorXScreen*20),(int)(factorYScreen*300),null);
        g2d.drawString("Friendly enemyship: just let them pass, don't shoot them,",(int)(factorXScreen*100),(int)(factorYScreen*320));
        g2d.drawString("because if you hit them, they will fire back",(int)(factorXScreen*100),(int)(factorYScreen*340));
        g2d.drawImage(boxDamageBulletIm,(int)(factorXScreen*25),(int)(factorYScreen*370),null);
        g2d.drawString("player shoots blue bullets",(int)(factorXScreen*100),(int)(factorYScreen*395));
        g2d.drawImage(damageBulletIm,(int)(factorXScreen*33),(int)(factorYScreen*425),null);
        g2d.drawImage(enemyBullet2Im,(int)(factorXScreen*50),(int)(factorYScreen*425),null);
        g2d.drawString("bullets: 50 damage",(int)(factorXScreen*100),(int)(factorYScreen*450));
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*20),(int)(factorYScreen*140),null);
        g2d.drawImage(enemyShip2Im,(int)(factorXScreen*85),(int)(factorYScreen*140),null);
        g2d.drawImage(bulletx2Im,(int)(factorXScreen*25),(int)(factorYScreen*480),null);
        g2d.drawString("shoot more bullets at a time",(int)(factorXScreen*150),(int)(factorYScreen*505));
        g2d.drawImage(bulletx3Im,(int)(factorXScreen*85),(int)(factorYScreen*480),null);
        g2d.drawImage(boxHpIm,(int)(factorXScreen*25),(int)(factorYScreen*540),null);
        g2d.drawString("+10 hp",(int)(factorXScreen*100),(int)(factorYScreen*560));
        this.render();
    }

    /**
     * creates scorebord on screen
     * @param scoreList ArrayList&lt;String&gt; list with names and scores
     */
    public void scorebord(ArrayList<String> scoreList){
        Graphics2D g2d = this.getG2d();
        g2d.drawImage(backgroundIm,0,0,null);
        g2d.setFont(text);
        for(int i =0;i<scoreList.size();i++){
            String line = scoreList.get(i);
            String[] words =line.split(" ");
            String spaces="";
            for(int j=0;j<(10-words[0].length());j++){
                spaces= spaces+" ";
            }
            if(i!=9){
            g2d.drawString(" "+(i+1)+ ": "+words[0]+ spaces+words[1], (int)(factorXScreen*30),(int)(factorYScreen*50*(i+1)+50*factorYScreen));
            }
            else{
                g2d.drawString((i+1)+ ": "+words[0]+ spaces+words[1], (int)(factorXScreen*30),(int)(factorYScreen*50*(i+1)+50*factorYScreen));
            }
        }
        g2d.setFont(title);
        g2d.drawString("Scorebord", (int)(factorXScreen*83),(int)(factorYScreen*50));
        g2d.drawImage(playerShipIm,(int)(factorXScreen*300),(int)(factorYScreen*500),null);
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*330),(int)(factorYScreen*200),null);
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*260),(int)(factorYScreen*200),null);
        g2d.drawImage(enemyBulletIm,(int)(factorXScreen*340),(int)(factorYScreen*300),null);
        g2d.drawImage(enemyBulletIm,(int)(factorXScreen*275),(int)(factorYScreen*350),null);
        g2d.drawImage(damageBulletIm,(int)(factorXScreen*320),(int)(factorYScreen*430),null);
        g2d.drawImage(friendlyIm,(int)(factorXScreen*400),(int)(factorYScreen*300),null);
        this.render();
    }

    /**
     * updates name that is been entered
     * @param name : string name
     */
    public void updateName(String name){
        this.name = name;
        readName();
    }

    /**
     * updates name, typed in to screen
     */
    public void readName(){
        Graphics2D g2d = this.getG2d();
        g2d.drawImage(backgroundIm,0,0,null);
        g2d.setFont(text);
        g2d.drawString("Type your name:", (int)(factorXScreen*50),(int)(factorYScreen*250));
        g2d.drawString(name, (int)(factorXScreen*50),(int)(factorYScreen*280));
        g2d.setFont(fontScore);
        g2d.drawString("score: " + score, (int)(10*factorXScreen), (int)(30*factorYScreen));
        g2d.drawString("HP: " + hp, (int)(130*factorXScreen), (int)(30*factorYScreen));
        g2d.drawString("level: " + level, (int)(400*factorXScreen), (int)(30*factorYScreen));
        g2d.setFont(text);
        g2d.drawString("press enter to continue", (int)(factorXScreen*30),(int)(factorYScreen*450));
        g2d.setFont(title);
        g2d.drawString("Game over", (int)(factorXScreen*83),(int)(factorYScreen*100));
        g2d.drawImage(playerShipIm,(int)(factorXScreen*300),(int)(factorYScreen*500),null);
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*330),(int)(factorYScreen*200),null);
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*260),(int)(factorYScreen*200),null);
        g2d.drawImage(enemyBulletIm,(int)(factorXScreen*340),(int)(factorYScreen*300),null);
        g2d.drawImage(enemyBulletIm,(int)(factorXScreen*275),(int)(factorYScreen*350),null);
        g2d.drawImage(damageBulletIm,(int)(factorXScreen*320),(int)(factorYScreen*430),null);
        g2d.drawImage(friendlyIm,(int)(factorXScreen*400),(int)(factorYScreen*300),null);
        this.render();
    }

    /**
     * creates gameover screen
     */
    public void gameover(){
        Graphics2D g2d = this.getG2d();
        g2d.drawImage(backgroundIm, 0, 0, null);
        g2d.setFont(fontScore);
        g2d.drawString("score: " + score, (int)(10*factorXScreen), (int)(30*factorYScreen));
        g2d.drawString("HP: " + hp, (int)(130*factorXScreen), (int)(30*factorYScreen));
        g2d.drawString("level: " + level, (int)(400*factorXScreen), (int)(30*factorYScreen));
        g2d.setFont(text);
        g2d.drawString("press enter to continue", (int)(factorXScreen*30),(int)(factorYScreen*450));
        g2d.setFont(title);
        g2d.drawString("Game over", (int)(factorXScreen*83),(int)(factorYScreen*100));
        g2d.drawImage(playerShipIm,(int)(factorXScreen*300),(int)(factorYScreen*500),null);
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*330),(int)(factorYScreen*200),null);
        g2d.drawImage(enemyShipIm,(int)(factorXScreen*260),(int)(factorYScreen*200),null);
        g2d.drawImage(enemyBulletIm,(int)(factorXScreen*340),(int)(factorYScreen*300),null);
        g2d.drawImage(enemyBulletIm,(int)(factorXScreen*275),(int)(factorYScreen*350),null);
        g2d.drawImage(damageBulletIm,(int)(factorXScreen*320),(int)(factorYScreen*430),null);
        g2d.drawImage(friendlyIm,(int)(factorXScreen*400),(int)(factorYScreen*300),null);
        this.render();
    }

    /**
     * updates level
     * @param level : int level
     */
    public void updateLevel(int level){
        this.level = level;
    }
}