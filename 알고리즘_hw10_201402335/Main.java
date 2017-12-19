package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("data11.txt"));
        String str;
        String[] strData = null;
        ArrayList<Node> list = new ArrayList<Node>();
        while ((str = reader.readLine()) != null) {
            strData = str.split("\t");
            list.add(new Node(Float.parseFloat(strData[0]), Float.parseFloat(strData[1])));
        }
        reader.close();

        int n = list.size() - 1;  //remove '-1' count

        opticalBST(list, n);
    }

    public static void opticalBST(ArrayList<Node> list, int n){
        float[][] e = new float[n+2][n+1];
        float[][] w = new float[n+2][n+1];
        int[][] root = new int[n+1][n+1];

        //init
        for(int i = 1; i <= n+1; i++){
            e[i][i-1] = list.get(i-1).getQ();
            w[i][i-1] = list.get(i-1).getQ();
        }

        for(int l = 1; l <= n; l++){
            for(int i = 1; i <= n-l+1; i++){
                int j = i + l - 1;
                e[i][j] = Float.MAX_VALUE;
                w[i][j] = w[i][j-1] + list.get(j).getQ() + list.get(j).getP();
                for (int r = i; r <= j; r++){
                        float t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                        t = Float.parseFloat(String.format("%.2f",t));
                        if (t < e[i][j]) {
                            e[i][j] = t;
                            root[i][j] = r;
                        }
                }
            }
        }

        //print e
        System.out.println("<e>");
        printMatrix(e);

        //print w
        System.out.println("<w>");
        printMatrix(w);

        //print root
        System.out.println("<root>");
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < n+1; j++)
                System.out.print(root[i][j] + "    ");
            System.out.println();
        }

        System.out.println("\nMin cost : "+ e[1][5]);
    }

    public static void printMatrix(float[][] mat){
        for(int i = 1; i < mat.length; i++){
            for(int j = 0; j < mat.length-1;j++){
                String num = String.format("%.2f" , mat[i][j]);
                System.out.print(num + "    ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}

class Node{
    private float p, q;

    public Node(float p, float q) {
        this.p = p;
        this.q = q;
    }

    public float getP() {
        return p;
    }

    public float getQ() {
        return q;
    }
}