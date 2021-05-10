package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;


import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import static be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.Input.Inputs.ENTER;

public class Game {
    private int GameHeight;
    private int GameWidth;
    private int PlayershipWidth;
    private int PlayershipHeight;
    private int BulletWidth;
    private int BulletHeight;
    private int EnemyshipWidth;
    private int EnemyshipHeight;
    private int boxHeight;
    private int boxWidth;
    private int delay;

    private final AbstractFactory F;
    private final MovementUpdater movup;
    private ArrayList<MovementComponent> listmov;
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
    private boolean bonusactive = false;
    private Bulletx Bx;
    private BoxDamageBullet BDB;
    private Timer timer;
    private int tellermax;


    private int bullet2xTime = 0;
    private int bullet3xTime = 0;
    private int damageBulletTime = 0;
    private boolean EsHitSide= false;
    int damagebullet = 10;
    int level =1;


    public Game(AbstractFactory f) {
        F = f;
        movup = new MovementUpdater();
        score = 0;
        level = 1;
        input = F.createInput();
        isRunning = false;
        F.setIsrunning(isRunning);
        timer = new Timer();
    }


    public void start(){
        // default values
        GameWidth = 10000;
        GameHeight = 10000;
        PlayershipWidth = 1000;
        PlayershipHeight = 1500;
        BulletWidth = 150;
        BulletHeight = 700;
        EnemyshipWidth = 1000;
        EnemyshipHeight = 1000;
        boxWidth = 750;
        boxHeight = 750;
        delay = 15;
        tellermax =1500;

        // read config file, if some values not present, standard values are used
        try {
        Scanner in = new Scanner(new File("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\Gameconfig.txt"));
        while(in.hasNextLine()) {
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" ");
            System.out.println(currentLine);
            switch(words[0]){
                case ("Game:"):{
                    System.out.println("game: "+ words[0]);
                    GameHeight = Integer.parseInt(words[2]);
                    GameWidth = Integer.parseInt(words[1]);
                    System.out.println("game"+ GameWidth);
                    break;
                }
                case ("Playership:"):{
                    System.out.println("ps: "+ words[0]);
                    PlayershipHeight = Integer.parseInt(words[2]);
                    PlayershipWidth = Integer.parseInt(words[1]);
                    System.out.println("playership"+ PlayershipWidth);
                    break;
                }
                case ("Bullet:"):{
                    System.out.println("b: "+ words[0]);
                    BulletHeight = Integer.parseInt(words[2]);
                    BulletWidth = Integer.parseInt(words[1]);
                    System.out.println("bullet");
                    break;
                }
                case ("Enemyship:"):{
                    System.out.println("es: "+ words[0]);
                    EnemyshipHeight = Integer.parseInt(words[2]);
                    EnemyshipWidth = Integer.parseInt(words[1]);
                    System.out.println("enemyship");
                    break;
                }
                case("Box:"):{
                    System.out.println("box: "+ words[0]);
                    boxHeight = Integer.parseInt(words[2]);
                    boxWidth = Integer.parseInt(words[1]);
                    System.out.println("box");
                    break;
                }
                case ("Speed:"):{
                    double speed = Integer.parseInt(words[1]);
                    if(speed<10 & speed>0){
                        delay = (int) (30/speed);
                    }
                    System.out.println("speed"+ delay);
                    break;
                }
            }
        }
        } catch (IOException ex) {
            Thread.currentThread().interrupt();
        }

