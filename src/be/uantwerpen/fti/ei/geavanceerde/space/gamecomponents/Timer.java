package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

public class Timer {
    private long starttime;
    private long endtime;

    public Timer() {
        System.out.println("timer set");
    }

    public void start(){
        starttime = System.currentTimeMillis();
    }
    public void end(){
        endtime = System.currentTimeMillis();
    }
    public void delay(int delay){
        try {
            Thread.sleep(delay-(endtime-starttime));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
