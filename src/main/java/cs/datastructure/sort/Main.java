package cs.datastructure.sort;

import java.util.Arrays;

public class Main
{
    static int MAX = 1000000; 
    public static void main(String[] args)
    {
        TimeConsume tc = new TimeConsume();
        tc.start();
        int[] lsinit = new int[MAX];
        int[] ls = null;
        BaseSort bs;
        int seed = MAX * 5;
        for(int i = 0; i < lsinit.length; i++)
        {
            lsinit[i] = (int) (Math.random() * seed);
        }
        tc.check("init");

        ls = Arrays.copyOf(lsinit, lsinit.length);
        bs = new QuickSort();
        bs.sort(ls);        
        tc.check("quick sort");
        
        ls = Arrays.copyOf(lsinit, lsinit.length);
        bs = new HeapSort();
        bs.sort(ls);        
        tc.check("heap sort");
        
        ls = Arrays.copyOf(lsinit, lsinit.length);
        bs = new MergeSort();
        bs.sort(ls);        
        tc.check("merge sort");
      
//      ls = Arrays.copyOf(lsinit, lsinit.length);
//      bs = new SelectionSort();
//      bs.sort(ls);        
//      tc.check("selection sort");
//      
//      ls = Arrays.copyOf(lsinit, lsinit.length);
//      bs = new InsertSort();
//      bs.sort(ls);        
//      tc.check("insert sort");
//      
//    ls = Arrays.copyOf(lsinit, lsinit.length);
//    bs = new BubbleSort();
//    bs.sort(ls);        
//    tc.check("bubble sort");
        
    }
}
