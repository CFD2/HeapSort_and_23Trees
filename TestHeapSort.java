/**
 * @author Vadim Yastrebov (CFD2) 100908473
 * @version 2017.08.14
 */
public class TestHeapSort<A> {
    public static void main(String[] args) {
        TestHeapSort<Integer> sort = new TestHeapSort<>();
        int[] arr = {990, 9 , 6, 3, 2, 5, 24, 35, 500, 64};
//        int[] arr = {15, 19, 10, 7, 17, 22};  //better test case
        System.out.println("\nInitial array: ");
        sort.printArr(arr);
        sort.heapSort(arr, arr.length);
        System.out.println("\nArray after heapSort: ");
        sort.printArr(arr);
    }

    private void printArr (int[] arr) {
        for (int a: arr) {
            System.out.print(a + ", ");
        }
    }

    public void heapSort(int[] arr, int n) {
        for (int i = n - 1; i >= 0; i--) {
            heapRebuild(arr, i, n);
        }
        System.out.println("\nAfter heap rebuild: ");
        printArr(arr);
        int last = n - 1;

        for (int i = 0; i < n; i++) {
            int temp = arr[0];
            arr[0] = arr[last];
            arr[last] = temp;
            heapRebuild(arr, 0, --last);
        }
    }

    private void heapRebuild(int[] arr, int root, int last) {
        int lChild = root + 1;
        int rChild = root + 2;
        int temp = arr[root];
        if (lChild < last && arr[lChild] > arr[root]) {
                arr[root] = arr[lChild];
                arr[lChild] = temp;
                heapRebuild(arr, lChild, last);
        }
        if (rChild < last && arr[rChild] > arr[root]) {
            arr[root] = arr[rChild];
            arr[rChild] = temp;
            heapRebuild(arr, rChild, last);
        }
    }
}
