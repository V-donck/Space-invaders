package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EnumMap;
import java.io.*;
import java.util.*;
import java.util.Scanner;


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

    private AbstractFactory F;
    private MovementUpdater movup;
    private ArrayList<MovementComponent> listmov;
    private Playership PS;
    private ArrayList<EnemyShip> ES;
    private ArrayList<PlayerBullet> PB;
    private ArrayList<EnemyBullet> EB;
    private int score;
    private Input input;
    private Input.Inputs key;
    private boolean isRunning;
    private Friendly Fr;
    private EnemyShip FrEs;
    private boolean bonusactive = false;
    private Bulletx Bx;
    private BoxDamageBullet BDB;


    public Game(AbstractFactory f) {
        F = f;
        movup = new MovementUpdater();
        score = 0;
        input = new Input(F);
        isRunning = false;
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
                    System.out.println("speed");
                    break;
                }
            }
            /*
            if(words[0].equals("Game:")){
                GameHeight = Integer.parseInt(words[2]);
                GameWidth = Integer.parseInt(words[1]);
                System.out.println("game");
            }
            else if(words[0].equals("Playership:")){
                PlayershipHeight = Integer.parseInt(words[2]);
                PlayershipWidth = Integer.parseInt(words[1]);
                System.out.println("playership");
            }
            else if(words[0].equals("Bullet:")){
                BulletHeight = Integer.parseInt(words[2]);
                BulletWidth = Integer.parseInt(words[1]);
                System.out.println("bullet");
            }
            else if(words[0].equals("Enemyship:")){
                EnemyshipHeight = Integer.parseInt(words[2]);
                EnemyshipWidth = Integer.parseInt(words[1]);
                System.out.println("enemyship");
            }
            else if(words[0].equals("Box:")){
                boxHeight = Integer.parseInt(words[2]);
                boxWidth = Integer.parseInt(words[1]);
                System.out.println("box");
            }
            else if(words[0].equals("Speed:")){
                double speed = Integer.parseInt(words[1]);
                if(speed<10 & speed>0){
                    delay = (int) (30/speed);
                }
                System.out.println("box");
            }*/
        }
        } catch (IOException ex) {
            Thread.currentThread().interrupt();
        }



        // set dimensions
        F.setGameDimensions(GameWidth, GameHeight, PlayershipWidth, PlayershipHeight, BulletWidth, BulletHeight, EnemyshipWidth, EnemyshipHeight,boxWidth,boxHeight, score);
        // initialise objects
        PS = F.createPlayership(PlayershipHeight);
        PB = new ArrayList<>();
        EB = new ArrayList<>();
        isRunning = true;
        listmov = new ArrayList<>();
        listmov.add(PS.getMovementComponent());
        ES = createESlist();
        movup.update(listmov);
        loop();
    }



    void loop() {
        // initialise some variables
        int teller = 0;
        int i =0;
        int j = 0;
        int randomvalue = (int) (Math.random()*1500);
        int randomvalue2;
        int bullet2xtime = 0;
        int bullet3xtime = 0;
        int damagebullet = 10;
        int damageBulletTime = 0;
        boolean EsHitSide= false;
        ArrayList<EnemyShip> listremovees;
        System.out.println("randomvalue" + randomvalue);
        boolean canshoot= true;
        listremovees = new ArrayList<>();

        // loop
        while (isRunning) {
            EsHitSide = false;
            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, 0);

            // create new enemyships if all enemys are killed
            if(ES.isEmpty()){
                ES = createESlist();
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
            if(teller>=1500){
                teller=0;
                randomvalue = (int) (Math.random()*1500);
                System.out.println("randomvalue loop" + randomvalue);
            }
            else{
                teller++;
            }



            // key inputs
            if (input.inputAvailable()) {
                key = input.getInput();
                if (key == Input.Inputs.SPACE) {
                    isRunning = false;
                    System.exit(0);
                } else {
                    isRunning = true;
                    switch (key) {
                        case LEFT:
                            System.out.println("left");
                            if(PS.getMovementComponent().getxCoord()>0)
                                PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), -100, 0);
                            else
                                PS.setMovementComponent(0, PS.getMovementComponent().getyCoord(), 0, 0);
                            break;
                        case RIGHT:
                            System.out.println("right");
                            if(PS.getMovementComponent().getxCoord()+PlayershipWidth< GameWidth)//gamewidth-size ps )
                                PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 100, 0);
                            else
                                PS.setMovementComponent(GameWidth-PlayershipWidth, PS.getMovementComponent().getyCoord(), 0, 0);
                            break;
                        case DOWN:
                            System.out.println("down");
                            System.out.println("x: " +PS.getMovementComponent().getxCoord());
                            System.out.println("y: " +PS.getMovementComponent().getyCoord());
                            System.out.println("shipleft " +(PS.getMovementComponent().getxCoord()+PlayershipWidth) );
                            break;
                        // PS shoot
                        case UP:
                            System.out.println("up");
                            if(canshoot) {
                                canshoot = false;
                                // bonus bullet3xtime active?
                                if(bullet3xtime > 0){ // PS shoots 3 bullets
                                    bullet3xtime--;
                                    System.out.println("aantal tripele kogels : "+ bullet3xtime);
                                    PlayerBullet playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -50);
                                    PB.add(playbul);
                                    listmov.add(playbul.getMovementComponent());
                                    System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                                    playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth, PS.getMovementComponent().getyCoord(), 0, -50);
                                    PB.add(playbul);
                                    listmov.add(playbul.getMovementComponent());
                                    System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                                    playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth/2, PS.getMovementComponent().getyCoord()-BulletHeight/4, 0, -50);
                                    PB.add(playbul);
                                    listmov.add(playbul.getMovementComponent());
                                    System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                                }
                                // bonus bullet2x active?
                                else if (bullet2xtime> 0) { // PS shoot 2 bullets
                                    bullet2xtime--;
                                    System.out.println("aantal dubbele kogels : " + bullet2xtime);
                                    PlayerBullet playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -50);
                                    PB.add(playbul);
                                    listmov.add(playbul.getMovementComponent());
                                    System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                                    playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth, PS.getMovementComponent().getyCoord(), 0, -50);
                                    PB.add(playbul);
                                    listmov.add(playbul.getMovementComponent());
                                    System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                                }
                                // PS shoot 1 bullet
                                else {
                                    PlayerBullet playbul = F.createPlayerBullet(damagebullet, PS.getMovementComponent().getxCoord() + PlayershipWidth / 2, PS.getMovementComponent().getyCoord(), 0, -50);
                                    PB.add(playbul);
                                    listmov.add(playbul.getMovementComponent());
                                    System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                                }
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
                    Fr = F.createFriendly();
                    System.out.println("create friendly");
                    listmov.add(Fr.getMovementComponent());
                }
                else if(randomvalue2 == 2){ // bulletx2
                    bonusactive = true;
                    Bx = F.createBulletx(2);
                    System.out.println("create bx2");
                    listmov.add(Bx.getMovementComponent());
                }
                else if(randomvalue2 == 3){ // bulletx3
                    bonusactive = true;
                    Bx = F.createBulletx(3);
                    System.out.println("create bx3");
                    listmov.add(Bx.getMovementComponent());
                }
                else if(randomvalue2 == 4){ // damagebulletbox
                    bonusactive = true;
                    BDB = F.createBoxDamageBullet();
                    System.out.println("created boxdamagebullet");
                    listmov.add(BDB.getMovementComponent());
                }

            }

            // enemyship shoot
            if(teller%200==0){
                System.out.println("enemy: fire");
                EnemyShip es = ES.get((int) (Math.random()*ES.size()));
                EnemyBullet eb = F.createEnemyBullet(10,es.getMovementComponent().getxCoord()+EnemyshipWidth/2,es.getMovementComponent().getyCoord()+EnemyshipHeight,0,7);
                EB.add(eb);
                listmov.add(eb.getMovementComponent());
            }

            // FrEs shoot
            if(FrEs !=null & teller%200==0){
                System.out.println("Frenemy: fire");
                EnemyBullet eb = F.createEnemyBullet(20,FrEs.getMovementComponent().getxCoord()+EnemyshipWidth/2,FrEs.getMovementComponent().getyCoord()+EnemyshipHeight,0,7);
                EB.add(eb);
                listmov.add(eb.getMovementComponent());
            }

            // PS canshoot
            if(teller%50==0){
                canshoot = true;
            }





        // collisions
            // collisions ES
            for(i=0; i<ES.size();i++){
                EnemyShip es = ES.get(i);
                // ES <-> PS
                if((((PS.getMovementComponent().getxCoord() + PlayershipWidth)>es.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord()<(es.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                        (((PS.getMovementComponent().getyCoord()+PlayershipHeight)>es.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord()<(es.getMovementComponent().getyCoord()+EnemyshipHeight)))){
                    PS.setHP(0);
                    System.out.println("game over");
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
                            F.updatescore(score);
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
                if ((eb.getMovementComponent().getxCoord()+PlayershipWidth>PS.getMovementComponent().getxCoord()&& eb.getMovementComponent().getxCoord()<PS.getMovementComponent().getxCoord()+PlayershipWidth) &&
                        (eb.getMovementComponent().getyCoord()+PlayershipHeight>PS.getMovementComponent().getyCoord() && eb.getMovementComponent().getyCoord()<PS.getMovementComponent().getyCoord()+PlayershipHeight)){
                    System.out.println("hit playership");
                    PS.setHP(PS.getHP()-eb.getDamage());
                    EB.remove(eb);
                    listmov.remove(eb.getMovementComponent());
                    i--;
                    if(PS.getHP()<=0){
                        System.out.println("destroyed ps: game over");
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
                        F.updatescore(score);
                        System.out.println("score : "+ score);

                        listmov.remove(pb.getMovementComponent());
                        PB.remove(i);
                        i--;
                        pb = null;
                        // remove Friendly
                        listmov.remove(Fr.getMovementComponent());

                        // create Enemy
                        FrEs = F.createEnemyship(100,Fr.getMovementComponent().getxCoord(),Fr.getMovementComponent().getyCoord(),0,0);
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
                        bullet2xtime = 50; // 50 bullets
                        System.out.println("box bx2 taken");
                    }
                    else if(Bx.getNumber()==3){
                        bullet3xtime = 35; // 35 bullets
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



            movup.update(listmov); // update movements
            // update score en hp to screen
            F.updatescore(score);
            F.updatehp(PS.getHP());

        //visualise components
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

        // delay
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }


    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<EnemyShip> createESlist() {
        ArrayList<EnemyShip> ES = new ArrayList<>();
        for (int j = 0; j<3;j++) {
            for (int i = 0; i < 6; i++) {
                EnemyShip es = F.createEnemyship(50, (int) (i * 1.3 * PlayershipWidth), EnemyshipHeight+EnemyshipHeight*j, 3, 0);
                ES.add(es);
                listmov.add(es.getMovementComponent());
            }
        }
        return ES;
    }

    public void Enemyshipsetdirection(int direction){
        for(int i = 0; i<ES.size();i++){
            EnemyShip es = ES.get(i);
            es.setMovementComponent(es.getMovementComponent().getxCoord()+direction*2,es.getMovementComponent().getyCoord()+EnemyshipHeight,direction,0);
        }
    }


}
