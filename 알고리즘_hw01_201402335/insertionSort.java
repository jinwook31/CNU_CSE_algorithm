package com.company;

public class insertionSort {
    public int[] sort(int [] input){
        int[] res = new int [input.length];
        System.arraycopy(input,0,res,0,input.length);

        int key = 1;
        while(key < res.length){
            for(int i = key-1; i > -1; i--){
                if(res[i] > res[i+1]){
                    //swap
                    int tmp = res[i+1];
                    res[i+1] = res[i];
                    res[i] = tmp;
                }else{ //done
                    break;
                }
            }
            key++;
        }

        return res;
    }
}
