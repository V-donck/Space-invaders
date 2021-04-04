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

        F.setGameDimensions(GameWidth, GameHeigth, PlayershipWidth, PlayershipHeight, BulletWidth, BulletHeight, EnemyshipWidth, EnemyshipHeight, score);
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
                            System.out.println(PS.getMovementComponent().getxCoord());
                            System.out.println("y: " +PS.getMovementComponent().getyCoord());
                            break;
                        case UP:
                            System.out.println("up");
                            PlayerBullet playbul = F.createPlayerBullet(10, PS.getMovementComponent().getxCoord()+PlayershipWidth/2, PS.getMovementComponent().getyCoord(), 0, -50);
                            System.out.println(PS.getMovementComponent().getxCoord() + "    " + PS.getMovementComponent().getyCoord());
                            PB.add(playbul);
                            listmov.add(playbul.getMovementComponent());
                            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                            break;


                    }
                }

            }
            movup.update(listmov);



            ArrayList<PlayerBullet> removepblist = new ArrayList<>();
            ArrayList<EnemyShip> removeeslist = new ArrayList<>();


            //collisions:
            //PS enemybullet
            //PS rand
            //PS ES?


            //PS bonus
            //enemy ship, playerbullet
            // PB rand

            for (int i = 0; i<PB.size();i++) {
                System.out.println(i);
                PlayerBullet pb = PB.get(i);
                if (pb.getMovementComponent().getyCoord() > GameHeigth || pb.getMovementComponent().getyCoord() < -10) {
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
            if(teller<(120)){
                teller++;
            }
            else{
                teller = 0;
                System.out.println("enemy: fire");
                EnemyShip es = ES.get((int) (Math.random()*ES.size()));
                EnemyBullet eb = F.createEnemyBullet(10,es.getMovementComponent().getxCoord()+EnemyshipWidth/2,es.getMovementComponent().getyCoord()+EnemyshipHeight,0,7);
                EB.add(eb);
                listmov.add(eb.getMovementComponent());
            }

            // EB rand
            for(int i = 0;i<EB.size();i++){
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
            // ES rand

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
            PS.visualise();
            F.render();


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
            EnemyShip es = F.createEnemyship(50,(int) (i*1.3*PlayershipWidth),0,1,0);
            ES.add(es);
            listmov.add(es.getMovementComponent());
        }
        return ES;
    }


}
