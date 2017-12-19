package com.company;

public class Main {

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;

        //weight
        int[][] w =
                        {{0,4,max,max,max,max,max,8,max},
                        {4,0,8,max,max,max,max,11,max},
                        {max,8,0,7,max,4,max,max,2},
                        {max,max,7,0,9,14,max,max,max},
                        {max,max,max,9,0,10,max,max,max},
                        {max,max,4,14,10,0,2,max,max},
                        {max,max,max,max,max,2,0,1,6},
                        {8,11,max,max,max,max,1,0,7},
                        {max,max,2,max,max,max,6,7,0}};

        Node s = new Node(0," ", "A");
        minHeap Q = new minHeap(10);
        Q.insert(s);

        Boolean[] visit = new Boolean[w[0].length];
        int[] key = new int[visit.length];
        for (int i = 0; i < visit.length;i++) {
            visit[i] = false;
            key[i] = 0;
        }

        while(Q.getSize() != 0){
            Node vertex = Q.extractMin();
            int cur = StringtoIndex(vertex.getV());
            int weight = vertex.getDist();
            if(visit[cur]) continue;
            visit[cur] = true;
            key[cur] = weight;
            System.out.println("w<"+vertex.getU()+", "+vertex.getV()+"> = "+weight);
            for(int i = 0; i < visit.length; i++){
                if(w[cur][i] != max){
                    if(!visit[i])
                        Q.insert(new Node(w[cur][i], IndextoString(cur), IndextoString(i)));
                }
            }
        }

        int total = 0;
        for (int dis:key) {
            total += dis;
        }
        System.out.println("\nw<MST> = "+total);
    }

    public static String IndextoString(int index){
        switch (index){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            default:
                return null;
        }
    }

    public static int StringtoIndex(String vertex){
        switch (vertex){
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            default:
                return -1;
        }
    }
}

class minHeap{
    private Node[] Nodes;
    private int size;

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
    private int dist;
    private String u,v;

    public Node(int dist, String u, String v) {
        this.dist = dist;
        this.u = u;
        this.v = v;
    }
    public int getDist() {
        return dist;
    }
    public String getU() {
        return u;
    }
    public String getV() {
        return v;
    }

}