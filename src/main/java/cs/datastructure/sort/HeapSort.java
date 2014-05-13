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
    // // û���ӽڵ�Ĳ���Ҫ�������ѣ������һ���ĸ��ڵ㿪ʼ
    // int startIndex = getParentIndex(data.length - 1);
    // // ��β�˿�ʼ�������ѣ�ÿ�ζ�����ȷ�Ķ�
    // for(int i = startIndex; i >= 0; i--)
    // {
    // maxHeapify(data, data.length, i);
    // }
    // }
    //
    // /**
    // * ��������
    // *
    // * @param data
    // * @param heapSize
    // * ��Ҫ�������ѵĴ�С��һ����sort��ʱ���õ�����Ϊ���ֵ����ĩβ��ĩβ�Ͳ��ٹ���������
    // * @param index
    // * ��ǰ��Ҫ�������ѵ�λ��
    // */
    // private static void maxHeapify(int[] data, int heapSize, int index)
    // {
    // // ��ǰ���������ӽڵ�Ƚ�
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
    // // �õ����ֵ�������Ҫ��������������ˣ����ӽڵ���ܾͲ��������ˣ���Ҫ���µ���
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
    // * �������ֵ����ĩβ��data��Ȼ�����ѣ��������ͳ��˵�����
    // *
    // * @param data
    // */
    // private static void heapSort(int[] data)
    // {
    // // ĩβ��ͷ�������������������
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
    // * ���ڵ�λ��
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
    // * ���ӽڵ�position ע�����ţ��ӷ����ȼ�����
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
    // * ���ӽڵ�position
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
    // * ��2Ϊ�׵Ķ���
    // *
    // * @param param
    // * @return
    // */
    // private static double getLog(double param)
    // {
    // return Math.log(param) / Math.log(2);
    // }

}
