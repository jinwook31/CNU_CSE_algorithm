package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList u = new ArrayList<Vertex>();
        u.add(new Vertex("u", "WHITE", null, new String[]{"v","x"}));
        u.add(new Vertex("v", "WHITE", null, new String[]{"y"}));
        u.add(new Vertex("w", "WHITE", null, new String[]{"y", "z"}));
        u.add(new Vertex("x", "WHITE", null, new String[]{"v"}));
        u.add(new Vertex("y", "WHITE", null, new String[]{"x"}));
        u.add(new Vertex("z", "WHITE", null, new String[]{"z"}));

        BFS bfs = new BFS(u);
    }
}

class BFS{
    private int time = 0;
    private ArrayList<Vertex> G;

    public BFS(ArrayList<Vertex> vertex) {
        this.G = vertex;
        time = 0;

        for (Vertex u: G) {
            if(u.color.equals("WHITE"))
                DFSVisit(u);
        }

        System.out.println("key\t\tpi\t\tu.d\t\tu.f");
        for(int i = 0; i < G.size(); i++){
            if(G.get(i).pi != null)
                System.out.println(G.get(i).key + "\t\t" + G.get(i).pi.key + "\t\t"+G.get(i).d+ "\t\t"+G.get(i).f);
            else
                System.out.println(G.get(i).key + "\t\t-" + "\t\t"+G.get(i).d+ "\t\t"+G.get(i).f);
        }
    }

    private void DFSVisit(Vertex u){
        time++;
        u.d = time;
        u.color = "GRAY";
        for (String vKey:u.adj) {
            Vertex v = G.get(getIndex(vKey));
            if(v.color.equals("WHITE")){
                v.pi = u;
                DFSVisit(v);
            }
        }
        u.color = "BLACK";
        time++;
        u.f = time;
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
    int d, f;
    Vertex pi;//pi

    public Vertex(String key, String color, Vertex pi, String[] adjList) {
        this.key = key;
        this.color = color;
        this.pi = pi;
        adj = new ArrayList();
        for (int i = 0; i<adjList.length; i++){
            adj.add(adjList[i]);
        }
    }
}