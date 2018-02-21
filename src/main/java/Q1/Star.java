package Q1;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class Star {
    public static BufferedImage img;
    public static int width = 1920;
    public static int height = 1080;
    public static DoublyLinkedList dll = new DoublyLinkedList();

    public static void main(String[] args) {
        try {
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        reset();
        Graphics2D graphics = img.createGraphics();
        graphics.setPaint ( Color.white );
        graphics.fillRect ( 0, 0, img.getWidth(), img.getHeight() );


        // number of vertices
        int n = 6;
        // number of threads
        int m = Integer.parseInt(args[0]);
        // check for m < n
        if (m > n){
            throw new IllegalArgumentException("Invalid m");
        }
        // number of repetitions
        int c = Integer.parseInt(args[1]);
        // check for c > 0
        if (c < 0){
            throw new IllegalArgumentException("Invalid c");
        }

        // to store coordinates
        int[] x_vals1 = new int[6];
        int[] y_vals1 = new int[6];

        dll.addFront(-1.0, 5.0);
        dll.addBack(1.0, 2.0);
        dll.addBack(5.0, 0.0);
        dll.addBack(1.0, -2.0);
        dll.addBack(-4.0, -4.0);
        dll.addBack(-3.0, 1.0);

        Thread[] threads = new Thread[m];
        for(int i = 0 ; i < m; i++){
            threads[i] = modThread(c);
            threads[i].start();
        }
        for(int i = 0 ; i < m; i++){
            threads[i].join();
        }
        dll.iterateForward(x_vals1, y_vals1);
        Polygon p = new Polygon(x_vals1, y_vals1,6);
        graphics.setColor(Color.blue);
        graphics.drawPolygon(p);
        graphics.dispose();
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
    public void update(int c, DoublyLinkedList d){
        int i = 0;
        while(i < c) {
            int vertice_index = ThreadLocalRandom.current().nextInt(1, 7);
            d.updateNode(vertice_index);
            i++;
        }

    }
    public static Thread modThread(final int c) {
        return new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while(i < c){
                    int vertice_index = ThreadLocalRandom.current().nextInt(1, 7);
                    dll.updateNode(vertice_index);
                    i++;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}