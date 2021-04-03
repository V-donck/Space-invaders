package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;


import java.util.ArrayList;

public class Game {
    private int GameHeigth;
    private int GameWidth;

    private int PlayershipWidth;
    private int PlayershipHeight;
    private int BulletWidth;
    private int BulletHeight;

    private AbstractFactory F;
    private MovementUpdater movup;
    private MovementComponent[] list;// dit weg?
    private ArrayList<MovementComponent> listmov;
    private Playership PS;
    private EnemyShip ES1;
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
        BulletWidth = 50;
        BulletHeight = 100;

        F.setGameDimensions(GameWidth, GameHeigth, PlayershipWidth, PlayershipHeight, BulletWidth, BulletHeight);
        PS = F.createPlayership();
        PS.visualise();

        F.render();
        listmov = new ArrayList<>();
        listmov.add(PS.getMovementComponent());
        movup.update(listmov);
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("1sec gewacht");
        PS.visualise();
        F.render();
        ES1 = F.createEnemyship();
        ES = new ArrayList<EnemyShip>();
        ES.add(F.createEnemyship(50, 0, 0, 0, 0));
        ES.add(F.createEnemyship(50, 100, 0, 0, 0));
        PB = new ArrayList<PlayerBullet>();
       isRunning = true;

        listmov = new ArrayList<>();
        listmov.add(PS.getMovementComponent());
        listmov.add(ES1.getMovementComponent());
        list = new MovementComponent[]{PS.getMovementComponent(), ES1.getMovementComponent()};
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
                            if(PS.getMovementComponent().getxCoord()<= GameWidth-PS.getWidth() )//gamewidth-size ps )
                            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 20, 0);
                            break;
                        case DOWN:
                            System.out.println("down");
                            break;
                        case UP:
                            System.out.println("up");
                            PlayerBullet playbul = F.createPlayerBullet(10, PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, -1);
                            System.out.println(PS.getMovementComponent().getxCoord() + "    " + PS.getMovementComponent().getyCoord());
                            PB.add(playbul);
                            listmov.add(playbul.getMovementComponent());
                            System.out.println(playbul.getMovementComponent().getxCoord() + "   =" + playbul.getMovementComponent().getyCoord());
                            break;
                        default:
                            PS.setMovementComponent(PS.getMovementComponent().getxCoord(), PS.getMovementComponent().getyCoord(), 0, 0);


                    }
                }

            }
            list = new MovementComponent[]{PS.getMovementComponent(), ES1.getMovementComponent()};
            movup.update(listmov);
            //   System.out.println(list[0].getDx());
            // System.out.println(list[0].getDy());
            //  System.out.println(list[0].getxCoord());
            //  System.out.println(list[0].getyCoord());
            for (EnemyShip es : ES) {
                es.visualise();
            }
            if (!PB.isEmpty()) {
                for (PlayerBullet pb : PB) {
                    pb.visualise();
                }
            }
            PS.visualise();
            F.render();

            // MovementComponent[] list = new MovementComponent[]{PS.getMovementComponent(), ES1.getMovementComponent()};
            // movup.update(list);


            ArrayList<PlayerBullet> removelist = new ArrayList<>();


            //collisions:
            //PS enemy bullet
            //PS rand
            //PS ES?


            //PS bonus
            //enemy ship, playerbullet
            // PB rand
            removelist.clear();
            for (PlayerBullet pb : PB) {
                if (pb.getMovementComponent().getyCoord() > 1000 || pb.getMovementComponent().getyCoord() < -10) {
                    removelist.add(pb);
                }
                System.out.println(pb.getMovementComponent().getxCoord() + "  xy bullet  " + pb.getMovementComponent().getyCoord());

            }
            for (PlayerBullet pb : removelist) {
                PB.remove(pb);
            }
            removelist.clear();
            // EB rand
            // ES rand




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
