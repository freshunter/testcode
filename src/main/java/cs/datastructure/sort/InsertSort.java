package cs.datastructure.sort;

public class InsertSort extends BaseSort
{
    int index;
    int[] res;
    
    @Override
    protected void sortAlg(int[] ls)
    {
        int val;
        for(int i = 1; i < ls.length; i++)
        {
            val = ls[i];
            int k = i;
            while(k > 0 && ls[k-1] > val)
            {
                ls[k] = ls[k-1];
                k--;
            }
            ls[k] = val;
        }
    }
    
    protected void sortAlgkkk(int[] ls)
    {
        res = new int[ls.length];
        index = 0;

        for(int i = 0; i < ls.length; i++)
        {
            insert(ls[i], res);
        }
        
        System.out.println("insert res:"+ toStr(res,10));
    }

    private void insert(int v, int[] res)
    {
            int before = index;
            for(int i = 0; i < index; i++)
            {
                if(v < res[i])
                {
                    insert(v, i, res);
                    break;
                }
            }
            if(before == index)
            {
                res[index++] = v;
            }
            
    }

    private void insert(int v, int i, int[] res)
    {
        for(int j = index; j > i; j--)
        {
            res[j] = res[j - 1];
        }
        res[i] = v;
        index++;
    }

}
