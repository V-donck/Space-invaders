package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;


import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import static be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.Input.Inputs.ENTER;

/**
 * Game class: this is the class where all the game calculations are made
 */
public class Game {
    private int gameHeight;
    private int gameWidth;
    private int playerShipWidth;
    private int playerShipHeight;
    private int bulletWidth;
    private int bulletHeight;
    private int enemyShipWidth;
    private int enemyShipHeight;
    private int boxHeight;
    private int boxWidth;
    private int delay;
    private final AbstractFactory F;
    private final MovementUpdater movup;
    private ArrayList<MovementComponent> listMov;
    private PlayerShip PS;
    private ArrayList<EnemyShip> ES;
    private ArrayList<PlayerBullet> PB;
    private ArrayList<EnemyBullet> EB;
    private int score;
    private final Input input;
    private Input.Inputs key;
    private boolean isRunning;
    private Friendly Fr;
    private EnemyShip FrEs;
    private boolean bonusActive = false;
    private Bulletx Bx;
    private BoxDamageBullet BDB;
    private BoxHp bhp;
    private Timer timer;
    private int TELLER_MAX;
    private int bullet2xTime = 0;
    private int bullet3xTime = 0;
    private int damageBulletTime = 0;
    private boolean EsHitSide= false;
    private int damageBullet = 10;
    private int level =1;

    /**
     * creates Game<br>
     * set some default values: score = 0<br>
     * set some default objects: create {@link MovementUpdater}<br>
     *create {@link Input}<br>
     * create {@link Timer}
     * @param f {@link AbstractFactory} for creating objects
     */
    public Game(AbstractFactory f) {
        F = f;
        movup = new MovementUpdater();
        score = 0;
        level = 1;
        input = F.createInput();
        isRunning = false;
        F.setIsRunning(isRunning);
        timer = new Timer();
    }

    /**
     * function to start game
     * set first some default values
     * read in game configfile
     * set gamedimensions to factory: {@link AbstractFactory}
     * create startscreen:
     * calls initialise
     * calls loop: gameloop
     */
    public void start(){
        // default values
        gameWidth = 10000;
        gameHeight = 10000;
        playerShipWidth = 1000;
        playerShipHeight = 1500;
        bulletWidth = 150;
        bulletHeight = 700;
        enemyShipWidth = 1000;
        enemyShipHeight = 1000;
        boxWidth = 750;
        boxHeight = 750;
        delay = 15;
        TELLER_MAX =1500;

        // read config file, if some values not present, standard values are used
        try {
            Scanner in = new Scanner(new File("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\Gameconfig.txt"));
            while(in.hasNextLine()) {
                String currentLine = in.nextLine();
                String[] words = currentLine.split(" ");
                switch(words[0]){
                    case ("Game:"):{
                        gameHeight = Integer.parseInt(words[2]);
                        gameWidth = Integer.parseInt(words[1]);
                        break;
                    }
                    case ("Playership:"):{
                        playerShipHeight = Integer.parseInt(words[2]);
                        playerShipWidth = Integer.parseInt(words[1]);
                        break;
                    }
                    case ("Bullet:"):{
                        bulletHeight = Integer.parseInt(words[2]);
                        bulletWidth = Integer.parseInt(words[1]);
                        break;
                    }
                    case ("Enemyship:"):{
                        enemyShipHeight = Integer.parseInt(words[2]);
                        enemyShipWidth = Integer.parseInt(words[1]);
                        break;
                    }
                    case("Box:"):{
                        boxHeight = Integer.parseInt(words[2]);
                        boxWidth = Integer.parseInt(words[1]);
                        break;
                    }
                    case ("Speed:"):{
                        double speed = Integer.parseInt(words[1]);
                        if(speed<10 & speed>0){
                            delay = (int) (30/speed);
                        }
                        break;
                    }
                }
            }
        }
        catch (IOException ex) {
            Thread.currentThread().interrupt();
        }

        // set dimensions
        F.setGameDimensions(gameWidth, gameHeight, playerShipWidth,playerShipHeight, bulletWidth, bulletHeight, enemyShipWidth, enemyShipHeight,boxWidth,boxHeight, score);
        first(); // create startscreen
        initialise(); // initialise all game components
        loop();
    }

