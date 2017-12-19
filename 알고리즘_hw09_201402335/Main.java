package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("data10.txt"));
        String str;
        int line = 0, nodeNum = 0, s = 0, t = 0, edgeNum = 0;
        int pointer = 0;
        Node[] edges = null;
        while ((str = reader.readLine()) != null) {
            switch (line++){
                case 0:
                    nodeNum = Integer.parseInt(str);
                    break;
                case 1:
                    String[] rootData = str.split(" ");
                    s = Integer.parseInt(rootData[0]); t = Integer.parseInt(rootData[1]);  //s to t
                    break;
                case 2:
                    edgeNum = Integer.parseInt(str);
                    edges = new Node[edgeNum];
                    break;
                default:
                    String[] edgeData = str.split(" ");
                    int from = Integer.parseInt(edgeData[0]);
                    int to = Integer.parseInt(edgeData[1]);
                    int weight = Integer.parseInt(edgeData[2]);
                    edges[pointer++] = new Node(from,to,weight);
                    break;
            }
        }
        reader.close();

        //initialization
        int[] dist = new int[nodeNum];
        for(int i = 0; i < nodeNum; i++){
            if(i == s) dist[i] = 0;
            else dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < nodeNum-1; i++) {
            for (int j = 0; j < edgeNum; j++) {
                //relaxation step
                if (dist[edges[j].getT()] > dist[edges[j].getS()] + edges[j].getW() && dist[edges[j].getS()] != Integer.MAX_VALUE) {
                    System.out.println(edges[j].getS() + " -> " + edges[j].getT());
                    System.out.println("dist["+edges[j].getT()+"]" + " = " + "dist["+edges[j].getS()+"]"  + " + " + edges[j].getW());
                    dist[edges[j].getT()] = dist[edges[j].getS()] + edges[j].getW();
                    for(int k = 0; k < dist.length; k++)
                        System.out.print(dist[k] + " ");
                    System.out.println("\n");
                }
            }
            System.out.println("\n--------------------------------------");
        }

        //check neg-Weight Cycle
        boolean negWeightCycle = false;
        for(int i = 0; i < edgeNum; i++) {
            if (dist[edges[i].getT()] > dist[edges[i].getS()] + edges[i].getW() && dist[edges[i].getS()] != Integer.MAX_VALUE){
                negWeightCycle = true;
            }
        }

        //print result
        if(negWeightCycle)
            System.out.println("negative-weight cycle exist");
        else
            System.out.println("Min dist from "+s + " to "+ t +" : " + dist[t]);
    }
}

class Node{
    private int s, t, w;

    public Node(int s, int t, int w) {
        this.s = s;
        this.t = t;
        this.w = w;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}

