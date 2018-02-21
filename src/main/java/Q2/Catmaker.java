package Q2;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ArrayList;

public class Catmaker {
    public static AtomicBoolean done = new AtomicBoolean(false);
    // to restrict amount of threads/robots accessing a bin
    public static Object toe_bin = new Object();
    public static Object leg_bin = new Object();
    public static Object tail_bin = new Object();
    public static Object body_bin = new Object();

    // to keep track of parts built
    public static ArrayList<Leg> hleg_bin = new ArrayList<Leg>();
    public static ArrayList<Leg> fleg_bin = new ArrayList<Leg>();
    public static ArrayList<Body> notail_bin = new ArrayList<Body>();
    public static ArrayList<Body> noleg_bin = new ArrayList<Body>();
    public static ArrayList<Body> lowerb_bin = new ArrayList<Body>();
    public void build() {




        // create threads
        // start and time threads
    }
}
