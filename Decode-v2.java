import java.util.ArrayList;

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

    public static String decode(ArrayList<pair<String, String>> encodedMsg, ArrayList<pair<String, String>> huffmanTable)
    {
        String res = "";
        for(pair<String, String> p : encodedMsg)
        {
            int zeroes = numOfZeroes(p.first, huffmanTable);
            int num = toDecimal(p.second);
            while(zeroes > 0)
            {
                res += "0,";
            }
            res +=  num + ",";
        }
        return res;
    }
    public static void main(String args[])
    {
        /*String msg = "-2,0,0,2,0,0,3,2,0,1,0,0,-2,0,-1,0,0,1,0,0,-1";
        ArrayList<pair<String, String>> encodedMsg = RLE.encode(msg);
        huffmanTable = RLE.huffmanTable;*/
        String s = "01";
        int res = Integer.parseInt(s, 2);
        System.out.println(~res);
    }
}
