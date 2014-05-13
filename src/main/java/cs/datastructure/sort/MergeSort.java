package cs.datastructure.sort;

public class MergeSort extends BaseSort
{

    @Override
    protected void sortAlg(int[] ls)
    {
    	int[] tmp = new int[ls.length];
        for(int len = 1; len < ls.length;)
        {
            int unit = len << 1;
            for(int i = 0; i < ls.length;)
            {
                int end = (i + unit >= ls.length) ? ls.length : i + unit;
                if(i + len >= ls.length)
                {
                    break;
                }
                merge(ls, i, i + len - 1, end - 1,tmp);
                if(end == ls.length)
                {
                    break;
                }
                i = i + unit;
            }
            len = unit;
        }

    }

    private void merge(int[] ls, int l, int m, int h, int[] tmp)
    {
        int lp = l;
        int hp = m + 1;
        int tmpi = 0;
        assert tmp.length >= (h-l+1) : "tmp array length more";
    	while(ls[lp]<=ls[hp] && lp<=m)
    	{
    		lp++;
    	}
        int tmpstart = lp;
        while(tmpi < tmp.length && !(lp > m || hp > h))
        {
            if(ls[lp] > ls[hp])
            {
                tmp[tmpi] = ls[hp];
                hp++;
            }
            else
            {
                tmp[tmpi] = ls[lp];
                lp++;
            }
            tmpi++;
        }
        if(hp > h)
        {
            for(int i = lp; i <= m; i++)
            {
                tmp[tmpi] = ls[i];
                tmpi++;
            }
        }
        for(int i = 0; i < tmpi; i++)
        {
            ls[tmpstart + i] = tmp[i];
        }
//        System.out.println(tmpi + " " + (h-l+1));
    }

    private void merge(int[] ls, int l, int m, int h)
    {
        int lp = l;
        int hp = m + 1;
        int[] tmp = new int[h - l + 1];
        int tmpi = 0;
        while(tmpi < tmp.length && !(lp > m || hp > h))
        {
            if(ls[lp] > ls[hp])
            {
                tmp[tmpi] = ls[hp];
                hp++;
            }
            else
            {
                tmp[tmpi] = ls[lp];
                lp++;
            }
            tmpi++;
        }
        if(hp > h)
        {
            for(int i = lp; i <= m; i++)
            {
                ls[h - (m - i)] = ls[i];
            }
        }
        for(int i = 0; i < tmpi; i++)
        {
            ls[l + i] = tmp[i];
        }
    }

}
