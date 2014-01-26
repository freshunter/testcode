package cs.datastructure.sort;

public class SelectionSort extends BaseSort
{

    @Override
    protected void sortAlg(int[] ls)
    {
        int minIndex;
        for(int i = 0; i < ls.length; i++)
        {
            minIndex = i;
            for(int j = i+1; j < ls.length; j++)
            {
                if(ls[minIndex]>ls[j])
                {
                    minIndex = j;
                }
            }
            swap(ls, minIndex, i);
        }
    }

}
