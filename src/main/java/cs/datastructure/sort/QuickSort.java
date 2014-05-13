package cs.datastructure.sort;

import java.util.Random;

public class QuickSort extends BaseSort
{

    @Override
    protected void sortAlg(int[] ls)
    {
        // sort(0, ls.length, ls);
        sort_1(0, ls.length - 1, ls);
//        sort_wiki(ls);
    }

    private void sort_1(int start, int end, int[] ls)
    {
        int i = start;
        int j = end;
        int index = start + r.nextInt(end - start + 1);
        int key = ls[index];
        swap(ls, index, i); 
        while(i < j)
        {
            while(ls[j] >= key && i < j)
            {
                j--;
            }
            if(i < j)
            {
                swap(ls, i, j);
            }
            while(ls[i] <= key && i < j)
            {
                i++;
            }
            if(i < j)
            {
                swap(ls, i, j);
            }
        }
        if(start < i-1)
        {
            sort_1(start, i-1, ls);
        }
        if(i+1 < end)
        {
            sort_1(i+1, end, ls);
        }
    }
    
    
    
    
    Random r = new Random();
    int partition(int[] array, int begin, int end) {
        int index = begin + r.nextInt(end - begin + 1);
        int pivot = array[index];
        swap(array, index, end);        
        for (int i = index = begin; i < end; ++ i) {
            if (array[i]<pivot) {
                swap(array, index++, i);
            }
        }
        swap(array, index, end);        
        return (index);
    }
 
    void qsort(int[] array, int begin, int end) {
        if (end > begin) {
            int index = partition(array, begin, end);
            qsort(array, begin, index - 1);
            qsort(array, index + 1,  end);
        }
    }
 
    public void sort_wiki(int[] array) {
        qsort(array, 0, array.length - 1);
    }

    private void sort(int start, int end, int[] ls)
    {
        int i = start;
        int j = end;
        int key = ls[i];
        while(i < j)
        {
            j--;
            if(ls[j] < key)
            {
                swap(ls, i, j);
                while(i < j)
                {
                    i++;
                    if(ls[i] > key)
                    {
                        swap(ls, i, j);
                        break;
                    }
                }
            }
        }
        if(start < j)
        {
            sort(start, j, ls);
        }
        i++;
        if(i < end)
        {
            sort(i, end, ls);
        }
    }

}
