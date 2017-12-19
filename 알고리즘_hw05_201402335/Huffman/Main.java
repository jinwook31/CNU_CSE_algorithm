package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args)  throws IOException {
        //read data
        BufferedReader reader = new BufferedReader(new FileReader("data06_huffman.txt"));
        String str = reader.readLine();
        String[] input = str.split("");
        reader.close();

        minHeap m = new minHeap();

        //check freq
        String word = "";
        for(int i = 0; i < input.length; i++){
            String data = input[i];
            int count = 1;
            if(word.contains(data)){
                continue;
            }

            for(int j = i+1; j < input.length; j++){
                if(input[j].compareTo(data) == 0)
                    count++;
            }

            word += data+"";
            Node tmp = new Node(count, data);
            m.insert(tmp);
        }

        Huffman h = new Huffman(m);
    }
}

class Huffman{
    minHeap m;

    public Huffman(minHeap m){
        this.m = m;
        Node tree = HuffmanTree();
        encode(tree, "");
    }

    public Node HuffmanTree(){
        int size = m.size;
        for(int i = 1; i < size; i++){
            Node tmp = new Node();
            Node tmpLeft = m.extractMin();
            Node tmpRight = m.extractMin();
            tmp.setLeft(tmpLeft);
            tmp.setRight(tmpRight);
            tmp.setFreq(tmpLeft.freq + tmpRight.freq);
            m.insert(tmp);
        }

        return m.extractMin();
    }

    public void encode(Node tree, String code){
        if(tree != null){
            if(tree.isLeaf) {
                tree.code = code;
                System.out.println(tree.data +", "+tree.code);
            }else{
                encode(tree.left, code+"0");
                encode(tree.right, code+"1");
            }
        }
    }
}


class minHeap{
    Node[] Nodes;
    int size;

    public minHeap(){
        size = 0;
        Nodes = new Node[10];
        Nodes[0] = null; //root
    }

    public void buildMinHeap(Node[] A){
        for(int i = size/2; i >= 1; i--)
            minHeapify(A, i);
    }


    private void minHeapify(Node[] A, int index){
        int l = 2*index, r = 2*index + 1;
        int min = -1;

        if(l <= size && A[l].freq < A[index].freq){
            min = l;
        }else{
            min = index;
        }

        if(r <= size && A[r].freq < A[min].freq){
            min = r;
        }

        if(min != index){
            Node tmp = A[index];
            A[index] = A[min];
            A[min] = tmp;

            minHeapify(A, min);
        }
    }

    public void insert(Node data){
        size++;
        if(size >= Nodes.length){
            //resize
            Node[] tmp = new Node[Nodes.length+10];
            System.arraycopy(Nodes,0,tmp,0,Nodes.length);
            Nodes = tmp;
        }
        Nodes[size] = data;

        buildMinHeap(Nodes);
    }

    public Node extractMin(){
        if(size == 0)
            return null;

        buildMinHeap(Nodes);
        Node root = Nodes[1];
        Nodes[1] = Nodes[size];
        size--;
        buildMinHeap(Nodes);
        return root;
    }
}

class Node{
    int freq;
    Boolean isLeaf = false;
    String data = null;
    Node left, right;
    String code;

    Node(){

    }

    Node(int index, String data){
        this.data = data;
        this.freq = index;
        this.isLeaf = true;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}