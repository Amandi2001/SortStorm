public class SortingAlgorithms {

        public static Double[] mergeSort(Double[] arr) {
        Double[] a = arr.clone();
        mergeSortRec(a, 0, a.length - 1);
        return a;
    }

    private static void mergeSortRec(Double[] a, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortRec(a, l, m);
            mergeSortRec(a, m + 1, r);
            merge(a, l, m, r);
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

}
