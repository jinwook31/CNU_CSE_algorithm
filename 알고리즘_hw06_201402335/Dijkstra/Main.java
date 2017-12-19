package com.company;


public class Main {

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        //weight
        int[][] w = {{0,10,3,max,max},
                    {max,0,1,2,max},
                    {max,4,0,8,2},
                    {max,max,max,0,7},
                    {max,max,max,9,0}};

        //distance
        //int[] d = {0,max,max,max,max};
        Node[] d = new Node[5];
        d[0] = new Node(0,"A",0);
        d[1] = new Node(1,"B",max);
        d[2] = new Node(2,"C",max);
        d[3] = new Node(3,"D",max);
        d[4] = new Node(4,"E",max);

        minHeap Q = new minHeap(5);
        for(int i = 0; i < 5; i++) {
            Q.insert(d[i]);
        }

        int l = 0;
        System.out.println("dijkstra's algorithm.\n");
        while(Q.getSize() != 0){
            Node u = Q.extractMin();
            System.out.println("======================================================");
            System.out.println("S[" + l++ + "] : d[" + u.getData() + "] = " + u.getDist());
            System.out.println("------------------------------------------------------");
            int i = 0;
            for (Node v : Q.getNodes()) {
                System.out.print("S[" + i++ + "] : d[" + v.getData() + "] = " + d[v.getIndex()].getDist());
                if(d[v.getIndex()].getDist() > d[u.getIndex()].getDist() + w[u.getIndex()][v.getIndex()] && w[u.getIndex()][v.getIndex()] != max) {
                    d[v.getIndex()].setDist(d[u.getIndex()].getDist() + w[u.getIndex()][v.getIndex()]);
                    System.out.print("  ->  d[" + v.getData() + "] = " + d[v.getIndex()].getDist());
                }
                System.out.println("\n");
            }
        }
        System.out.println("======================================================");
    }
}

class minHeap{
    private Node[] Nodes;
    private int size;

    public Node[] getNodes() {
        Node[] res = new Node[size];
        System.arraycopy(Nodes,1,res,0,size);
        return res;
    }

    public int getSize(){return size;}


    public minHeap(int i){
        size = 0;
        Nodes = new Node[i];
        Nodes[0] = null; //root
    }

    public void buildMinHeap(Node[] A){
        for(int i = size/2; i >= 1; i--)
            minHeapify(A, i);
    }


    private void minHeapify(Node[] A, int index){
        int l = 2*index, r = 2*index + 1;
        int min = -1;

        if(l <= size && A[l].getDist() < A[index].getDist()){
            min = l;
        }else{
            min = index;
        }

        if(r <= size && A[r].getDist() < A[min].getDist()){
            min = r;
        }

        if(min != index){
            Node tmp = A[index];
            A[index] = A[min];
            A[min] = tmp;

            minHeapify(A, min);
        }
    }

    public void insert(Node data){
        size++;
        if(size >= Nodes.length){
            //resize
            Node[] tmp = new Node[Nodes.length+10];
            System.arraycopy(Nodes,0,tmp,0,Nodes.length);
            Nodes = tmp;
        }
        Nodes[size] = data;

        buildMinHeap(Nodes);
    }

    public Node extractMin(){
        if(size == 0)
            return null;

        buildMinHeap(Nodes);
        Node root = Nodes[1];
        Nodes[1] = Nodes[size];
        size--;
        buildMinHeap(Nodes);
        return root;
    }
}

class Node{
    private int dist, index;
    private String data = null;

    Node(int index, String data, int dist){
        this.data = data;
        this.dist = dist;
        this.index = index;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getDist() {
        return dist;
    }

    public String getData() {
        return data;
    }

    public int getIndex(){
        return index;
    }
}