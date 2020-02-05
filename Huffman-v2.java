/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rle;

/**
 *
 * @author saad
 */

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

// node class is the basic structure 
// of each node present in the Huffman - tree. 
class HuffmanNode {

    int data;
    String c;

    HuffmanNode left;
    HuffmanNode right;
}

// comparator class helps to compare the node 
// on the basis of one of its attribute. 
// Here we will be compared 
// on the basis of data values of the nodes. 
class MyComparator implements Comparator<HuffmanNode> {

    public int compare(HuffmanNode x, HuffmanNode y)
    {
        return x.data - y.data;
    }
}

public class Huffman
{
    static ArrayList<pair<String, String>> huffmanTable = new ArrayList<>();
    public static void printTable(ArrayList<pair<String, String>> table)
    {
        for(pair<String,String> p : table)
        {
            System.out.println(p.first + ":" + p.second);
        }
    }
    public static void generateTable(HuffmanNode root, String s)
    {
        if (root.left == null && root.right == null)
        {
            pair<String, String> huffman = new pair<>();
            huffman.first = root.c;
            huffman.second = s;
            huffmanTable.add(huffman);
            return;
        }
        generateTable(root.left, s + "0");
        generateTable(root.right, s + "1");
    }

    public static ArrayList<String> getUniqueArr(ArrayList<String> charArray)
    {
        ArrayList<String> uniqueArr = new ArrayList<>();
        for(String s : charArray)
        {
            boolean found = false;
            for(String us : uniqueArr)
            {
                if(s.equals(us))
                {
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                uniqueArr.add(s);
            }
        }
        return uniqueArr;
    }

    public static ArrayList<Integer> getFrequencies(ArrayList<String> charArray, ArrayList<String> uniqueArr)
    {
        ArrayList<Integer> frequencies = new ArrayList<>();
        for(int i = 0 ; i < uniqueArr.size() ; i++)
        {
            int ctr = 0;
            for(int j = 0 ; j<charArray.size() ; j++)
            {
                if(uniqueArr.get(i).equals(charArray.get(j)))
                {
                    ctr++;
                }
            }
            frequencies.add(ctr);
        }
        return frequencies;
    }
    public static ArrayList<pair<String, String>> getTable(ArrayList<String> charArray)
    {
        ArrayList<String> uniqueArr = getUniqueArr(charArray);
        ArrayList<Integer> frequencies = getFrequencies(charArray, uniqueArr);

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(uniqueArr.size(), new MyComparator());
        for (int i = 0; i < uniqueArr.size() ; i++)
        {
            HuffmanNode hn = new HuffmanNode();

            hn.c = uniqueArr.get(i);
          //  System.out.println("hn.data = " + frequencies.get(i));
            hn.data = frequencies.get(i);

            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        HuffmanNode root = null;

        while (q.size() > 1)
        {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = "-";

            f.left = x;
            f.right = y;

            root = f;
            q.add(f);
        }

        generateTable(root, "");
        return huffmanTable;
    }
    public static void main(String args[])
    {
        ArrayList<String> charArray = new ArrayList<>();
        charArray.add("0/2");
        charArray.add("2/2");
        charArray.add("2/2");
        charArray.add("0/2");
        charArray.add("1/1");
        charArray.add("2/2");
        charArray.add("1/1");
        charArray.add("2/1");
        charArray.add("2/1");
        charArray.add("EOB");

        ArrayList<pair<String, String>> a = getTable(charArray);
        printTable(huffmanTable);
    }
} 
  
