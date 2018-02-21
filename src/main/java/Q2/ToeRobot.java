package Q2;
import java.util.Random;

public class ToeRobot extends Thread {
    private Leg leg;
    private Random rand = new Random();
    @Override
    public void run() {
        while(!Catmaker.done.get()) {
            int toes = getToe();
            leg = new Leg(toes);

            try {
                // takes 10 - 20ms
                Thread.sleep(10 + rand.nextInt(11));
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            builtLeg();
        }
    }

    private void builtLeg() {
        if(leg.getToes() == 4) {
            synchronized(Catmaker.hleg_bin) {
                Catmaker.hleg_bin.add(leg);
                Catmaker.hleg_bin.notify();
            }
        }else {
            synchronized(Catmaker.fleg_bin) {
                Catmaker.fleg_bin.add(leg);
                Catmaker.fleg_bin.notify();
            }
        }
    }

    private int getToe() {
        synchronized(Catmaker.toe_bin) {
            return ((rand.nextInt(11)%2 == 0) ? 4 : 5);
        }
    }
}
