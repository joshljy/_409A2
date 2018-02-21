package Q2;
import java.util.Random;

public class TailRobot extends Thread {
    private Body body;
    private Random rand = new Random();
    private boolean legs;

    private boolean gotLegs(){
        return this.legs;
    }
    private void setLegs(boolean b){
        this.legs = b;
    }

    @Override
    public void run() {
        while(!Catmaker.done.get()){
            body = bodyType();
            try {
                // takes 10 - 20ms
                Thread.sleep(10 + rand.nextInt(11));
                body.setTail(true);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            // now check if we added a tail to a body with legs
            if(gotLegs()){
                synchronized (Catmaker.lowerb_bin){
                    Catmaker.lowerb_bin.add(body);
                    Catmaker.lowerb_bin.notify();
                }
            }
            else{
                // no legs
                synchronized (Catmaker.noleg_bin){
                    Catmaker.noleg_bin.add(body);
                    Catmaker.noleg_bin.notify();
                }
            }
        }
    }
    private Body bodyType() {
        synchronized(Catmaker.notail_bin) {
            // check to see if there are any tailless bodies with legs
            if(Catmaker.notail_bin.size() > 0) {
                setLegs(true);
                return Catmaker.notail_bin.remove(Catmaker.notail_bin.size()-1);
            }
        }
        //Otherwise just get new body
        synchronized(Catmaker.body_bin) {
            setLegs(false);
            return new Body();
        }
    }
}
