public class SortingAlgorithms {

    //Merge sort

        public static Double[] mergeSort(Double[] arr) {
        Double[] a = arr.clone();  // Clone the input array
        mergeSortRec(a, 0, a.length - 1); // Recursive merge sort
        return a;
    }

    private static void mergeSortRec(Double[] a, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2; // Find the middle index
            mergeSortRec(a, l, m);  // Sort left half
            mergeSortRec(a, m + 1, r); // Sort right half
            merge(a, l, m, r);  // Merge sorted halves
        }
    }

        private static void merge(Double[] a, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        Double[] L = new Double[n1];
        Double[] R = new Double[n2];

        System.arraycopy(a, l, L, 0, n1);
        System.arraycopy(a, m + 1, R, 0, n2);

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) a[k++] = L[i++];
            else a[k++] = R[j++];
        }

        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }

    
    //Quick sort

    public static Double[] quickSort(Double[] arr) {
        Double[] a = arr.clone(); // Clone the input array
        quick(a, 0, a.length - 1); // Recursive quick sort
        return a;
    }

    private static void quick(Double[] a, int low, int high) {
        if (low < high) {
            int pi = partition(a, low, high); // Partition the array
            quick(a, low, pi - 1); // Sort left part
            quick(a, pi + 1, high); // Sort right part
        }
    }

        private static int partition(Double[] a, int low, int high) {
        double pivot = a[high]; // Choose last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                i++;
                double t = a[i]; a[i] = a[j]; a[j] = t; // Swap
            }
        }

        double t = a[i + 1];
        a[i + 1] = a[high];
        a[high] = t;

        return i + 1; // Return pivot index
    }
}
