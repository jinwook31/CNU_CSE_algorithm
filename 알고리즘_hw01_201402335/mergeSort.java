package com.company;

public class mergeSort {
    public int[] mergeSort(int[] input){  //div with recursive func
        if (input.length == 1) return input;
        int[] a1 = new int [input.length/2];
        int[] a2 = new int [input.length - a1.length];
        System.arraycopy(input, 0, a1, 0, a1.length);
        System.arraycopy(input, a1.length,a2, 0 ,a2.length);
        int[] tmp1 = mergeSort(a1);
        int[] tmp2 = mergeSort(a2);
        return merge(tmp1, tmp2);
    }

    private int[] merge(int[] a, int[] b){  //merge diveded cells (select small val in each array)
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
                res[k] = b[j];
                j++;
            } else {
                res[k] = a[i];
                i++;
            }
        }
        return res;
    }
}