        // set dimensions
        F.setGameDimensions(GameWidth, GameHeight, PlayershipWidth, PlayershipHeight, BulletWidth, BulletHeight, EnemyshipWidth, EnemyshipHeight,boxWidth,boxHeight, score);
        first(); // create startscreen
        initialise(); // initialise all game components
        loop();
    }



    void loop() {
        // initialise some variables
        int teller = 0;

        int randomvalue = (int) (Math.random()*tellermax);
        int randomvalue2;


        System.out.println("randomvalue" + randomvalue);
        boolean canshoot= true;

        // loop
        while (isRunning) {
            timer.start();
            EsHitSide = false;
            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, 0); // PS stands still

            // create new enemyships if all enemys are killed
            // hier levels inbrengen??
            if(ES.isEmpty()){
                ES = createESlist();
                level++;
                F.updateLevel(level);
            }
            // if bonus damagebullet active:
            if(damageBulletTime>0){
                damageBulletTime--;
                damagebullet = 50;
            }
            else{
                damagebullet = 10;
            }

            // teller to delay some actions
            if(teller>=tellermax){
                teller=0;
                randomvalue = (int) (Math.random()*tellermax);
                System.out.println("randomvalue loop" + randomvalue);
            }
            else{
                teller++;
            }



            // key inputs
            if (input.inputAvailable()) {
                key = input.getInput();
                if (key == Input.Inputs.E) {
                    isRunning = false;
                    F.setIsrunning(isRunning);
                    System.exit(0);
                } else {
                    isRunning = true;
                    F.setIsrunning(isRunning);
                    switch (key) {
                        case LEFT:
                            System.out.println("left");
                            if (PS.getMovementComponent().getxCoord() > 0)
                                PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), -GameWidth / 100, 0); //dx default -100
                            else
                                PS.setMovementComponent(0, PS.getMovementComponent().getyCoord(), 0, 0);
                            break;
                        case RIGHT:
                            System.out.println("right");
                            if (PS.getMovementComponent().getxCoord() + PlayershipWidth < GameWidth)//gamewidth-size ps )
                                PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), GameWidth / 100, 0); // dx default 100
                            else
                                PS.setMovementComponent(GameWidth - PlayershipWidth, PS.getMovementComponent().getyCoord(), 0, 0);
                            break;
                        case DOWN:
                            System.out.println("down");
                            System.out.println("x: " + PS.getMovementComponent().getxCoord());
                            System.out.println("y: " + PS.getMovementComponent().getyCoord());
                            System.out.println("shipleft " + (PS.getMovementComponent().getxCoord() + PlayershipWidth));
                            End();
                            break;
                        // PS shoot
                        case UP:
                            System.out.println("up");
                            if (canshoot) {
                                PlayershipShoot();
                                canshoot = false;
                            }
                            break;
                        case SPACE:
                            System.out.println("space");
                            if (canshoot) {
                                PlayershipShoot();
                                canshoot = false;
                            }
                            break;
                    }
                }

            }


            // bonus
            if(teller == randomvalue & !bonusactive){ // bonus
                System.out.println("teller = randomvalue");
                randomvalue2 =  (int) (Math.random()*5);
                System.out.println("randomvalue2: " + randomvalue2);
                // randomvalue2 = 0 -> no bonus
                if(randomvalue2 ==1) { // friendly
                    bonusactive = true;
                    Fr = F.createFriendly((int) (Math.random()*(GameWidth)-EnemyshipWidth));
                    System.out.println("create friendly");
                    listmov.add(Fr.getMovementComponent());
                }
                else if(randomvalue2 == 2){ // bulletx2
                    bonusactive = true;
                    Bx = F.createBulletx(2,(int) (Math.random()*GameWidth));
                    System.out.println("create bx2");
                    listmov.add(Bx.getMovementComponent());
                }
                else if(randomvalue2 == 3){ // bulletx3
                    bonusactive = true;
                    Bx = F.createBulletx(3,(int) (Math.random()*GameWidth));
                    System.out.println("create bx3");
                    listmov.add(Bx.getMovementComponent());
                }
                else if(randomvalue2 == 4){ // damagebulletbox
                    bonusactive = true;
                    BDB = F.createBoxDamageBullet((int) (Math.random()*GameWidth));
                    System.out.println("created boxdamagebullet");
                    listmov.add(BDB.getMovementComponent());
                }

            }

            // enemyship shoot
            if(teller%(int)(tellermax/7.5)==0){ // default 200
                System.out.println("enemy: fire");
                EnemyShip es = ES.get((int) (Math.random()*ES.size()));
                EnemyBullet eb;
                if(es.getLevel()<3) {
                    eb = F.createEnemyBullet(10, es.getMovementComponent().getxCoord() + EnemyshipWidth / 2, es.getMovementComponent().getyCoord() + EnemyshipHeight, 0, GameHeight / 1000); //dy default 10
                }
                else{
                    eb = F.createEnemyBullet(50, es.getMovementComponent().getxCoord() + EnemyshipWidth / 2, es.getMovementComponent().getyCoord() + EnemyshipHeight, 0, GameHeight / 1000); //dy default 10
                }
                EB.add(eb);
                listmov.add(eb.getMovementComponent());
            }

            // FrEs shoot
            if(FrEs !=null & teller%(int)(tellermax/7.5)==0){ // default 200
                System.out.println("Frenemy: fire");
                EnemyBullet eb = F.createEnemyBullet(20,FrEs.getMovementComponent().getxCoord()+EnemyshipWidth/2,FrEs.getMovementComponent().getyCoord()+EnemyshipHeight,0,GameHeight/1000); //dy default 10
                EB.add(eb);
                listmov.add(eb.getMovementComponent());
            }

            // PS canshoot
            if(teller%(tellermax/30)==0){ // default 50
                canshoot = true;
            }


            checkcollisions();



            movup.update(listmov); // update movements
            // update score en hp to screen
            F.updateScore(score);
            F.updateHP(PS.getHP());

            //visualise components
            visualise();


            timer.end(); // end timer
            timer.delay(delay);

        }


    }

    public int getScore() {
        return score;
    }


    public ArrayList<EnemyShip> createESlist() {
        ArrayList<EnemyShip> ES = new ArrayList<>();
        for (int j = 0; j<3;j++) {
            for (int i = 0; i < 6; i++) {
                EnemyShip es = F.createEnemyShip(30, (int) (i * 1.3 * PlayershipWidth), EnemyshipHeight+EnemyshipHeight*j, GameWidth/2000, 0,level); //dx default 5
                ES.add(es);
                listmov.add(es.getMovementComponent());
            }
        }
        return ES;
    }

    public void Enemyshipsetdirection(int direction){
        for(int i = 0; i<ES.size();i++){
            EnemyShip es = ES.get(i);
            es.setMovementComponent(es.getMovementComponent().getxCoord()+direction*2,es.getMovementComponent().getyCoord()+EnemyshipHeight,direction*GameWidth/2000,0); // dx default -5 or 5
        }
    }


    public void first(){
        F.first();
        // check if enter is pressed before starting
        while(!isRunning){
            if (input.inputAvailable()) {
                key = input.getInput();
                System.out.println(key);
                if (key == ENTER) {
                    isRunning = true;
                    F.setIsrunning(true);
                    System.out.println("starting:::::");
                }
                else{
                    System.out.println("wait");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void End(){
        isRunning = false;
        F.setIsrunning(false);
        boolean added= false;
        String name;
        name = "name";
        System.out.println("END");
        System.out.println("Game over");

        ArrayList<String> scorelist= new ArrayList<>();


        try {
            //read scorebord.txt
            System.out.println("read: ");
            Scanner in = new Scanner(new File("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\scorebord.txt"));
            while(in.hasNextLine()) {
                String currentLine = in.nextLine();
                String[] words = currentLine.split(" ");
                System.out.println(currentLine);

                if((score>Integer.parseInt(words[1]) & !added)){ //player on scorebord
                    name = readname(); // read name
                    System.out.println(score + " ;;; "+ Integer.parseInt(words[1]));
                    scorelist.add(name + " " + score);
                    added = true;
                }
                scorelist.add(words[0]+ " "+words[1]);
            }
            if(!added & scorelist.size()<10){
                name = readname();
                System.out.println("ofwel slechtste ofwel eerste");
                scorelist.add(name + " "+ score);
            }
            while(added & scorelist.size() >10){
                scorelist.remove(10);
            }


            // write updated list back to scorebord.txt
            System.out.println("write: ");
            FileWriter myWriter = new FileWriter("C:\\Users\\thijs\\IdeaProjects\\projecttest\\Space-invaders\\src\\resource\\scorebord.txt");
            for(String line: scorelist){
                System.out.println(line);
                myWriter.write(line+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException ex) {
            Thread.currentThread().interrupt();
        }
        // player must give his name -> make gameoverscreen
        if (name.equals("name")) {
            gameover();
        }
        // clear all elements
        PS = null;
        ES.clear();
        EB.clear();
        PB.clear();
        Bx = null;
        BDB = null;
        Fr = null;
        FrEs = null;


        scorebord(scorelist); // make scorebord
    }

    public void gameover(){
        System.out.println("gameoverfuncite");
        F.gameover();
        boolean keyinput = false;
        // wait for enter
        while (!keyinput) {
            if (input.inputAvailable()) {
                key = input.getInput();
                System.out.println(key);
                if(key == ENTER) {
                    keyinput = true;
                    System.out.println("verder!!!");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void scorebord(ArrayList<String> scorelist){
        F.scorebord(scorelist); // make scorebord on screen
        F.render();
        System.out.println("uit java2d. scorebord");
        // wait fo renter before restart
        while(!isRunning){
            if (input.inputAvailable()) {
                key = input.getInput();
                System.out.println("pressed key");
                System.out.println(key);
                if (key == ENTER) {
                    isRunning = true;
                    F.setIsrunning(isRunning);
                    System.out.println("starting:::::");
                    initialise();
                    loop();
                }
                else{
                    System.out.println("wait");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }


// read name
    public String readname() {
        F.readName();
        System.out.println("readname");
        boolean enter = false;
        String name="";
        while (!enter) {
            if (input.inputAvailable()) {
                key = input.getInput();
                System.out.println(key);
                switch (key) {
                    case A:
                        System.out.println("A");
                        name = name + "A";
                        break;
                    case B:
                        System.out.println("B");
                        name = name + "B";
                        break;
                    case C:
                        System.out.println("C");
                        name = name + "C";
                        break;
                    case D:
                        System.out.println("D");
                        name = name + "D";
                        break;
                    case E:
                        System.out.println("E");
                        name = name + "E";
                        break;
                    case F:
                        System.out.println("F");
                        name = name + "F";
                        break;
                    case G:
                        System.out.println("G");
                        name = name + "G";
                        break;
                    case H:
                        System.out.println("H");
                        name = name + "H";
                        break;
                    case I:
                        System.out.println("I");
                        name = name + "I";
                        break;
                    case J:
                        System.out.println("J");
                        name = name + "J";
                        break;
                    case K:
                        System.out.println("K");
                        name = name + "K";
                        break;
                    case L:
                        System.out.println("L");
                        name = name + "L";
                        break;
                    case M:
                        System.out.println("M");
                        name = name + "M";
                        break;
                    case N:
                        System.out.println("N");
                        name = name + "N";
                        break;
                    case O:
                        System.out.println("O");
                        name = name + "O";
                        break;
                    case P:
                        System.out.println("P");
                        name = name + "P";
                        break;
                    case Q:
                        System.out.println("Q");
                        name = name + "Q";
                        break;
                    case R:
                        System.out.println("R");
                        name = name + "R";
                        break;
                    case S:
                        System.out.println("S");
                        name = name + "S";
                        break;
                    case T:
                        System.out.println("T");
                        name = name + "T";
                        break;
                    case U:
                        System.out.println("U");
                        name = name + "U";
                        break;
                    case V:
                        System.out.println("V");
                        name = name + "V";
                        break;
                    case W:
                        System.out.println("W");
                        name = name + "W";
                        break;
                    case X:
                        System.out.println("X");
                        name = name + "X";
                        break;
                    case Y:
                        System.out.println("Y");
                        name = name + "Y";
                        break;
                    case Z:
                        System.out.println("Z");
                        name = name + "Z";
                        break;
                    case BACKSPACE:
                        System.out.println("Backspace");
                        if(name.length()>0)
                        name = name.substring(0, name.length() - 1);
                        break;
                    case ENTER:
                        System.out.println("enter");
                        enter = true;
                        break;
                }
                // length of name max 9 characters
                if(name.length()>9) {
                    System.out.println("Backspace");
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

// initialise game objects
    public void initialise(){
        score =0;
        level = 1;
        F.updateLevel(level);
        PS = F.createPlayerShip(PlayershipHeight,PlayershipWidth,GameWidth,GameHeight);
        PB = new ArrayList<>();
        EB = new ArrayList<>();
        isRunning = true;
        F.setIsrunning(isRunning);
        listmov = new ArrayList<>();
        listmov.add(PS.getMovementComponent());
        ES = createESlist();
        movup.update(listmov);
    }

    // check collisions
    public void checkcollisions(){
        int i,j;
        ArrayList<EnemyShip> listremovees;
        listremovees = new ArrayList<>();
        // collisions
        // collisions ES
        for(i=0; i<ES.size();i++){
            EnemyShip es = ES.get(i);
            // ES <-> PS
            if((((PS.getMovementComponent().getxCoord() + PlayershipWidth)>es.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord()<(es.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord()+PlayershipHeight)>es.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord()<(es.getMovementComponent().getyCoord()+EnemyshipHeight)))){
                PS.setHP(0);
                System.out.println("game over");
                End();
            }

            // ES <-> PB
            for(j=0; j<PB.size();j++){
                PlayerBullet pb = PB.get(j);
                if((((pb.getMovementComponent().getxCoord() + BulletWidth)>es.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(es.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                        (((pb.getMovementComponent().getyCoord()+BulletHeight)>es.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(es.getMovementComponent().getyCoord()+EnemyshipHeight)))){   // y-coordinaat vallen samen
                    System.out.println("hit");
                    es.setHP(es.getHP()-pb.getDamage());
                    PB.remove(j);
                    listmov.remove(pb.getMovementComponent());
                    j--;
                    System.out.println(es.getHP());
                    if(es.getHP()<=0) {
                        System.out.println("ES destroyed");
                        score++;
                        F.updateScore(score);
                        listremovees.add(es);
                        System.out.println("added es to listremovees");
                    }


                }
            }
            // ES <-> rand
            if(!EsHitSide) {
                if (es.getMovementComponent().getxCoord() <= 0) { // left side
                    Enemyshipsetdirection(1);
                    EsHitSide = true;
                }
                if (es.getMovementComponent().getxCoord() + EnemyshipWidth >= GameWidth) { // right side
                    Enemyshipsetdirection(-1);
                    EsHitSide = true;
                }
                if (es.getMovementComponent().getyCoord() + EnemyshipHeight >= GameHeight) {  //bottom
                    System.out.println("Enemy ship passed");
                    System.out.println("game over");
                    End();
                }
            }


        }
        //remove es
        for(EnemyShip es: listremovees){
            System.out.println("enemyship in listremovees");
            ES.remove(es);
            listmov.remove(es.getMovementComponent());
        }
        listremovees.clear();

        // collisions EB
        for(i = 0;i<EB.size();i++){
            EnemyBullet eb = EB.get(i);


            // EB <-> PS
            if ((eb.getMovementComponent().getxCoord()+BulletWidth>PS.getMovementComponent().getxCoord()&& eb.getMovementComponent().getxCoord()<PS.getMovementComponent().getxCoord()+PlayershipWidth) &&
                    (eb.getMovementComponent().getyCoord()+BulletHeight>PS.getMovementComponent().getyCoord() && eb.getMovementComponent().getyCoord()<PS.getMovementComponent().getyCoord()+PlayershipHeight)){
                System.out.println("hit playership");
                PS.setHP(PS.getHP()-eb.getDamage());
                EB.remove(eb);
                listmov.remove(eb.getMovementComponent());
                i--;
                if(PS.getHP()<=0){
                    System.out.println("destroyed ps: game over");
                    End();
                }
            }

            // EB <-> PB
            for(j = 0; j<PB.size();j++) {
                PlayerBullet pb = PB.get(j);
                if ((eb.getMovementComponent().getxCoord() + BulletWidth > pb.getMovementComponent().getxCoord() && eb.getMovementComponent().getxCoord() < pb.getMovementComponent().getxCoord() + BulletWidth) &&
                        (eb.getMovementComponent().getyCoord() + BulletHeight > pb.getMovementComponent().getyCoord() && eb.getMovementComponent().getyCoord() < pb.getMovementComponent().getyCoord() + BulletHeight)) {
                    System.out.println("destroy bullet");
                    EB.remove(eb);
                    i--;
                    PB.remove(pb);
                    j--;
                }
            }

            // EB <-> rand
            if (eb.getMovementComponent().getyCoord() > GameHeight || eb.getMovementComponent().getyCoord() < -5) {
                EB.remove(eb);
                listmov.remove(eb.getMovementComponent());
                System.out.println(i);
                i--;
            }

        }

        // collision PB
        // PB <-> rand
        for(i=0;i<PB.size();i++){
            PlayerBullet pb = PB.get(i);
            if (pb.getMovementComponent().getyCoord() > GameHeight || pb.getMovementComponent().getyCoord() < -1*BulletHeight) {
                PB.remove(pb);
                listmov.remove(pb.getMovementComponent());
                i--;
                System.out.println("bulletrand");
            }
            // PB <-> Fr
            if(Fr!=null){
                if((((pb.getMovementComponent().getxCoord() + BulletWidth)>Fr.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(Fr.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                        (((pb.getMovementComponent().getyCoord()+BulletHeight)>Fr.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(Fr.getMovementComponent().getyCoord()+EnemyshipHeight)))) {
                    bonusactive = true;
                    System.out.println("hit friendly");
                    score = score - 10;
                    F.updateScore(score);
                    System.out.println("score : "+ score);

                    listmov.remove(pb.getMovementComponent());
                    PB.remove(i);
                    i--;
                    pb = null;
                    // remove Friendly
                    listmov.remove(Fr.getMovementComponent());

                    // create Enemy
                    FrEs = F.createEnemyShip(50,Fr.getMovementComponent().getxCoord(),Fr.getMovementComponent().getyCoord(),0,0,1);
                    Fr = null;
                    listmov.add(FrEs.getMovementComponent());
                }
            }
            // PB <-> FrEs
            if(FrEs!=null & pb != null){
                if((((pb.getMovementComponent().getxCoord() + BulletWidth)>FrEs.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(FrEs.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                        (((pb.getMovementComponent().getyCoord()+BulletHeight)>FrEs.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(FrEs.getMovementComponent().getyCoord()+EnemyshipHeight)))) {
                    System.out.println("hit Frenemy");
                    FrEs.setHP(FrEs.getHP()-pb.getDamage());
                    PB.remove(pb);
                    listmov.remove(pb.getMovementComponent());
                    i--;
                    System.out.println(FrEs.getHP());
                    if(FrEs.getHP()<=0) {
                        System.out.println("destroyed Frenemy");
                        listmov.remove(FrEs.getMovementComponent());
                        FrEs = null;
                        bonusactive = false;
                    }
                }
            }
        }
        // collision bonus

        // collision Fr
        if(Fr != null) {
            // Fr <-> PS
            if((((PS.getMovementComponent().getxCoord() + PlayershipWidth)>Fr.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord()<(Fr.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord()+PlayershipHeight)>Fr.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord()<(Fr.getMovementComponent().getyCoord()+EnemyshipHeight)))){
                PS.setHP(0);
                System.out.println("game over");
                End();
            }
            // Fr <-> rand
            if (Fr.getMovementComponent().getyCoord() + EnemyshipHeight >= GameHeight) {  //bottom
                System.out.println("Friendly Enemy ship passed");
                listmov.remove(Fr.getMovementComponent());
                Fr = null;
                bonusactive = false;
            }

        }

        // collision Bx
        if(Bx != null) {
            // Bx <-> PS
            if ((((PS.getMovementComponent().getxCoord() + PlayershipWidth) > Bx.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord() < (Bx.getMovementComponent().getxCoord() + boxWidth))) &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord() + PlayershipHeight) > Bx.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord() < (Bx.getMovementComponent().getyCoord() + boxHeight)))) {
                if(Bx.getNumber()==2){
                    bullet2xTime = 50; // 50 bullets
                    System.out.println("box bx2 taken");
                }
                else if(Bx.getNumber()==3){
                    bullet3xTime = 35; // 35 bullets
                    System.out.println("box bx3 taken");
                }

                listmov.remove(Bx.getMovementComponent());
                Bx = null;
                bonusactive = false;

            }
        }
        // Bx <-> rand
        if(Bx != null){
            if (Bx.getMovementComponent().getyCoord() + boxHeight >= GameHeight) {  //bottom
                System.out.println("box bx2 passed");
                listmov.remove(Bx.getMovementComponent());
                Bx = null;
                bonusactive = false;
            }
        }

        // collisions BoxDamageBullet: BDB
        if(BDB != null) {
            // BDB <-> PS
            if ((((PS.getMovementComponent().getxCoord() + PlayershipWidth) > BDB.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord() < (BDB.getMovementComponent().getxCoord() + boxWidth))) &&   // x-coordinaten vallen samen
                    (((PS.getMovementComponent().getyCoord() + PlayershipHeight) > BDB.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord() < (BDB.getMovementComponent().getyCoord() + boxHeight)))) {
                damageBulletTime = 500;
                System.out.println("collision BDB PS time: "+ damageBulletTime);
                listmov.remove(BDB.getMovementComponent());
                BDB = null;
                bonusactive = false;

            }
        }
        // BDB <-> rand
        if(BDB != null){
            if (BDB.getMovementComponent().getyCoord() + boxHeight >= GameHeight) {  //bottom
                System.out.println("box blubullet passed");
                listmov.remove(BDB.getMovementComponent());
                BDB = null;
                bonusactive = false;
            }
        }
    }

    //visualise components
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
        F.render();
    }


    public void PlayershipShoot(){
        // bonus bullet3xTime active?
        if(bullet3xTime > 0){ // PS shoots 3 bullets
            bullet3xTime--;
            System.out.println("aantal tripele kogels : "+ bullet3xTime);
            PlayerBullet playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -GameHeight/200); // dy default -50
            PB.add(playbul);
            listmov.add(playbul.getMovementComponent());
            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
            playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth, PS.getMovementComponent().getyCoord(), 0, -GameHeight/200); // dy default -50
            PB.add(playbul);
            listmov.add(playbul.getMovementComponent());
            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
            playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth/2, PS.getMovementComponent().getyCoord()-BulletHeight/4, 0, -GameHeight/200); // dy default -50
            PB.add(playbul);
            listmov.add(playbul.getMovementComponent());
            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
        }
        // bonus bullet2x active?
        else if (bullet2xTime> 0) { // PS shoot 2 bullets
            bullet2xTime--;
            System.out.println("aantal dubbele kogels : " + bullet2xTime);
            PlayerBullet playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -GameHeight/200); // dy default -50
            PB.add(playbul);
            listmov.add(playbul.getMovementComponent());
            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
            playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth, PS.getMovementComponent().getyCoord(), 0, -GameHeight/200); // dy default -50
            PB.add(playbul);
            listmov.add(playbul.getMovementComponent());
            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
        }
        // PS shoot 1 bullet
        else {
            PlayerBullet playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth / 2, PS.getMovementComponent().getyCoord(), 0, -GameHeight/200); // dy default -50
            PB.add(playbul);
            listmov.add(playbul.getMovementComponent());
            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
        }



    }


}
