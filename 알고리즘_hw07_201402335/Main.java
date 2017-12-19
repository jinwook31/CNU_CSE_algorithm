package com.company;

import java.util.Scanner;
import static sun.swing.MenuItemLayoutHelper.max;

public class Main {
    private static Node[] items;
    private static int max = 0;
    private static String picked = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bag = sc.nextLine();
        String[] input = bag.split(" ");
        int numItem = Integer.parseInt(input[0]);
        int weightLimit = Integer.parseInt(input[1]);

        items = new Node[numItem];
        for(int i = 0; i < numItem; i++){
            bag = sc.nextLine();
            input = bag.split(" ");
            items[i] = new Node(Integer.parseInt(input[1]),Integer.parseInt(input[0]));
        }

        String[][] pickedList = new String[numItem+1][weightLimit+1];

        int[][] OPT = new int [numItem+1][weightLimit+1];
        for(int n = 0; n < numItem+1; n++){
            for(int w = 0; w <weightLimit+1; w++){
                if(n == 0) {
                    OPT[n][w] = 0;
                    pickedList[n][w] = "";
                }else if(items[n-1].getWeight() > w){
                    OPT[n][w] = OPT[n-1][w];
                    pickedList[n][w] = pickedList[n-1][w];
                }else{
                    OPT[n][w] = max(OPT[n-1][w], items[n-1].getValue() + OPT[n-1][w - items[n-1].getWeight()]);
                    int prevMax = max;
                    max = max(max, OPT[n][w]);

                    if(OPT[n-1][w - items[n-1].getWeight()] == 0){
                        pickedList[n][w] = n + " ";
                    }else{
                        pickedList[n][w] = n + " " + pickedList[n-1][w - items[n-1].getWeight()];
                    }

                    if(prevMax != max) picked = pickedList[n][w];
                }
            }
        }


        for(int i = 0; i < numItem + 1;i++){
            for(int j = 0; j < weightLimit+1; j++){
                System.out.print(OPT[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("max: " + max);
        System.out.println("item: " + picked);
    }
}

class Node{
    private int weight;
    private int value;

    public Node(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}