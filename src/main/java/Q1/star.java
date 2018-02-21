package Q1;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class star {

    public static BufferedImage img;
    public static int width = 1920;
    public static int height = 1080;

    public static void main(String[] args) {
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = img.createGraphics();


        // number of vertices
        int n = 6;
        // number of threads
        // Integer.parseInt(args[0]);
        int m;
        // number of repetitions
        // Integer.parseInt(args[1]);
        int c;
        // temporarily set C as 2
        c = 1;

        // to store coordinates
        double[] x_vals = new double[6];
        double[] y_vals = new double[6];
        int[] x_vals1 = new int[6];
        int[] y_vals1 = new int[6];

        //System.out.println(n);
        doublyLinkedList dll = new doublyLinkedList();
        dll.addFront(-1.0, 5.0);
        dll.addBack(1.0, 2.0);
        dll.addBack(5.0, 0.0);
        dll.addBack(1.0, -2.0);
        dll.addBack(-4.0, 4.0);
        dll.addBack(-3.0, 1.0);
        //dll.iterateForward();

        // going through the repetitions
        for(int i = 0 ; i < c ; i++) {
            int vertice_index = ThreadLocalRandom.current().nextInt(1, 7);
            dll.updateNode(vertice_index);

            System.out.println(vertice_index);
        }
        dll.iterateForward(x_vals, y_vals);


        Polygon p = new Polygon(x_vals1, y_vals1,6);
        graphics.setColor(Color.blue);
        graphics.drawPolygon(p);
        graphics.dispose();


        try {
            File outputfile = new File("outputimage.png");
            ImageIO.write(img, "png", outputfile);
        }
        catch (Exception e) {
            System.out.println("Error: " +e);
            e.printStackTrace();
        }



    }
    public static void reset() {
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                img.setRGB(i,j,0);
            }
        }
    }
}