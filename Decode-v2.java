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
import com.rle.Encode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Decode
{
    public static int numOfZeroes(String huffmanCode, ArrayList<pair<String, String>> huffmanTable)
    {
        int res = 0;
        for(pair<String, String> p : huffmanTable)
        {
            if(huffmanCode.equals(p.second))
            {
                String[] descriptor = p.first.split("/");
                res = Integer.parseInt(descriptor[0]);
                break;
            }
        }
        return res;
    }
    public static int toDecimal(String additionalbits)
    {
        int res = 0;
        res = Integer.parseInt(additionalbits, 2);
        if(additionalbits.charAt(0) == '0')
        {
            res = ~res;
        }
        return res;
    }

    
    public static String decode(ArrayList<pair<String, String>> encodedMsg, ArrayList<pair<String, String>> huffmanTable) throws FileNotFoundException, IOException
    {
        File f = new File("encoding.txt");
        BufferedReader read = new BufferedReader(new FileReader(f));
        ArrayList<pair<String, String>> encodedMsg2 = new ArrayList<>();
        String str = read.readLine();
        
        String[] charArr = str.split(" ");
        for(String s : charArr)
        {
            String[] node = s.split(",");
            pair<String, String> p = new pair<>(node[0], node[1]);
            encodedMsg2.add(p);
        }
        
        File f2 = new File("huffman.txt");
        BufferedReader read2 = new BufferedReader(new FileReader(f2));
        ArrayList<pair<String, String>> huffmanTable2 = new ArrayList<>();
        String str2 = read2.readLine();
        String[] arr2 = str2.split(" ");
        for(String s : arr2)
        {
            String[] node2 = s.split(",");
            pair<String, String> p2 = new pair<>(node2[0], node2[1]);
            huffmanTable2.add(p2);
        }        
     
        String res = "";
        for(pair<String, String> p : encodedMsg2)
        {
            int zeroes = numOfZeroes(p.first, huffmanTable2);
            int num = toDecimal(p.second);

            while(zeroes-- > 0)
            {
                res += "0,";
            }
            res +=  num + ",";
        }
        return res;
    }

    public static void main(String args[]) throws IOException
    {
        String msg = "-2,0,0,2,0,0,3,2,0,1,0,0,-2,0,-1,0,0,1,0,0,-1";
        ArrayList<pair<String, String>> encodedMsg = Encode.encode(msg);
        ArrayList<pair<String, String>> huffmanTable = Encode.huffmanTable;
        String res = decode(encodedMsg, huffmanTable);
        System.out.println(res);
    }
}

