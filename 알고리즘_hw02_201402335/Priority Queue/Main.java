package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        maxHeap h = new maxHeap();

        //read data
        BufferedReader reader = new BufferedReader(new FileReader("data_heap.txt"));
        String str;
        String txt = "";
        while ((str = reader.readLine()) != null) {
            txt += str + "\t";
            String[] tmp = str.split(",");
            h.insert(Integer.parseInt(tmp[0]),tmp[1]);
        }
        reader.close();

        //Queue 정렬
        priotityQueue q = new priotityQueue();

        //Get Inputs
        Scanner sc = new Scanner(System.in);
        int input = 0;
        while (true) {
            System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****\n");

            //print
            h.print();

            System.out.println("-----------------------------------------------");
            System.out.println("1. 작업 추가    2. 최대값  3. 최대 우선순위 작업 처리");
            System.out.println("4. 원소 키값 증가       5. 작업 제거        6. 종료");
            System.out.println("-----------------------------------------------");

            input = sc.nextInt();
            System.out.flush();
            switch (input){
                case 1:
                    Node tmp = new Node(0,null);
                    System.out.print("신규 작업명 (20Bytes 이내) : ");
                    tmp.data = sc.next();
                    System.out.print("우선 순위 (0~999) : ");
                    tmp.index = sc.nextInt();
                    q.insert(h, tmp);
                    break;
                case 2:
                    Node res = q.max(h);
                    System.out.println("**** MAX : "+ res.index +" "+ res.data + " ****\n");
                    break;
                case 3:
                    q.extract_max(h);
                    System.out.println("**** 한 개의 작업을 처리했습니다 ****\n");
                    break;
                case 4:
                    System.out.print("수정할 노드 x : ");
                    int index = sc.nextInt();
                    if(!(index <= h.size)){
                        System.out.print("**** Error: 잘못된 입력 ****");
                        break;
                    }
                    System.out.print("수정할 key : ");
                    int key = sc.nextInt();
                    q.increase_Key(h, index, key);
                    break;
                case 5:
                    System.out.print("삭제할 노드 x 입력 : ");
                    int del = sc.nextInt();
                    q.delete(h, del);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

class maxHeap{
    Node[] Nodes;
    int size;

    public maxHeap(){
        size = 0;
        Nodes = new Node[10];
        Nodes[0] = null; //root
    }

    //입력받은 배열A를 최대 힙으로 만드는 함수
    public void buildMaxHeap(Node[] A){
        for(int i = size/2; i >= 1; i--)
            maxHeapify(A, i);
    }

    //특정 노드를 기점으로, 트리를 내려가며 최대 힙이 되도록 함
    private void maxHeapify(Node[] A, int index){
        int l = 2*index, r=2*index+1;
        int max;
        if(l <= size && A[l].index > A[index].index){
            max = l;
        }else{
            max = index;
        }

        if(r <= size && A[r].index > A[max].index){
            max = r;
        }

        if(max != index){
            Node tmp = A[index];
            A[index] = A[max];
            A[max] = tmp;
            maxHeapify(A, max);
        }
    }

    public void insert(int index, String data){
        size++;
        if(size >= Nodes.length){
            //resize
            Node[] tmp = new Node[Nodes.length+10];
            System.arraycopy(Nodes,0,tmp,0,Nodes.length);
            Nodes = tmp;
        }
        Nodes[size] = new Node(index, data);

        buildMaxHeap(Nodes);
    }

    public Node getMax(){
        return Nodes[1];
    }

    //가장 우선순위가 높은 작업 제거
    public void extractMax(){
        for(int i =1; i < size; i++){
            Nodes[i] = Nodes[i+1];
        }
        Nodes[size] = null;
        size--;
        buildMaxHeap(Nodes);
    }

    public void print(){
        for(int i = 1; i <= size; i++){
            System.out.println(Nodes[i].index + "\t" + Nodes[i].data);
        }
        System.out.println();
    }
}


class priotityQueue {
    public void insert(maxHeap S, Node x){
        S.insert(x.index,x.data);
    }

    public Node max(maxHeap S){
        return S.getMax();
    }

    public void extract_max(maxHeap S){
        S.extractMax();
    }

    public void increase_Key(maxHeap S, int x, int k){
        S.Nodes[x].index = k;
    }

    public void delete(maxHeap S, int x){
        for(int i = x; i < S.size; i++){
            S.Nodes[i] = S.Nodes[i+1];
        }
        S.Nodes[S.size] = null;
        S.size--;

        S.buildMaxHeap(S.Nodes);
    }
}


//Node 클래스
class Node{
    int index;
    String data;

    Node(int index, String data){
        this.data = data;
        this.index = index;
    }
}