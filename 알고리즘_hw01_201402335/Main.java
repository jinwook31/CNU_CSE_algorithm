package com.company;

import java.io.*;
import java.util.Arrays;

/**
 * Created by jinwook31 on 13/09/2017.
 */
public class Main {
    private final static mergeSort m1 = new mergeSort();
    private final static insertionSort s1 = new insertionSort();
    private final static binary_insertionSort bs1 = new binary_insertionSort();

    public static void main (String [] args) throws IOException {
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
        int[] res = sort(data,0);  //0: insertion, 1: binary insertion. else: merge

        //record result
        FileWriter writer = new FileWriter("201402335_output.txt");  //merge sort
        //int arr to string arr
        String wrtData = Arrays.toString(res).replace(",", "").replace("[", "").replace("]", "");
        writer.write(wrtData);
        writer.close();
    }

    private static int[] sort(int [] data, int sortnum){
        long startTime = System.currentTimeMillis();
        int[] insertion_sort = s1.sort(data);
        long stopTime = System.currentTimeMillis();
        long exeTime = stopTime - startTime;
        System.out.println("Insertion Sort Time: " + exeTime);

        startTime = System.currentTimeMillis();
        int[] binary_insertion_sort = bs1.sort(data);
        stopTime = System.currentTimeMillis();
        exeTime = stopTime - startTime;
        System.out.println("Binary insertion Sort Time: " + exeTime);

        startTime = System.currentTimeMillis();
        int[] merge_sort = m1.mergeSort(data);
        stopTime = System.currentTimeMillis();
        exeTime = stopTime - startTime;
        System.out.println("Merge Sort Time: " + exeTime);

        if(sortnum == 0) return insertion_sort;
        else if(sortnum == 1) return binary_insertion_sort;
        else return merge_sort;
    }
}