    /**
     * Gameloop:
     * first initialise some values <br>
     * loop: <br>
     * - set some values<br>
     * - check inputs<br>
     * - check bonusses<br>
     * - Enemies shoot<br>
     * - call checkcollisions<br>
     * - update Movementcomponents<br>
     * - update score and HP<br>
     * - visualise components<br>
     * - delay
     */
    public void loop() {
        // initialise some variables
        int teller = 0;
        int randomvalue = (int) (Math.random()*TELLER_MAX);
        int randomvalue2;
        boolean canshoot= true;
        // loop
        while (isRunning) {
            timer.start();
            EsHitSide = false;
            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, 0); // PS stands still
            // create new enemyships if all enemys are killed
            if(ES.isEmpty()){
                level++;
                F.updateLevel(level);
                ES = createESList();
            }
            // if bonus damageBullet active:
            if(damageBulletTime>0){
                damageBulletTime--;
                damageBullet = 50;
            }
            else{
                damageBullet = 10;
            }
            // teller to delay some actions
            if(teller>=TELLER_MAX){
                teller=0;
                randomvalue = (int) (Math.random()*TELLER_MAX);
            }
            else{
                teller++;
            }
            // key inputs
            if (input.inputAvailable()) {
                key = input.getInput();
                switch (key) {
                    case LEFT:
                        if (PS.getMovementComponent().getxCoord() > 0)
                            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), -gameWidth / 100, 0); //dx default -100
                        else
                            PS.setMovementComponent(0, PS.getMovementComponent().getyCoord(), 0, 0);
                        break;
                    case RIGHT:
                        if (PS.getMovementComponent().getxCoord() + playerShipWidth < gameWidth)
                            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), gameWidth / 100, 0); // dx default 100
                        else
                            PS.setMovementComponent(gameWidth - playerShipWidth, PS.getMovementComponent().getyCoord(), 0, 0);
                        break;
                    // PS shoot
                    case UP:
                    case SPACE:
                        if (canshoot) {
                            PlayerShipShoot();
                            canshoot = false;
                        }
                        break;
                }
            }

            // bonus
            if(teller == randomvalue & !bonusActive){ // bonus
                randomvalue2 = (int) (Math.random()*6);
                // randomvalue2 = 0 -> no bonus
                if(randomvalue2 ==1) { // friendly
                    bonusActive = true;
                    Fr = F.createFriendly((int) (Math.random()*(gameWidth-enemyShipWidth)));
                    listMov.add(Fr.getMovementComponent());
                }
                else if(randomvalue2 == 2){ // bulletx2
                    bonusActive = true;
                    Bx = F.createBulletx(2,(int) (Math.random()*(gameWidth-boxWidth)));
                    listMov.add(Bx.getMovementComponent());
                }
                else if(randomvalue2 == 3){ // bulletx3
                    bonusActive = true;
                    Bx = F.createBulletx(3,(int) (Math.random()*(gameWidth-boxWidth)));
                    listMov.add(Bx.getMovementComponent());
                }
                else if(randomvalue2 == 4){ // damageBulletBox
                    bonusActive = true;
                    BDB = F.createBoxDamageBullet((int) (Math.random()*(gameWidth-boxWidth)));
                    listMov.add(BDB.getMovementComponent());
                }
                else if (randomvalue2 == 5){ // boxhp
                    bonusActive = true;
                    bhp = F.createBoxHp((int) (Math.random()*(gameWidth-boxWidth)),10);
                    listMov.add(bhp.getMovementComponent());
                }
            }

            // enemyship shoot
            if(teller%(int)(TELLER_MAX/7.5)==0){ // default 200
                EnemyShip es = ES.get((int) (Math.random()*ES.size()));
                EnemyBullet eb;
                if(es.getLevel()<3) {
                    eb = F.createEnemyBullet(10, es.getMovementComponent().getxCoord() +enemyShipWidth / 2, es.getMovementComponent().getyCoord() + enemyShipHeight, 0, gameHeight / 1000); //dy default 10
                }
                else{
                    eb = F.createEnemyBullet(50, es.getMovementComponent().getxCoord() + enemyShipWidth / 2, es.getMovementComponent().getyCoord() + enemyShipHeight, 0, gameHeight / 1000); //dy default 10
                }
                EB.add(eb);
                listMov.add(eb.getMovementComponent());
            }

            // FrEs shoot
            if(FrEs !=null & teller%(int)(TELLER_MAX/7.5)==0){ // default 200
                EnemyBullet eb = F.createEnemyBullet(20,FrEs.getMovementComponent().getxCoord()+enemyShipWidth/2,FrEs.getMovementComponent().getyCoord()+enemyShipHeight,0,gameHeight/1000); //dy default 10
                EB.add(eb);
                listMov.add(eb.getMovementComponent());
            }

            // PS canshoot
            if(teller%(TELLER_MAX/30)==0){ // default 50
                canshoot = true;
            }
            checkCollisions();
            movup.update(listMov); // update movements
            // update score en hp to screen
            F.updateScore(score);
            F.updateHP(PS.getHP());
            //visualise components
            visualise();
            timer.end(); // end timer
            timer.delay(delay);
        }
    }

    /**
     * get score
     * @return int score
     */
    public int getScore() {
        return score;
    }

    /**
     * creates list of EnemyShips and set them at the right position
     * @return ArrayList &lt;{@link EnemyShip}&gt;ES
     */
    public ArrayList<EnemyShip> createESList() {
        ArrayList<EnemyShip> ES = new ArrayList<>();
        if (level%10!=0) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 6; i++) {
                    EnemyShip es = F.createEnemyShip(30, (int) (i * 1.3 * playerShipWidth), enemyShipHeight + enemyShipHeight * j, gameWidth / 2000, 0, level); //dx default 5
                    ES.add(es);
                    listMov.add(es.getMovementComponent());
                }
            }
        }
        // special at level 10,20,30,...
        else{
                for (int j = 0; j<4;j++) {
                    for (int i = 0; i < 6; i++) {
                        EnemyShip es = F.createEnemyShip(30, (int) (i * 1.3 * playerShipWidth), enemyShipHeight+enemyShipHeight*j, gameWidth/2000, 0,((i*j)%2)*5); //dx default 5
                        ES.add(es);
                        listMov.add(es.getMovementComponent());
                    }
                }
            }
        return ES;
    }

    /**
     * set direction of {@link EnemyShip}
     * @param direction set dx to - or + of {@link EnemyShip}
     */
    public void enemyShipSetDirection(int direction){
        for (EnemyShip es : ES) {
            es.setMovementComponent(es.getMovementComponent().getxCoord() + direction * 2, es.getMovementComponent().getyCoord() + enemyShipHeight, direction * gameWidth / 2000, 0); // dx default -5 or 5
        }
    }

    /**
     * first: set startscreen and wait untill enter is pressed
     */
    public void first(){
        F.first();
        // check if enter is pressed before starting
        while(!isRunning){
            if (input.inputAvailable()) {
                key = input.getInput();
                if (key == ENTER) {
                    isRunning = true;
                    F.setIsRunning(true);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * if gameover, this function is called <br>
     * - clear all objects<br>
     * - read scorebord.txt<br>
     * - check if score is hight enough to be on scorebord<br>
     * - if yes: call readname<br>
     * - else call gameover<br>
     * - calls scorebord<br>
     */
    public void End(){
        isRunning = false;
        F.setIsRunning(false);
        // clear all elements
        PS = null;
        ES.clear();
        EB.clear();
        PB.clear();
        Bx = null;
        BDB = null;
        Fr = null;
        FrEs = null;
        bullet2xTime = 0;
        bullet3xTime = 0;
        damageBulletTime = 0;
        bhp = null;
        boolean added= false;
        String name;
        name = "name";
        ArrayList<String> scorelist= new ArrayList<>();
        try {
            //read scorebord.txt
            Scanner in = new Scanner(new File("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\scorebord.txt"));
            while(in.hasNextLine()) {
                String currentLine = in.nextLine();
                String[] words = currentLine.split(" ");
                if((score>Integer.parseInt(words[1]) & !added)){ //player on scorebord
                    name = readName(); // read name
                    scorelist.add(name + " " + score);
                    added = true;
                }
                scorelist.add(words[0]+ " "+words[1]);
            }
            if(!added & scorelist.size()<10){
                name = readName();
                scorelist.add(name + " "+ score);
            }
            while(added & scorelist.size() >10){
                scorelist.remove(10);
            }
            // write updated list back to scorebord.txt
            FileWriter myWriter = new FileWriter("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\scorebord.txt");
            for(String line: scorelist){
                myWriter.write(line+"\n");
            }
            myWriter.close();
        }
        catch (IOException ex) {
            Thread.currentThread().interrupt();
        }
        // player must not give his name -> make gameoverscreen
        if (name.equals("name")) {
            gameover();
        }
        scorebord(scorelist); // make scorebord
    }

    /**
     * gameover: calls F.gameover (create gameoverscreen)
     * waits untill enter is pressed.
     */
    public void gameover(){
        F.gameover();
        boolean keyinput = false;
        // wait for enter
        while (!keyinput) {
            if (input.inputAvailable()) {
                key = input.getInput();
                if(key == ENTER) {
                    keyinput = true;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * sends scorelist to factory for creating scorebord on screen <br>
     * restart after enter is pressed:<br>
     *     - initialise
     *     - loop
     *
     * @param scoreList list of names and scores
     */
    public void scorebord(ArrayList<String> scoreList){
        F.scorebord(scoreList); // make scorebord on screen
        F.render();
        // wait for enter before restart
        while(!isRunning){
            if (input.inputAvailable()) {
                key = input.getInput();
                if (key == ENTER) {
                    isRunning = true;
                    F.setIsRunning(isRunning);
                    initialise();
                    loop();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * reads name from input
     * @return string name
     */
    public String readName() {
        F.readName();
        boolean enter = false;
        String name="";
        while (!enter) {
            if (input.inputAvailable()) {
                key = input.getInput();
                switch (key) {
                    case A:
                        name = name + "A";
                        break;
                    case B:
                        name = name + "B";
                        break;
                    case C:
                        name = name + "C";
                        break;
                    case D:
                        name = name + "D";
                        break;
                    case E:
                        name = name + "E";
                        break;
                    case F:
                        name = name + "F";
                        break;
                    case G:
                        name = name + "G";
                        break;
                    case H:
                        name = name + "H";
                        break;
                    case I:
                        name = name + "I";
                        break;
                    case J:
                        name = name + "J";
                        break;
                    case K:
                        name = name + "K";
                        break;
                    case L:
                        name = name + "L";
                        break;
                    case M:
                        name = name + "M";
                        break;
                    case N:
                        name = name + "N";
                        break;
                    case O:
                        name = name + "O";
                        break;
                    case P:
                        name = name + "P";
                        break;
                    case Q:
                        name = name + "Q";
                        break;
                    case R:
                        name = name + "R";
                        break;
                    case S:
                        name = name + "S";
                        break;
                    case T:
                        name = name + "T";
                        break;
                    case U:
                        name = name + "U";
                        break;
                    case V:
                        name = name + "V";
                        break;
                    case W:
                        name = name + "W";
                        break;
                    case X:
                        name = name + "X";
                        break;
                    case Y:
                        name = name + "Y";
                        break;
                    case Z:
                        name = name + "Z";
                        break;
                    case BACKSPACE:
                        if(name.length()>0)
                        name = name.substring(0, name.length() - 1);
                        break;
                    case ENTER:
                        enter = true;
                        break;
                }
                // length of name max 9 characters
                if(name.length()>9) {
                    name = name.substring(0, name.length() - 1);
                }
            }
            F.updateName(name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        return name;
    }

    /**
     * initialise game objects
     */
    public void initialise(){
        score =0;
        level = 1;
        F.updateLevel(level);
        PS = F.createPlayerShip(playerShipHeight,gameWidth,gameHeight);
        PB = new ArrayList<>();
        EB = new ArrayList<>();
        isRunning = true;
        F.setIsRunning(isRunning);
        listMov = new ArrayList<>();
        listMov.add(PS.getMovementComponent());
        ES = createESList();
        movup.update(listMov);
        bonusActive = false;
    }

    /**
     * check collisions<br>
     *     -EnemyShip<br>
     *         + EnemyShip - PlayerShip<br>
     *         + EnemyShip - PlayerBullet<br>
     *         + EnemyShip - rand<br>
     *     -EnemyBullet<br>
     *         + EnemyBullet - PlayerShip<br>
     *         + EnemyBullet - PlayerBullet<br>
     *         + EnemyBullet - rand<br>
     *     -PlayerBullet<br>
     *         + Playerbullet - rand<br>
     *         + Playerbullet - Friendly<br>
     *         + PlayerBullet - FriendlyEnemyShip<br>
     *     -Friendly<br>
     *         + Friendly - PlayerShip<br>
     *         + Friendly - rand<br>
     *     -Bulletx<br>
     *         + Bulletx - PlayerShip<br>
     *         + Bulletx - rand<br>
     *     -BoxDamageBullet<br>
     *         + BoxDamageBullet - PlayerShip<br>
     *         + BoxDamageBullet - rand<br>
     *     -BoxHp<br>
     *         + BoxHp - PlayerShip<br>
     *         + BoxHp - rand<br>
     */
    public void checkCollisions(){
        int i,j;
        ArrayList<EnemyShip> listRemoveEs;
        listRemoveEs = new ArrayList<>();
        // collisions
        // collisions ES
        for(i=0; i<ES.size();i++){
            EnemyShip es = ES.get(i);
            // ES <-> PS
            if((((PS.getMovementComponent().getxCoord() + playerShipWidth)>es.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord()<(es.getMovementComponent().getxCoord() + enemyShipWidth) ))  &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord()+playerShipHeight)>es.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord()<(es.getMovementComponent().getyCoord()+enemyShipHeight)))){
                PS.setHP(0);
                F.updateHP(PS.getHP());
                End();
            }
            // ES <-> PB
            for(j=0; j<PB.size();j++){
                PlayerBullet pb = PB.get(j);
                if((((pb.getMovementComponent().getxCoord() + bulletWidth)>es.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(es.getMovementComponent().getxCoord() + enemyShipWidth) ))  &&   // x-coordinaten vallen samen
                        (((pb.getMovementComponent().getyCoord()+bulletHeight)>es.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(es.getMovementComponent().getyCoord()+enemyShipHeight)))){   // y-coordinaat vallen samen
                    es.setHP(es.getHP()-pb.getDamage());
                    PB.remove(j);
                    listMov.remove(pb.getMovementComponent());
                    j--;
                    if(es.getHP()<=0) {
                        score++;
                        F.updateScore(score);
                        listRemoveEs.add(es);
                    }
                }
            }
            // ES <-> rand
            if(!EsHitSide) {
                if (es.getMovementComponent().getxCoord() <= 0) { // left side
                    enemyShipSetDirection(1);
                    EsHitSide = true;
                }
                if (es.getMovementComponent().getxCoord() + enemyShipWidth >= gameWidth) { // right side
                    enemyShipSetDirection(-1);
                    EsHitSide = true;
                }
                if (es.getMovementComponent().getyCoord() + enemyShipHeight >= gameHeight) {  //bottom
                    PS.setHP(0);
                    F.updateHP(PS.getHP());
                    End();
                }
            }
        }
        //remove es
        for(EnemyShip es: listRemoveEs){
            ES.remove(es);
            listMov.remove(es.getMovementComponent());
        }
        listRemoveEs.clear();
        // collisions EB
        for(i = 0;i<EB.size();i++){
            EnemyBullet eb = EB.get(i);
            // EB <-> PS
            if ((eb.getMovementComponent().getxCoord()+bulletWidth>PS.getMovementComponent().getxCoord()&& eb.getMovementComponent().getxCoord()<PS.getMovementComponent().getxCoord()+playerShipWidth) &&
                    (eb.getMovementComponent().getyCoord()+bulletHeight>PS.getMovementComponent().getyCoord() && eb.getMovementComponent().getyCoord()<PS.getMovementComponent().getyCoord()+playerShipHeight)){
                PS.setHP(PS.getHP()-eb.getDamage());
                EB.remove(eb);
                listMov.remove(eb.getMovementComponent());
                i--;
                if(PS.getHP()<=0){
                    PS.setHP(0);
                    F.updateHP(PS.getHP());
                    End();
                }
            }
            // EB <-> PB
            for(j = 0; j<PB.size();j++) {
                PlayerBullet pb = PB.get(j);
                if ((eb.getMovementComponent().getxCoord() + bulletWidth > pb.getMovementComponent().getxCoord() && eb.getMovementComponent().getxCoord() < pb.getMovementComponent().getxCoord() + bulletWidth) &&
                        (eb.getMovementComponent().getyCoord() + bulletHeight > pb.getMovementComponent().getyCoord() && eb.getMovementComponent().getyCoord() < pb.getMovementComponent().getyCoord() + bulletHeight)) {
                    EB.remove(eb);
                    i--;
                    PB.remove(pb);
                    j--;
                }
            }
            // EB <-> rand
            if (eb.getMovementComponent().getyCoord() > gameHeight || eb.getMovementComponent().getyCoord() < -5) {
                EB.remove(eb);
                listMov.remove(eb.getMovementComponent());
                i--;
            }
        }
        // collision PB
        // PB <-> rand
        for(i=0;i<PB.size();i++){
            PlayerBullet pb = PB.get(i);
            if (pb.getMovementComponent().getyCoord() > gameHeight || pb.getMovementComponent().getyCoord() < -1*bulletHeight) {
                PB.remove(pb);
                listMov.remove(pb.getMovementComponent());
                i--;
            }
            // PB <-> Fr
            if(Fr!=null){
                if((((pb.getMovementComponent().getxCoord() + bulletWidth)>Fr.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(Fr.getMovementComponent().getxCoord() + enemyShipWidth) ))  &&   // x-coordinaten vallen samen
                        (((pb.getMovementComponent().getyCoord()+bulletHeight)>Fr.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(Fr.getMovementComponent().getyCoord()+enemyShipHeight)))) {
                    bonusActive = true;
                    score = score - 10;
                    F.updateScore(score);
                    listMov.remove(pb.getMovementComponent());
                    PB.remove(i);
                    i--;
                    pb = null;
                    // remove Friendly
                    listMov.remove(Fr.getMovementComponent());

                    // create Enemy
                    FrEs = F.createEnemyShip(50,Fr.getMovementComponent().getxCoord(),Fr.getMovementComponent().getyCoord(),0,0,1);
                    Fr = null;
                    listMov.add(FrEs.getMovementComponent());
                }
            }
            // PB <-> FrEs
            if(FrEs!=null & pb != null){
                if((((pb.getMovementComponent().getxCoord() + bulletWidth)>FrEs.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(FrEs.getMovementComponent().getxCoord() + enemyShipWidth) ))  &&   // x-coordinaten vallen samen
                        (((pb.getMovementComponent().getyCoord()+bulletHeight)>FrEs.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(FrEs.getMovementComponent().getyCoord()+enemyShipHeight)))) {
                    FrEs.setHP(FrEs.getHP()-pb.getDamage());
                    PB.remove(pb);
                    listMov.remove(pb.getMovementComponent());
                    i--;
                    if(FrEs.getHP()<=0) {
                        listMov.remove(FrEs.getMovementComponent());
                        FrEs = null;
                        bonusActive = false;
                    }
                }
            }
        }
        // collision bonus
        // collision Fr
        if(Fr != null) {
            // Fr <-> PS
            if((((PS.getMovementComponent().getxCoord() + playerShipWidth)>Fr.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord()<(Fr.getMovementComponent().getxCoord() + enemyShipWidth) ))  &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord()+playerShipHeight)>Fr.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord()<(Fr.getMovementComponent().getyCoord()+enemyShipHeight)))){
                PS.setHP(0);
                F.updateHP(PS.getHP());
                End();
            }
            // Fr <-> rand
            if (Fr.getMovementComponent().getyCoord() + enemyShipHeight >= gameHeight) {  //bottom
                listMov.remove(Fr.getMovementComponent());
                Fr = null;
                bonusActive = false;
            }
        }
        // collision Bx
        if(Bx != null) {
            // Bx <-> PS
            if ((((PS.getMovementComponent().getxCoord() + playerShipWidth) > Bx.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord() < (Bx.getMovementComponent().getxCoord() + boxWidth))) &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord() + playerShipHeight) > Bx.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord() < (Bx.getMovementComponent().getyCoord() + boxHeight)))) {
                if(Bx.getNumber()==2){
                    bullet2xTime = 50; // 50 bullets
                }
                else if(Bx.getNumber()==3){
                    bullet3xTime = 35; // 35 bullets
                }

                listMov.remove(Bx.getMovementComponent());
                Bx = null;
                bonusActive = false;
            }
        }
        // Bx <-> rand
        if(Bx != null){
            if (Bx.getMovementComponent().getyCoord() + boxHeight >= gameHeight) {  //bottom
                listMov.remove(Bx.getMovementComponent());
                Bx = null;
                bonusActive = false;
            }
        }
        // collisions BoxDamageBullet: BDB
        if(BDB != null) {
            // BDB <-> PS
            if ((((PS.getMovementComponent().getxCoord() + playerShipWidth) > BDB.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord() < (BDB.getMovementComponent().getxCoord() + boxWidth))) &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord() + playerShipHeight) > BDB.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord() < (BDB.getMovementComponent().getyCoord() + boxHeight)))) {
                damageBulletTime = 500;
                listMov.remove(BDB.getMovementComponent());
                BDB = null;
                bonusActive = false;
            }
        }
        // BDB <-> rand
        if(BDB != null){
            if (BDB.getMovementComponent().getyCoord() + boxHeight >= gameHeight) {  //bottom
                listMov.remove(BDB.getMovementComponent());
                BDB = null;
                bonusActive = false;
            }
        }
        // collisions BoxHp: bhp
        if(bhp != null) {
            // bhp <-> PS
            if ((((PS.getMovementComponent().getxCoord() + playerShipWidth) > bhp.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord() < (bhp.getMovementComponent().getxCoord() + boxWidth))) &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord() + playerShipHeight) > bhp.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord() < (bhp.getMovementComponent().getyCoord() + boxHeight)))) {
                if(PS.getHP()<100){
                    PS.setHP(PS.getHP()+bhp.getHp());
                }
                listMov.remove(bhp.getMovementComponent());
                bhp = null;
                bonusActive = false;
            }
        }
        // bhp <-> rand
        if(bhp != null){
            if (bhp.getMovementComponent().getyCoord() + boxHeight >= gameHeight) {  //bottom
                listMov.remove(bhp.getMovementComponent());
                bhp = null;
                bonusActive = false;
            }
        }
    }

    /**
     * visualise all objects
     */
    public void visualise(){
        // visualise Enemy ships
        for (EnemyShip es : ES) {
            es.visualise();
        }
        // visualise Player Bullets
        if (!PB.isEmpty()) {
            for (PlayerBullet pb : PB) {
                pb.visualise();
            }
        }
        //visualise Enemy bullets
        for (EnemyBullet eb :EB) {
            eb.visualise();
        }
        //Playership visualise
        PS.visualise();
        // Friendly visualise
        if(Fr!=null) {
            Fr.visualise();
        }
        if(FrEs!=null){
            FrEs.visualise();
        }
        if(Bx != null){
            Bx.visualise();
        }
        if(BDB != null){
            BDB.visualise();
        }
        if(bhp != null){
            bhp.visualise();
        }
        F.render();
    }

    /**
     * PlayerShip shoots: check if 1, 2 or 3 bullets, create {@link PlayerBullet}
     */
    public void PlayerShipShoot(){
        // bonus bullet3xTime active?
        if(bullet3xTime > 0){ // PS shoots 3 bullets
            bullet3xTime--;
            PlayerBullet playBul = F.createPlayerBullet(damageBullet, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -gameHeight/200); // dy default -50
            PB.add(playBul);
            listMov.add(playBul.getMovementComponent());
            playBul = F.createPlayerBullet(damageBullet, PS.getMovementComponent().getxCoord() + playerShipWidth, PS.getMovementComponent().getyCoord(), 0, -gameHeight/200); // dy default -50
            PB.add(playBul);
            listMov.add(playBul.getMovementComponent());
            playBul = F.createPlayerBullet(damageBullet, PS.getMovementComponent().getxCoord() + playerShipWidth/2, PS.getMovementComponent().getyCoord()-bulletHeight/4, 0, -gameHeight/200); // dy default -50
            PB.add(playBul);
            listMov.add(playBul.getMovementComponent());
        }
        // bonus bullet2x active?
        else if (bullet2xTime> 0) { // PS shoot 2 bullets
            bullet2xTime--;
            PlayerBullet playBul = F.createPlayerBullet(damageBullet, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -gameHeight/200); // dy default -50
            PB.add(playBul);
            listMov.add(playBul.getMovementComponent());
            playBul = F.createPlayerBullet(damageBullet, PS.getMovementComponent().getxCoord() + playerShipWidth, PS.getMovementComponent().getyCoord(), 0, -gameHeight/200); // dy default -50
            PB.add(playBul);
            listMov.add(playBul.getMovementComponent());
        }
        // PS shoot 1 bullet
        else {
            PlayerBullet playBul = F.createPlayerBullet(damageBullet, PS.getMovementComponent().getxCoord() + playerShipWidth / 2, PS.getMovementComponent().getyCoord(), 0, -gameHeight/200); // dy default -50
            PB.add(playBul);
            listMov.add(playBul.getMovementComponent());
        }
    }
}