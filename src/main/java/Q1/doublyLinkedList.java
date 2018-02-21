package Q1;

import java.util.concurrent.ThreadLocalRandom;

public class doublyLinkedList {
    private Node head;
    private Node tail;


    private class Coordinate{
        double x;
        double y;

        public Coordinate(double x, double y){
            this.x = x;
            this.y = y;
        }
    }
    private class Node {
        Coordinate c;
        Node next;
        Node prev;

        public Node(Coordinate c, Node next, Node prev) {
            this.c = c;
            this.next = next;
            this.prev = prev;
        }
        public void setC(double x, double y){
            Coordinate newC = new Coordinate(x, y);
            this.c = newC;
        }
        public void setNext(Node newN){
            this.next = newN;
        }
        public void setPrev(Node newN){
            this.prev = newN;
        }
        public double getX(){
            return c.x;
        }
        public double getY(){
            return c.y;
        }
    }

    public Node getHead(){
        return head;
    }

    public Node getTail(){
        return tail;
    }

    public void addFront(double x, double  y) {
        Coordinate newC = new Coordinate(x, y);
        Node newN = new Node(newC, head, null);
        if(head != null ) {
            head.prev = newN;
        }
        head = newN;
        head.prev = tail;
        if(tail == null) {
            tail = newN;
        }
        //System.out.println("adding: x = " + newC.x + " ,y = "  + newC.y);
    }


    public void addBack(double x, double y) {
        Coordinate newC = new Coordinate(x, y);
        Node newN = new Node(newC, head, tail);
        tail.next = newN;
        head.prev = newN;
        tail = newN;
        //System.out.println("adding: x = " + newC.x + " ,y = "  + newC.y);
    }

    public double triangleCoord(double t1, double t2, double t3, double random1, double random2){
        double term1 = (1 - Math.sqrt(random1)) * t1;
        double term2 = (Math.sqrt(random1) * (1 - random2)) * t2;
        double term3 = (Math.sqrt(random1) * (random2)) * t3;
        return term1 + term2 + term3;
    }

    public synchronized void updateNode(int index){
        //System.out.println(index);
        Node temp = head;
        for(int i = 0; i < index -1; i++){
            temp = temp.next;
        }
        Node n = temp;
        synchronized(n) {
            synchronized (n.prev){
                Node a = n.prev;
                synchronized (n.next){
                    Node c = n.next;
                    double ax = a.getX();
                    double ay = a.getY();
                    double cx = c.getX();
                    double cy = c.getY();
                    double bx = n.getX();
                    double by = n.getY();

                    //random double 1
                    double r1 = ThreadLocalRandom.current().nextDouble(0, 1);
                    double r2 = ThreadLocalRandom.current().nextDouble(0, 1);
                    double newx = triangleCoord(ax, bx, cx, r1, r2);
                    double newy = triangleCoord(ay, by, cy, r1, r2);
                    //System.out.println("Original x :" + n.getX() + " and original y: " + n.getY());
                    //System.out.println("New x :" + newx + " and New y: " + newy);
                    temp.setC(newx,newy);
                }


            }
            //System.out.println("Original x :"+n.getX()+" and original y: "+ n.getY());
            //n.setC(x, y);

        }
//        Coordinate newC = new Coordinate(x, y);
//        Node newN = new Node(newC, n.next, n.prev);
//
//        if(n == head){
//            head = newN;
//        }
//        else if (n == tail){
//            tail = newN;
//        }

    }

    public void iterateForward(int[] x_Arr, int[] y_Arr){

        System.out.println("iterating forward..");
        Node tmp = head;
        int i  = 0;
        while(i < 6){
            x_Arr[i] = (int) (Math.round(tmp.c.x * 300 + 960));
            y_Arr[i] = (int) (Math.round(tmp.c.y * 300 + 540));
            System.out.println("Iteration "+ i +"   " + "x: "+ tmp.c.x + " y: " + tmp.c.y);
            System.out.println("new"+ i +"   " + "x: "+ x_Arr[i] + " y: " + y_Arr[i]);
            tmp = tmp.next;
            i++;
        }
    }

}
