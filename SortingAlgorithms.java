
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
}
