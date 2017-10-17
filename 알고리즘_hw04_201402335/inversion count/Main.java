package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("data05_inversion_01.txt"));
        String str;
        String[] strData = null;
        while ((str = reader.readLine()) != null) {
            strData = str.split(" ");
        }
        reader.close();
        int[] data = Arrays.asList(strData).stream().mapToInt(Integer::parseInt).toArray();

        System.out.println(Sort(data).getCount());
    }

    public static result Sort(int[] input){  //div with recursive func
        if (input.length == 1) {
            result tmp = new result(input, 0);
            return tmp;
        }
        int[] a1 = new int [input.length/2];
        int[] a2 = new int [input.length - a1.length];
        System.arraycopy(input, 0, a1, 0, a1.length);
        System.arraycopy(input, a1.length,a2, 0 ,a2.length);
        result left = Sort(a1);
        result right = Sort(a2);
        int[] l = left.getArr();
        int[] r = right.getArr();
        result merge = merge(l,r);
        result res = new result(merge.getArr(), left.getCount() + right.getCount() + merge.getCount());
        return res;
    }

    private static result merge(int[] a, int[] b){  //merge diveded cells (select small val in each array)
        int inversion = 0;
        //merge
        int[]res = new int [a.length + b.length];
        int i=0, j=0;
        for(int k = 0; k < res.length; k++) {
            if(i == a.length){
                res[k] = b[j];
                j++;
                continue;
            }else if(j == b.length){
                res[k] = a[i];
                i++;
                continue;
            }

            if (a[i] > b[j]) {
                //count inversion
                inversion += a.length - i;
                res[k] = b[j];
                j++;
            } else {
                res[k] = a[i];
                i++;
            }
        }

        return new result(res, inversion);
    }
}

class result {
    private int[] arr;
    private int count;

    public result(int[] arr, int count) {
        this.arr = arr;
        this.count = count;
    }

    public int[] getArr() {
        return arr;
    }

    public int getCount() {
        return count;
    }
}