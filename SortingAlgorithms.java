public class SortingAlgorithms {

  public static Double[] heapSort(Double[] arr) {
        Double[] a = arr.clone();
        int n = a.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);

        for (int i = n - 1; i >= 0; i--) {
            double temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            heapify(a, i, 0);
        }

        return a;
    }

    private static void heapify(Double[] a, int n, int i) {
        int largest = i;
        int l = 2 * i + 1, r = 2 * i + 2;

        if (l < n && a[l] > a[largest]) largest = l;
        if (r < n && a[r] > a[largest]) largest = r;

        if (largest != i) {
            double swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;

            heapify(a, n, largest);
        }
    }

    private static void heapify(Double[] a, int n, int i) {
    int largest = i;       // Initialize largest as root
    int l = 2 * i + 1;     // left child
    int r = 2 * i + 2;     // right child

    // If left child is larger than root
    if (l < n && a[l] > a[largest]) largest = l;

    // If right child is larger than largest so far
    if (r < n && a[r] > a[largest]) largest = r;

    // If largest is not root
    if (largest != i) {
        double swap = a[i];
        a[i] = a[largest];
        a[largest] = swap;

        // Recursively heapify the affected sub-tree
        heapify(a, n, largest);
    }
}
  

}
