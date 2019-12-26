import java.util.ArrayList;

public class RLE {

    static ArrayList<String> additionalBits = new ArrayList<>();

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
    static ArrayList<pair<Integer, Integer>> generateDescriptor(ArrayList<Integer> arr)
    {
        ArrayList<pair<Integer, Integer>> descriptors = new ArrayList<>();
        int zeroes = 0;
        for(int i = 0 ; i<arr.size() ; i++)
        {

            if(arr.get(i) != 0)
            {
                additionalBits.add(convertToBianry(arr.get(i)));
                pair<Integer, Integer> p = new pair<>();
                p.first = zeroes;
                p.second = category(arr.get(i));
                descriptors.add(p);
                zeroes = 0;
            }
            else zeroes++;
        }
        return descriptors;
    }
    public static void main(String args[])
    {
        String str = "-2,0,0,2,0,0,3,2,0,1,0,0,-2,0,-1,0,0,1,0,0,-1";
        ArrayList<Integer> arr = convertToArr(str);
        for(Integer i : arr)
        {
            System.out.println(i);
        }
    }
}
