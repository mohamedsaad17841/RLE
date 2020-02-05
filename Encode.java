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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;

public class Encode {

    static ArrayList<String> additionalBits = new ArrayList<>();
    static ArrayList<pair<String, String>> huffmanTable = new ArrayList<>();

    public static ArrayList<pair<String, String>> getHuffmanTable()
    {
        return huffmanTable;
    }
    static ArrayList<Integer> convertToArr2(String str)
    {
        boolean ok = false;
        ArrayList<Integer> arr = new ArrayList<>();
        int num = 0;
        for(int i = 0 ; i<str.length() ; i++)
        {
            if(str.charAt(i) == '-')
            {
                ok = true;
                continue;
            }
            else
            {
                num = str.charAt(i) - '0';
            }
            if(ok)
            {
                num *= -1;
                ok = false;
            }
            arr.add(num);
        }
        return arr;
    }
    static ArrayList<Integer> convertToArr(String str)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        String[] strArr = str.split(",");
        for(int i=0 ; i<strArr.length ; i++)
        {
            arr.add(Integer.parseInt(strArr[i]));
        }
        return arr;
    }
    static int category(int num)
    {
        if(num < 0) num *= -1;                                            //convert -ve to +ve, as their category will be the same
        if((num & (num - 1)) == 0) num++;                                 //if power of two, decrement
        int res =  (int) Math.ceil (( Math.log(num) / Math.log(2) ) );
        return res;
    }
    static String convertToBianry(int num)
    {
        String str = "";
        if(num < 0)
        {
            num *= -1;
            str = Integer.toBinaryString(~num);
            str = str.substring(str.length() - category(num));
        }
        else str =  Integer.toBinaryString(num);
        return str;
    }
    static ArrayList<String> generateDescriptors(ArrayList<Integer> arr)
    {
        ArrayList<String> descriptors = new ArrayList<>();
        int zeroes = 0;
        for(int i = 0 ; i<arr.size() ; i++)
        {

            if(arr.get(i) != 0)
            {
                additionalBits.add(convertToBianry(arr.get(i)));
                descriptors.add(zeroes + "/" + category(arr.get(i)));
                zeroes = 0;
            } 
            else zeroes++;
        }
        return descriptors;
    }
    public static ArrayList<pair<String, String>> encode(String msg) throws IOException {

        ArrayList<pair<String, String>> encodedMsg = new ArrayList<>();
        ArrayList<Integer> arr = convertToArr(msg);
        ArrayList<String> descriptors = generateDescriptors(arr);
        huffmanTable = Huffman.getTable(descriptors);
        
        File f2 = new File("huffman.txt");
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(f2));
        for(pair<String, String> p : huffmanTable)
        {
            writer2.write(p.first + "," +  p.second + " ");
        }
        writer2.close();
        File f = new File("encoding.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < descriptors.size(); i++)
        {
            for (pair<String, String> p : huffmanTable)
            {
                if (descriptors.get(i).equals(p.first))
                {
                    pair<String, String> code = new pair<String, String>(p.second, additionalBits.get(i));
                    writer.write(code.first + "," + code.second + " ");
                    encodedMsg.add(code);
                    break;
                }
            }
        }
        writer.close();
        return encodedMsg;
    }
    public static void main(String args[]) throws IOException
    {
//        String msg = "-2,0,0,2,0,0,3,2,0,1,0,0,-2,0,-1,0,0,1,0,0,-1";
//        ArrayList<pair<String, String>> encodedMsg = encode(msg);      
//        for(pair<String, String> p : encodedMsg)
//        {
//            System.out.println(p.first + "," + p.second);           
//        }
        
    }

}

