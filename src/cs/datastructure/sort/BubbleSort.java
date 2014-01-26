package cs.datastructure.sort;

public class BubbleSort extends BaseSort
{

    @Override
    public void sortAlg(int[] ls)
    {
        for(int i = 0; i < ls.length; i++)
        {
            for(int j = i + 1; j < ls.length; j++)
            {
                if(ls[i] > ls[j])
                {
                    swap(ls, i, j);
                }
            }
        }
    }

}
