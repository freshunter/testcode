package cs.datastructure.sort;

public class HeapSort extends BaseSort
{

    @Override
    protected void sortAlg(int[] ls)
    {
        buildHeap(ls, ls.length - 1);
        hsort(ls, ls.length - 1);
        // buildMaxHeapify(ls);
        // heapSort(ls);

    }

    private void hsort(int[] ls, int end)
    {
        swap(ls, 0, end);
        for(int i = end-1; i > 0; i--)
        {
            maxHeapifyk(ls, 0, i);
            swap(ls, 0, i);
        }
    }

    private void buildHeap(int[] ls, int end)
    {
        for(int i = ((end - 1) >> 1); i >= 0; i--)
        {
            maxHeapifyk(ls, i, end);
        }
    }

    /**
     * Ki>=K2i Ki>=K2i+1
     * 
     * @param ls
     * @param i
     */
    private void maxHeapifyk(int a[], int i, int end)
    {

        int maxIndex = i;
        int finish = (end - 1) >> 1;
        for(int j = i; j <= finish;)
        {
            int left = (j << 1) + 1;
            int right = left + 1;
            if(left <= end && a[left] > a[j])
                maxIndex = left;
            if(right <= end && a[right] > a[maxIndex])
                maxIndex = right;
            if(maxIndex != j)
            {
                swap(a, maxIndex, j);
                j = maxIndex;
            }
            else
            {
                break;
            }
        }
    }
    
    private void hsortdigui(int[] ls, int end)
    {
        if(end > 0)
        {
            swap(ls, 0, end);
            maxHeapifyk(ls, 0, end - 1);
            hsort(ls, end - 1);
        }
    }

    private void maxHeapifykdigui(int a[], int i, int end)
    {

        int maxIndex = i;

        int left = (i << 1) + 1;
        int right = left + 1;
        if(left <= end && a[left] > a[i])
            maxIndex = left;
        if(right <= end && a[right] > a[maxIndex])
            maxIndex = right;
        if(maxIndex != i)
        {
            swap(a, maxIndex, i);
            maxHeapifyk(a, maxIndex, end);
        }
    }

    // private static void buildMaxHeapify(int[] data)
    // {
    // // 没有子节点的才需要创建最大堆，从最后一个的父节点开始
    // int startIndex = getParentIndex(data.length - 1);
    // // 从尾端开始创建最大堆，每次都是正确的堆
    // for(int i = startIndex; i >= 0; i--)
    // {
    // maxHeapify(data, data.length, i);
    // }
    // }
    //
    // /**
    // * 创建最大堆
    // *
    // * @param data
    // * @param heapSize
    // * 需要创建最大堆的大小，一般在sort的时候用到，因为最多值放在末尾，末尾就不再归入最大堆了
    // * @param index
    // * 当前需要创建最大堆的位置
    // */
    // private static void maxHeapify(int[] data, int heapSize, int index)
    // {
    // // 当前点与左右子节点比较
    // int left = getChildLeftIndex(index);
    // int right = getChildRightIndex(index);
    //
    // int largest = index;
    // if(left < heapSize && data[index] < data[left])
    // {
    // largest = left;
    // }
    // if(right < heapSize && data[largest] < data[right])
    // {
    // largest = right;
    // }
    // // 得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
    // if(largest != index)
    // {
    // int temp = data[index];
    // data[index] = data[largest];
    // data[largest] = temp;
    // maxHeapify(data, heapSize, largest);
    // }
    // }
    //
    // /**
    // * 排序，最大值放在末尾，data虽然是最大堆，在排序后就成了递增的
    // *
    // * @param data
    // */
    // private static void heapSort(int[] data)
    // {
    // // 末尾与头交换，交换后调整最大堆
    // for(int i = data.length - 1; i > 0; i--)
    // {
    // int temp = data[0];
    // data[0] = data[i];
    // data[i] = temp;
    // maxHeapify(data, i, 0);
    // }
    // }
    //
    // /**
    // * 父节点位置
    // *
    // * @param current
    // * @return
    // */
    // private static int getParentIndex(int current)
    // {
    // return (current - 1) >> 1;
    // }
    //
    // /**
    // * 左子节点position 注意括号，加法优先级更高
    // *
    // * @param current
    // * @return
    // */
    // private static int getChildLeftIndex(int current)
    // {
    // return (current << 1) + 1;
    // }
    //
    // /**
    // * 右子节点position
    // *
    // * @param current
    // * @return
    // */
    // private static int getChildRightIndex(int current)
    // {
    // return (current << 1) + 2;
    // }
    //
    // private static void print(int[] data)
    // {
    // int pre = -2;
    // for(int i = 0; i < data.length; i++)
    // {
    // if(pre < (int) getLog(i + 1))
    // {
    // pre = (int) getLog(i + 1);
    // System.out.println();
    // }
    // System.out.print(data[i] + " |");
    // }
    // }
    //
    // /**
    // * 以2为底的对数
    // *
    // * @param param
    // * @return
    // */
    // private static double getLog(double param)
    // {
    // return Math.log(param) / Math.log(2);
    // }

}
