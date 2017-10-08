package com.company;

public class binary_insertionSort {
    public int[] sort(int [] input){
        int[] res = new int [input.length];
        System.arraycopy(input,0,res,0,input.length);

        for(int i = 1; i < res.length; i++){
            int key = res[i];
            int left=0, right=i;

            while(left < right){
                int mid = (right + left) / 2;
                if(key >= res[mid]) left = mid +1; //div again
                else right = mid;  //done
            }
            //swap
            for(int j = i; j > right;  j--){
                int tmp = res[j-1];
                res[j-1] = res[j];
                res[j] = tmp;
            }
        }
        return res;
    }
}
