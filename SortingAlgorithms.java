
public class SortingAlgorithms {

    public static Double[] insertionSort(Double[] arr) {
        Double[] a = arr.clone();

        for (int i = 1; i < a.length; i++) {
            double key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
        return a;
    }

    public static Double[] shellSort(Double[] arr) {
        Double[] a = arr.clone();
        int n = a.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                double temp = a[i];
                int j;
                for (j = i; j >= gap && a[j - gap] > temp; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }

        return a;
    }

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
            if (L[i] <= R[j]) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
            }
        }

        while (i < n1) {
            a[k++] = L[i++];
        }
        while (j < n2) {
            a[k++] = R[j++];
        }
    }

    public static Double[] quickSort(Double[] arr) {
        Double[] a = arr.clone();
        quick(a, 0, a.length - 1);
        return a;
    }

    private static void quick(Double[] a, int low, int high) {
        if (low < high) {
            int pi = partition(a, low, high);
            quick(a, low, pi - 1);
            quick(a, pi + 1, high);
        }
    }

    private static int partition(Double[] a, int low, int high) {
        double pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                i++;
                double t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }

        double t = a[i + 1];
        a[i + 1] = a[high];
        a[high] = t;

        return i + 1;
    }

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

}
