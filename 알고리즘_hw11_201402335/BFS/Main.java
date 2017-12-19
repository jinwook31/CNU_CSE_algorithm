package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        ArrayList u = new ArrayList<Vertex>();
        u.add(new Vertex("s", "GRAY", 0, null, new String[]{"r", "w"}));
        u.add(new Vertex("r", "WHITE", Integer.MAX_VALUE, null, new String[]{"s", "v"}));
        u.add(new Vertex("t", "WHITE", Integer.MAX_VALUE, null, new String[]{"w","x","u"}));
        u.add(new Vertex("u", "WHITE", Integer.MAX_VALUE, null, new String[]{"t","x","y"}));
        u.add(new Vertex("v", "WHITE", Integer.MAX_VALUE, null, new String[]{"r"}));
        u.add(new Vertex("w", "WHITE", Integer.MAX_VALUE, null, new String[]{"s", "t","x"}));
        u.add(new Vertex("x", "WHITE", Integer.MAX_VALUE, null, new String[]{"w", "t","u","y"}));
        u.add(new Vertex("y", "WHITE", Integer.MAX_VALUE, null, new String[]{"x", "u"}));

        BFS bfs = new BFS(u);
    }
}

class BFS{
    private ArrayList<Vertex> G;
    private Queue<Vertex> q;

    public BFS(ArrayList<Vertex> vertex) {
        this.G = vertex;

        q = new LinkedList<Vertex>();
        q.add(G.get(getIndex("s")));

        while (q.size() != 0){
            Vertex u = q.remove();
            for (String vKey:u.adj) {
                Vertex v = G.get(getIndex(vKey));
                if(v.color.equals("WHITE")){
                    v.color = "GRAY";
                    v.d = u.d + 1;
                    v.pi = u;
                    q.add(v);
                }
            }
            u.color = "BLACK";
        }

        System.out.println("key\t->\tpi\td\n");
        for(int i = 1; i<G.size(); i++){
            System.out.println(G.get(i).key + "\t->\t" + G.get(i).pi.key + "\t"+G.get(i).d);
        }
    }

    private int getIndex(String key){
        for(int i=0; i<G.size(); i++){
            if(G.get(i).key.equals(key)) return i;
        }
        return -1;
    }

}

class Vertex{
    String key, color;
    ArrayList<String> adj;
    int d;
    Vertex pi;//pi

    public Vertex(String key, String color, int d, Vertex pi, String[] adjList) {
        this.key = key;
        this.color = color;
        this.d = d;
        this.pi = pi;
        adj = new ArrayList();
        for (int i = 0; i<adjList.length; i++){
            adj.add(adjList[i]);
        }
    }
}