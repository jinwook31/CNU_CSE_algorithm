package com.company;

import java.util.Scanner;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    System.out.print("string1 : ");
	    String input1 = sc.next();
        System.out.print("string2 : ");
        String input2 = sc.next();
        System.out.println();

        int[][] path = seqAlignment(input1.length(),input2.length(), input1.toCharArray(), input2.toCharArray(),1,2);

        System.out.println("Min Cost: " + path[input1.length()][input2.length()]);
        for (int i = 0; i < input1.length()+1;i++){
            for (int j = 0; j < input2.length()+1; j++){
                System.out.print(path[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public static int[][] seqAlignment(int m, int n, char[] s1, char[] s2, int g, int a){
        int[][] M = new int[m+1][n+1];

        for(int i = 0; i <= m;i++)
            M[i][0] = i * g;
        for(int j = 0; j <= n;j++)
            M[0][j] = j * g;

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n;j++){
                M[i][j] = min(check(s1[i-1], s2[j-1], a)+M[i-1][j-1], min(g+M[i-1][j], g+M[i][j-1]));
            }
        }
        return M;
    }

    public static int check(char x, char y, int mismatch){
        if(x == y)
            return 0;
        else
            return mismatch;
    }
}
