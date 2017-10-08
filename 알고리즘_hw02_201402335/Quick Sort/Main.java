package com.company;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String str;
        String[] strData = null;
        while ((str = reader.readLine()) != null) {
            strData = str.split(" ");
        }
        reader.close();
        int[] data = Arrays.asList(strData).stream().mapToInt(Integer::parseInt).toArray();

        //run sort
        long startTime = System.currentTimeMillis();
        quickSort q = new quickSort(data);
        int [] res = q.getData();
        long stopTime = System.currentTimeMillis();
        long exeTime = stopTime - startTime;
        System.out.println("Quick Sort Time: " + exeTime);

        //record result
        FileWriter writer = new FileWriter("201402335_quick.txt");  //merge sort
        //int arr to string arr
        String wrtData = Arrays.toString(res).replace(",", "").replace("[", "").replace("]", "");
        writer.write(wrtData);
        writer.close();
    }
}

class quickSort {
    private int[] data;

    public quickSort(int[] data){
        this.data = data;
        quickSort(data,0,data.length-1);
    }

    public void quickSort(int[] A, int p, int r){
        if(p < r){
            int q = partition(A,p,r);
            quickSort(A,p,q-1);
            quickSort(A,q+1,r);
        }
    }

    private int partition(int[] A, int p, int r){
        int x = A[r];
        int i = p, j = r-1;
        while(j != i-1){
            if(A[r] >= A[i]) i++;
            else if(A[r] < A[j]) j--;
            else {
                swap(A,i,j);
                i++;
                j--;
            }
        }
        swap(A,i,r);
        return i;
    }

    private void swap(int[] A, int p, int r){
        int tmp = A[p];
        A[p] = A[r];
        A[r] = tmp;
    }

    public int[] getData(){
        return data;
    }
}