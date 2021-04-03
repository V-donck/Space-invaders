package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;


import java.util.ArrayList;

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
        GameWidth = 1000;
        GameHeigth = 1000;

        PlayershipWidth = 100;
        PlayershipHeight = 150;
        BulletWidth = 15;
        BulletHeight = 70;
        EnemyshipWidth = 100;
        EnemyshipHeight = 100;

        F.setGameDimensions(GameWidth, GameHeigth, PlayershipWidth, PlayershipHeight, BulletWidth, BulletHeight, EnemyshipWidth, EnemyshipHeight);
        PS = F.createPlayership();
        PS.visualise();
        F.render();

        ES = new ArrayList<>();
        ES.add(F.createEnemyship(50, 0, 0, 0, 0));
        ES.add(F.createEnemyship(50, 200, 0, 0, 0));
        PB = new ArrayList<>();
        isRunning = true;

        listmov = new ArrayList<>();
        listmov.add(PS.getMovementComponent());
        movup.update(listmov);
        for(EnemyShip es : ES){
            listmov.add(es.getMovementComponent());
        }

        loop();

    }

    void loop() {
        while (isRunning) {
            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, 0);
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
                            if(PS.getMovementComponent().getxCoord()>=PS.getWidth())
                            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), -20, 0);
                            break;
                        case RIGHT:
                            System.out.println("right");
                            if(PS.getMovementComponent().getxCoord()<= GameWidth-PlayershipWidth )//gamewidth-size ps )
                                PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 20, 0);
                            break;
                        case DOWN:
                            System.out.println("down");
                            System.out.println(PS.getMovementComponent().getxCoord());
                            break;
                        case UP:
                            System.out.println("up");
                            PlayerBullet playbul = F.createPlayerBullet(10, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -5);
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

            for (PlayerBullet pb : PB) {
                if (pb.getMovementComponent().getyCoord() > GameHeigth || pb.getMovementComponent().getyCoord() < -10) {
                    removepblist.add(pb);
                }
                for(EnemyShip es: ES){
                    if((((pb.getMovementComponent().getxCoord() + BulletWidth)>es.getMovementComponent().getxCoord()) && (pb.getMovementComponent().getxCoord()<(es.getMovementComponent().getxCoord() + EnemyshipWidth) ))  &&   // x-coordinaten vallen samen
                            (((pb.getMovementComponent().getyCoord()+BulletHeight)>es.getMovementComponent().getyCoord()) && (pb.getMovementComponent().getyCoord()<(es.getMovementComponent().getyCoord()+EnemyshipHeight)))){   // y-coordinaat vallen samen
                        System.out.println("hit");
                        removeeslist.add(es);


                    }
                }

            }
            for (PlayerBullet pb : removepblist) {
                PB.remove(pb);
            }
            for(EnemyShip es: removeeslist){
                ES.remove(es);
            }
            // EB rand
            // ES rand

            // visualise Enemy ships
            for (EnemyShip es : ES) {
                es.visualise();
            }
            // visualise Player Bullet
            if (!PB.isEmpty()) {
                for (PlayerBullet pb : PB) {
                    pb.visualise();
                }
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
}
