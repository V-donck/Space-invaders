package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EnumMap;

public class Game {
    private int GameHeigth;
    private int GameWidth;

    private int PlayershipWidth;
    private int PlayershipHeight;
    private int BulletWidth;
    private int BulletHeight;
    private int EnemyshipWidth;
    private int EnemyshipHeight;
    private int boxHeight;
    private int boxWidth;

    private AbstractFactory F;
    private MovementUpdater movup;
    private MovementComponent[] list;// dit weg?
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
    private Bulletx2 Bx2;


    public Game(AbstractFactory f) {
        F = f;
        movup = new MovementUpdater();
        score = 0;
        input = new Input(F);
        isRunning = false;
    }



    public void start(){
        GameWidth = 10000;
        GameHeigth = 10000;

        PlayershipWidth = 1000;
        PlayershipHeight = 1500;
        BulletWidth = 150;
        BulletHeight = 700;
        EnemyshipWidth = 1000;
        EnemyshipHeight = 1000;
        boxWidth = 750;
        boxHeight = 750;

        F.setGameDimensions(GameWidth, GameHeigth, PlayershipWidth, PlayershipHeight, BulletWidth, BulletHeight, EnemyshipWidth, EnemyshipHeight,boxWidth,boxHeight, score);
        PS = F.createPlayership(PlayershipHeight);
        PS.visualise();
        F.render();



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
        int teller = 0;
        int i =0;
        int j = 0;
        int randomvalue = (int) (Math.random()*300);
        int randomvalue2;
        int bullet2xtime = 0;
        System.out.println("randomvalue" + randomvalue);
        while (isRunning) {

            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, 0);
            if(ES.isEmpty()){
                ES = createESlist();
            }

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
                        case UP:
                            System.out.println("up");
                            if(bullet2xtime<=0) {
                                PlayerBullet playbul = F.createPlayerBullet(10, PS.getMovementComponent().getxCoord() + PlayershipWidth / 2, PS.getMovementComponent().getyCoord(), 0, -50);
                                PB.add(playbul);
                                listmov.add(playbul.getMovementComponent());
                                System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                            }
                            else {
                                bullet2xtime--;
                                System.out.println("aantal dubbele kogels : "+ bullet2xtime);
                                PlayerBullet playbul = F.createPlayerBullet(10, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -50);
                                PB.add(playbul);
                                listmov.add(playbul.getMovementComponent());
                                System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                                playbul = F.createPlayerBullet(10, PS.getMovementComponent().getxCoord() + PlayershipWidth, PS.getMovementComponent().getyCoord(), 0, -50);
                                PB.add(playbul);
                                listmov.add(playbul.getMovementComponent());
                                System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                            }
                            break;


                    }
                }

            }
        // enemyship shoot

            if(teller>=(500)){ // moet op 1500 staan -> 30 sec zie ook boven, voor loop
                teller=0;
                randomvalue = (int) (Math.random()*500);
                System.out.println("randomvalue loop" + randomvalue);
            }
            else{
                teller++;

                // enemyship shoot
                if(teller%200==0){
                    System.out.println("enemy: fire");
                    EnemyShip es = ES.get((int) (Math.random()*ES.size()));
                    EnemyBullet eb = F.createEnemyBullet(10,es.getMovementComponent().getxCoord()+EnemyshipWidth/2,es.getMovementComponent().getyCoord()+EnemyshipHeight,0,7);
                    EB.add(eb);
                    listmov.add(eb.getMovementComponent());
                }
                if(teller == randomvalue & !bonusactive){ // bonus
                    System.out.println("teller = randomvalue");
                    randomvalue2 = (int) (Math.random()*3);
                    System.out.println("randomvalue2: " + randomvalue2);
                    if(randomvalue2 ==1) { // friendly
                        bonusactive = true;
                        Fr = F.createFriendly();
                        System.out.println("create friendly");
                        listmov.add(Fr.getMovementComponent());
                    }
                    else if(randomvalue2 == 2){ // bulletx2
                        bonusactive = true;
                        Bx2 = F.createBulletx2();
                        System.out.println("create bx2");
                        listmov.add(Bx2.getMovementComponent());
                    }
                }
                if(FrEs !=null & teller%200==0){
                    System.out.println("Frenemy: fire");
                    EnemyBullet eb = F.createEnemyBullet(20,FrEs.getMovementComponent().getxCoord()+EnemyshipWidth/2,FrEs.getMovementComponent().getyCoord()+EnemyshipHeight,0,7);
                    EB.add(eb);
                    listmov.add(eb.getMovementComponent());
                }

            }





            //collisions:
            //PS enemybullet
            //PS rand
            //PS ES?
