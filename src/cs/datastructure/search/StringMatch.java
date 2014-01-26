package cs.datastructure.search;

import java.io.File;
import java.io.IOException;

//import org.apache.commons.io.FileUtils;

import cs.datastructure.sort.TimeConsume;

public class StringMatch
{
    static int matchCount(String str, String sub)
    {
        char[] chStr = str.toCharArray();
        char[] chSub = sub.toCharArray();
        int count = 0;
        for(int i = 0; i < chStr.length; i++)
        {
            int j = 0;
            for(int k = i; j < chSub.length && k<chStr.length; j++,k++)
            {
                if(chStr[k] == chSub[j])
                {
                    continue;
                }
                else
                {
                    break;
                }
            }
            if(j == chSub.length)
            {
                count++;
            }
        }
        return count;
    }
    
    static int matchCountKMP(String str, String sub)
    {
        char[] chStr = str.toCharArray();
        char[] chSub = sub.toCharArray();
        int[] next = getnext(sub);
        int count = 0;
        for(int i = 0; i < chStr.length; )
        {
            int j = 0;
            while(j < chSub.length && i < chStr.length) 
            {
                if(chStr[i] == chSub[j])
                {
                    i++;j++;
                    continue;
                }
                else
                {
                    if(next[j] != -1)
                    {
                        j=next[j];
                    }
                    else
                    {
                        i++;
                        break;
                    }
                }
            }
            if(j == chSub.length)
            {
                count++;
            }
        }
        return count;
    }
    

    static int[] getnext(String sub)
    {
        char[] chSub = sub.toCharArray();
//        boolean isSuc = true;
        int[] next = new int[chSub.length];
        next[0] = -1;
        for(int i = 1; i < chSub.length; i++)
        {
            if(next[i-1] == -1 || chSub[i-1] == chSub[next[i-1]]){
                next[i] = next[i-1] + 1;
            }
            else
            {
                next[i] = 0;
            }
        }
//        System.out.println();
//        for(int i = 0; i < next.length; i++)
//        {
//            System.out.print(next[i] + " " );
//        }
//        System.out.println();
        return next;
    }

    public static void main(String[] args)
    {
        TimeConsume tc = new TimeConsume();
        String s = "abssabasssababaababassssababadgababa";
//        try
//        {
//            s = FileUtils.readFileToString(new File("str.txt"));
//        }
//        catch(IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//         String sub = "ababa";
        String sub = "nggasdfnggasknggcsknggmmm";
        tc.start();
        System.out.println(matchCount(s, sub));
        tc.check("string searching");
        
//        tc.start();
        System.out.println(matchCountKMP(s, sub));
        tc.check("string searching KMP");
        
//        getnext("abacabcde");
//        
//        getnext("ababa");
    }

}
