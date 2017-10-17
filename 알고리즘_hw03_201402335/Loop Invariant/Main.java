package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("invariant_data.txt"));
        String str;
        String[] strData = null;
        while ((str = reader.readLine()) != null) {
            strData = str.split(" ");
        }
        reader.close();
        int[] A = Arrays.asList(strData).stream().mapToInt(Integer::parseInt).toArray();

        //run sort
        System.out.print("찾으려는 수 입력: ");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();

        //Binary Search (log N search Algorithm)
        //PreCondition : Sorted Array (오름차순)
        Arrays.sort(A);

        //Invariant : look for key
        int mid, x = -1;
        int p = 0, q = A.length;

        long startTime = System.currentTimeMillis();
        while(p <= q){
            mid = (p + q) / 2;
            if(key < A[mid]) q = mid - 1;
            else if (key > A[mid]) p = mid + 1;
            else{
                x = mid;
                break;
            }
        }
        long stopTime = System.currentTimeMillis();
        long exeTime = stopTime - startTime;
        System.out.println("Binary Search Time: " + exeTime);

        //PostCondition : if key found - index of location
        if(key == A[x]) System.out.println("found");
        else  System.out.println("not found");
    }
}