/*
oude collisions :
            //PS bonus
            //enemy ship, playerbullet
            // PB rand
            // ES rand

            for (i = 0; i<PB.size();i++) {
                PlayerBullet pb = PB.get(i);
                if (pb.getMovementComponent().getyCoord() > GameHeigth || pb.getMovementComponent().getyCoord() < 0) {
                    PB.remove(pb);
                    listmov.remove(pb.getMovementComponent());
                    System.out.println(i);
                    i--;
                }
                for(int j = 0;j<ES.size();j++){
                    EnemyShip es = ES.get(j);
                    if((((pb.getMovementComponent().getxCoord() + BulletWidth)>es.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(es.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                            (((pb.getMovementComponent().getyCoord()+BulletHeight)>es.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(es.getMovementComponent().getyCoord()+EnemyshipHeight)))){   // y-coordinaat vallen samen
                        System.out.println("hit");
                        es.setHP(es.getHP()-pb.getDammage());
                        PB.remove(i);
                        listmov.remove(pb.getMovementComponent());
                        i--;
                        System.out.println(es.getHP());
                        if(es.getHP()<=0) {
                            System.out.println("destroyed");
                            score++;
                            F.updatescore(score);
                            ES.remove(es);
                            listmov.remove(es.getMovementComponent());
                            j--;
                        }


                    }
                }

            }


            // EB rand
            for(i = 0;i<EB.size();i++){
                EnemyBullet eb = EB.get(i);
                if (eb.getMovementComponent().getyCoord() > GameHeigth || eb.getMovementComponent().getyCoord() < -5) {
                    EB.remove(eb);
                    listmov.remove(eb.getMovementComponent());
                    System.out.println(i);
                    i--;
                }
                if ((eb.getMovementComponent().getxCoord()+EnemyshipWidth>PS.getMovementComponent().getxCoord()&& eb.getMovementComponent().getxCoord()<PS.getMovementComponent().getxCoord()+PlayershipWidth) &&
                (eb.getMovementComponent().getyCoord()+EnemyshipHeight>PS.getMovementComponent().getyCoord() && eb.getMovementComponent().getyCoord()<PS.getMovementComponent().getyCoord()+PlayershipHeight)){
                    System.out.println("hit playership");
                    PS.setHP(PS.getHP()-eb.getDammage());
                    EB.remove(eb);
                    listmov.remove(eb.getMovementComponent());
                    i--;
                    if(PS.getHP()<=0){
                        System.out.println("destroyed ps: game over");
                    }

                }

            }
            */

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
                        es.setHP(es.getHP()-pb.getDammage());
                        PB.remove(j);
                        listmov.remove(pb.getMovementComponent());
                        j--;
                        System.out.println(es.getHP());
                        if(es.getHP()<=0) {
                            System.out.println("destroyed");
                            score++;
                            F.updatescore(score);
                            ES.remove(es);
                            listmov.remove(es.getMovementComponent());
                            i--;
                        }


                    }
                }
                // ES <-> rand
                if(es.getMovementComponent().getxCoord()<=0){ // left side
                    Enemyshipsetdirection(1);
                }
                if(es.getMovementComponent().getxCoord()+EnemyshipWidth>=GameWidth){ // right side
                    Enemyshipsetdirection(-1);
                }
                if(es.getMovementComponent().getyCoord()+EnemyshipHeight>=GameHeigth){  //bottom
                    System.out.println("Enemy ship passed");
                    System.out.println("game over");
                }


            }

            // collisions EB
            for(i = 0;i<EB.size();i++){
                EnemyBullet eb = EB.get(i);


                // EB <-> PS
                if ((eb.getMovementComponent().getxCoord()+PlayershipWidth>PS.getMovementComponent().getxCoord()&& eb.getMovementComponent().getxCoord()<PS.getMovementComponent().getxCoord()+PlayershipWidth) &&
                        (eb.getMovementComponent().getyCoord()+PlayershipHeight>PS.getMovementComponent().getyCoord() && eb.getMovementComponent().getyCoord()<PS.getMovementComponent().getyCoord()+PlayershipHeight)){
                    System.out.println("hit playership");
                    PS.setHP(PS.getHP()-eb.getDammage());
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
                if (eb.getMovementComponent().getyCoord() > GameHeigth || eb.getMovementComponent().getyCoord() < -5) {
                    EB.remove(eb);
                    listmov.remove(eb.getMovementComponent());
                    System.out.println(i);
                    i--;
                }

            }

            // collision Bonus

            // collision PB
            // PB <-> rand
            for(i=0;i<PB.size();i++){
                PlayerBullet pb = PB.get(i);
                if (pb.getMovementComponent().getyCoord() > GameHeigth || pb.getMovementComponent().getyCoord() < -1*BulletHeight) {
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
                        FrEs.setHP(FrEs.getHP()-pb.getDammage());
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

            // collision Fr

            if(Fr != null) {
                // Fr <-> PS
                if((((PS.getMovementComponent().getxCoord() + PlayershipWidth)>Fr.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord()<(Fr.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                        (((PS.getMovementComponent().getyCoord()+PlayershipHeight)>Fr.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord()<(Fr.getMovementComponent().getyCoord()+EnemyshipHeight)))){
                    PS.setHP(0);
                    System.out.println("game over");
                }
                // Fr <-> rand
                if (Fr.getMovementComponent().getyCoord() + EnemyshipHeight >= GameHeigth) {  //bottom
                    System.out.println("Friendly Enemy ship passed");
                    listmov.remove(Fr.getMovementComponent());
                    Fr = null;
                    bonusactive = false;
                }

            }

            // collision Bx2
            if(Bx2 != null) {
                // Bx2 <-> PS
                if ((((PS.getMovementComponent().getxCoord() + PlayershipWidth) > Bx2.getMovementComponent().getxCoord()) && (PS.getMovementComponent().getxCoord() < (Bx2.getMovementComponent().getxCoord() + boxWidth))) &&   // x-coordinaten vallen samen
                        (((PS.getMovementComponent().getyCoord() + PlayershipHeight) > Bx2.getMovementComponent().getyCoord()) && (PS.getMovementComponent().getyCoord() < (Bx2.getMovementComponent().getyCoord() + boxHeight)))) {
                    bullet2xtime = 50; // 50 bullets
                    System.out.println("box bx2 taken");
                    listmov.remove(Bx2.getMovementComponent());
                    Bx2 = null;
                    bonusactive = false;

                }
            }
                // Bx2 <-> rand
            if(Bx2 != null){
                if (Bx2.getMovementComponent().getyCoord() + boxHeight >= GameHeigth) {  //bottom
                    System.out.println("box bx2 passed");
                    listmov.remove(Bx2.getMovementComponent());
                    Bx2 = null;
                    bonusactive = false;
                }
            }


            movup.update(listmov); // update movements

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
            if(Bx2 != null){
                Bx2.visualise();
            }
            F.render();

        // delay
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        /*

            if ((ES1.getMovementComponent().getxCoord() - PB1.getWidth() < PB1.getMovementComponent().getxCoord() && ES1.getMovementComponent().getxCoord() > PB1.getMovementComponent().getxCoord()) && ES1.getMovementComponent().getyCoord() - PB1.getHeight() < PB1.getMovementComponent().getyCoord() && ES1.getMovementComponent().getyCoord() > PB1.getMovementComponent().getyCoord()) {
                //playerbullet hits enemyship
                ES1.setHP(ES1.getHP() - PB1.getDammage());
                //PB1.destroy();
                if (ES1.getHP() < 0) {
                    //    ES1.destroy();
                    score++;
                }
            }
            int i = 0;

         */
/*       for(EnemyShip ESf : ES){

            if((ESf.getMovementComponent().getxCoord()-PB1.getWidth()<PB1.getMovementComponent().getxCoord() && ESf.getMovementComponent().getxCoord()>PB1.getMovementComponent().getxCoord()) && ESf.getMovementComponent().getyCoord()-PB1.getHeight()<PB1.getMovementComponent().getyCoord() && ESf.getMovementComponent().getyCoord()>PB1.getMovementComponent().getyCoord() ){
                //playerbullet hits enemyship
                ESf.setHP(ESf.getHP() - PB1.getDammage());
                //PB1.destroy();
                if (ESf.getHP()<0){
                    //    ES1.destroy();
                    ES.remove(i);
                    score++;
                }
            }
            i++;
        }

*/


    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<EnemyShip> createESlist() {
        ArrayList<EnemyShip> ES = new ArrayList<>();
        for(int i = 0; i<6; i++){
            EnemyShip es = F.createEnemyship(50,(int) (i*1.3*PlayershipWidth),0,3,0);
            ES.add(es);
            listmov.add(es.getMovementComponent());
        }
        return ES;
    }

    public void Enemyshipsetdirection(int direction){
        for(int i = 0; i<ES.size();i++){
            EnemyShip es = ES.get(i);
            es.setMovementComponent(es.getMovementComponent().getxCoord(),es.getMovementComponent().getyCoord()+500,direction,0);
        }
    }


}
