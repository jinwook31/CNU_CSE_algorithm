package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args)  throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("data06_lateness.txt"));
        String str; int n, i = 0;
        boolean first = true;
        Node[] input = null;

        while ((str = reader.readLine()) != null) {
            if(first){
                first = false;
                n = Integer.parseInt(str);
                input = new Node[n];
            }else{
                String[] tmp = str.split(" ");
                input[i] = new Node(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                i++;
            }
        }
        reader.close();
        //t: 수행에 필요한 시간 & d: 작업이 완료되어야 하는 시간

        //d는 오름차순으로 이미 정렬되어 있음
        System.out.println("Max lateness = " + latness(input));
    }

    public static int latness(Node[] data){
        int t = 0;
        int intervalsTotal = 0;
        int latness = 0;

        for(int j = 0; j < data.length; j++){
            int startTime = t;
            int finishTime = t + data[j].getT();
            intervalsTotal += (finishTime - startTime);
            t += data[j].getT();
            if(t > data[j].getD())
                latness += (t - data[j].getD());
        }

        return latness;
    }

}

class Node{
    private int t, d;
    public Node(int t, int d){
        this.t = t;
        this.d = d;
    }

    public int getT(){return t;}
    public int getD(){return d;}
}
