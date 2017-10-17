package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("closest_data.txt"));
        String str;
        String[] data = new String[10];
        int size = 0;
        while ((str = reader.readLine()) != null) {
            if(size == data.length){
                //resize
                data = Arrays.copyOf(data, data.length+10);
            }
            data[size] = str;
            size++;
        }
        reader.close();

        Node[] dots = new Node[size];
        int total = 0;
        for(int i = 0; i<size; i++){
            String[] tmp = data[i].split(" ");
            dots[i] = new Node(Double.parseDouble(tmp[0]),Double.parseDouble(tmp[1]));
            total += Double.parseDouble(tmp[0]);
        }

        //1. x좌표 기준으로 오름차순 정렬
        quickSort(dots, 0, dots.length-1);

        System.out.println(clostestPair(dots));
    }

    public static double clostestPair(Node[] dot){
        double d1Min, d2Min;

        if(dot.length <= 3) return brute_force(dot);
        else{
            //2. 두 개의 같은 크기의 집합으로 나뉘도록 수직선 L 생성
            int mid = dot.length/2;
            Node[] tmp1 = new Node[mid];
            Node[] tmp2 = new Node[dot.length - mid];

            //3. check left, right dots (recursive)
            System.arraycopy(dot,0,tmp1,0,mid);
            System.arraycopy(dot,mid,tmp2,0,dot.length-mid);
            d1Min = clostestPair(tmp1);
            d2Min = clostestPair(tmp2);

            //4. get min(l,r) & check near L dots
            Node[] nearL = searchWindow(dot, min(d1Min,d2Min), mid);
            quickSortY(nearL, 0 ,nearL.length-1);
            double n = brute_force(nearL);

            //5. choose min(l,r,n) & print result
            if(n!=0)  return min(n, min(d1Min, d2Min));
            else return min(d1Min, d2Min);
        }
    }

    public static double brute_force(Node[] dot){
        double min=0 ;
        for(int i=0; i < dot.length-1; i++){
            for(int j=i+1; j < dot.length; j++){
                if(min == 0){
                    min = distance(dot[i], dot[j]);
                }
                else{
                    min = min(min, distance(dot[i], dot[j]));}
            }
        }
        return min;
    }

    public static double distance(Node a, Node b){
        BigDecimal aX = new BigDecimal(String.valueOf(a.getX()));
        BigDecimal aY = new BigDecimal(String.valueOf(a.getY()));
        BigDecimal bX = new BigDecimal(String.valueOf(b.getX()));
        BigDecimal bY = new BigDecimal(String.valueOf(b.getY()));

        double X = pow(aX.subtract(bX).doubleValue(),2);
        double Y = pow(aY.subtract(bY).doubleValue(),2);

        return sqrt(X+Y);
    }


    public static Node[] searchWindow(Node[] dot, double minL, int basicLine){
        int leftPivot = basicLine;
        int rightPivot = basicLine;

        String x = String.valueOf(minL);
        String y = String.valueOf(dot[basicLine].getX());
        BigDecimal min_x = new BigDecimal(x);
        BigDecimal basic = new BigDecimal(y);
        double left = basic.subtract(min_x).doubleValue();
        double right = basic.add(min_x).doubleValue();

        while((leftPivot>=1)){
            if(dot[leftPivot-1].getX()>=left){
                leftPivot--;
            }
            else{
                break;
            }
        }
        while((rightPivot+1<dot.length)){
            if(dot[rightPivot+1].getX() <=right){
                rightPivot++;
            }
            else{
                break;
            }
        }
        Node[] res = new Node[rightPivot-leftPivot+1];
        int temp=leftPivot;
        for(int i=0; i<res.length;i++){

            res[i]= dot[temp++];
        }
        return res;
    }


    public static int getIndex(Node[] dots, double x){
        for(int i=0; i < dots.length; i++){
            if(dots[i].getX() > x){
                return i;
            }
        }
        return dots.length-1;
    }


    public static void quickSort(Node[] A, int p, int r){
        if(p < r){
            int q = partition(A,p,r);
            quickSort(A,p,q-1);
            quickSort(A,q+1,r);
        }
    }

    private static int partition(Node[] A, int p, int r){
        double x = A[r].getX();
        int i = p, j = r-1;
        while(j != i-1){
            if(A[r].getX() >= A[i].getX()) i++;
            else if(A[r].getX() < A[j].getX()) j--;
            else {
                swap(A,i,j);
                i++;
                j--;
            }
        }
        swap(A,i,r);
        return i;
    }

    public static void quickSortY(Node[] A, int p, int r){
        if(p < r){
            int q = partitionY(A,p,r);
            quickSort(A,p,q-1);
            quickSort(A,q+1,r);
        }
    }

    private static int partitionY(Node[] A, int p, int r){
        double x = A[r].getY();
        int i = p, j = r-1;
        while(j != i-1){
            if(A[r].getY() >= A[i].getY()) i++;
            else if(A[r].getY() < A[j].getY()) j--;
            else {
                swap(A,i,j);
                i++;
                j--;
            }
        }
        swap(A,i,r);
        return i;
    }

    private static void swap(Node[] A, int p, int r){
        Node tmp = A[p];
        A[p] = A[r];
        A[r] = tmp;
    }
}

class Node{
    private double x, y;
    public Node(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){return x;}
    public double getY(){return y;}
}