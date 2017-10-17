package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("data05_Karatsuba.txt"));
        String str; int i = 0;
        String[] strData = new String[2];
        while ((str = reader.readLine()) != null) {
            strData[i++] = str;
        }
        reader.close();

        BigInteger[] num = new BigInteger[2];
        num[0] = new BigInteger(strData[0]);  //A
        num[1] = new BigInteger(strData[1]);  //B

        System.out.println(karatsuba(num[0], num[1]));
    }

    private static BigInteger karatsuba(BigInteger A, BigInteger B){
        String a = "" + A;
        String b = "" + B ;
        if(a.length() < 2 || b.length() < 2)    return A.multiply(B);
        int n = a.length();
        int m = n/2;

        BigInteger x1 = new BigInteger(divNum(m,a)[0]);
        BigInteger x2 = new BigInteger(divNum(m,a)[1]);
        BigInteger y1 = new BigInteger(divNum(m,b)[0]);
        BigInteger y2 = new BigInteger(divNum(m,b)[1]);

        BigInteger z0 = karatsuba(x2, y2);
        BigInteger z2 = karatsuba(x1, y1);
        BigInteger z1 = karatsuba(x1.add(x2), y2.add(y1)).subtract(z2.add(z0));

        //z2*10^2m + z1*10^m + z0
        BigInteger res = z2.multiply(new BigInteger(String.valueOf(power(10, 2*m))));
        res = res.add(z1.multiply(new BigInteger(String.valueOf(power(10, m)))));
        res = res.add(z0);
        return res;
    }

    public static String[] divNum(int index, String number) {
        String[] res = new String[2];
        int i = number.length() - index;
        res[0] = number.substring(0, i);
        res[1] = number.substring(i,number.length());
        return res;
    }

    public static long power(int x, int y) {
        if (y == 0)
            return 1;
        else {
            int res = 1;
            for (int i = 1; i<=y; i++) {
                res *= x;
            }
            return res;
        }
    }
}
