package cs.datastructure.sort;

import java.util.Arrays;

public abstract class BaseSort
{

    public BaseSort()
    {
        super();
    }

    public void sort(int[] ls)
    {
        System.out.println("=="+this.getClass().getName());
        System.out.println("before:"+toStr(ls, 10)+"...");
        sortAlg(ls);
        System.out.println("after:"+toStr(ls, 10)+"...");
    }

    protected abstract void sortAlg(int[] ls);

    public String toStr(int[] ls, int len)
    {
        StringBuffer sb = new StringBuffer(100);
        for(int i = 0; i < (len<ls.length?len:ls.length); i++)
        {
            sb.append(ls[i]).append(" ");
        }
        return sb.toString();
    }

    protected void swap(int[] ls, int i, int j)
    {
        if(i!=j)
        {
            int tmp;
            tmp = ls[i];
            ls[i] = ls[j];
            ls[j] = tmp;
        }
    }
    
    public static void main(String[] args)
    {
        TimeConsume tc = new TimeConsume();
        tc.start();
        int[] lsinit = new int[]{2,16,15,16,3};
        int[] ls = null;
        BaseSort bs;
        tc.check("init");

//        ls = Arrays.copyOf(lsinit, lsinit.length);
//        bs = new QuickSort();
//        bs.sort(ls);        
//        tc.check("quick sort");
        
//        ls = Arrays.copyOf(lsinit, lsinit.length);
//        bs = new HeapSort();
//        bs.sort(ls);        
//        tc.check("heap sort");        
        
        ls = Arrays.copyOf(lsinit, lsinit.length);
        bs = new MergeSort();
        bs.sort(ls);        
        tc.check("merge sort");
        
      
//      ls = Arrays.copyOf(lsinit, lsinit.length);
//      bs = new SelectionSort();
//      bs.sort(ls);        
//      tc.check("selection sort");
    }

}