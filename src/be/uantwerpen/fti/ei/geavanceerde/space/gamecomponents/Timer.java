package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * Timer to create delay
 */
public class Timer {
    private long starttime;
    private long endtime;

    /**
     * create Timer
     */
    public Timer() {
    }

    /**
     * start Timer: get currenttime
     */
    public void start(){
        starttime = System.currentTimeMillis();
    }

    /**
     * end Timer: get currenttime
     */
    public void end(){
        endtime = System.currentTimeMillis();
    }

    /**
     * creates delay
     * @param delay delay that is set in Game
     */
    public void delay(int delay){
        long delaySleep = delay-(endtime-starttime);
        if(delaySleep<0){
            delaySleep = 0;
        }
        try {
            Thread.sleep(delaySleep);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
