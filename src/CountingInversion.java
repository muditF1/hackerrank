public class CountingInversion {

  static long inverts = 0;

  private static long merge(int arr[], int l, int m, int r) {
    int lhsArraySize = m - l + 1;
    int rhsArraySize = r - m;

    int lhs[] = new int[lhsArraySize];
    int rhs[] = new int[rhsArraySize];

    for (int i = 0; i < lhsArraySize; i++) lhs[i] = arr[l + i];
    for (int j = 0; j < rhsArraySize; ++j) rhs[j] = arr[m + 1 + j];

    int i = 0, j = 0;
    int k = l;
    while (i < lhsArraySize && j < rhsArraySize) {
      if (lhs[i] <= rhs[j]) {
        arr[k] = lhs[i];
        i++;
      } else {
        arr[k] = rhs[j];
        inverts++;
        j++;
      }
      k++;
    }

    while (i < lhsArraySize) {
      arr[k] = lhs[i];
      i++;
      k++;
    }

    while (j < rhsArraySize) {
      arr[k] = rhs[j];
      j++;
      k++;
    }
    return inverts;
  }

  private static void mergeSort(int[] arr, int l, int h) {
    int mid = (l + h) / 2;
    if (l < h) {
      mergeSort(arr, l, mid);
      mergeSort(arr, mid + 1, h);
      inverts = merge(arr, l, mid, h);
    }
  }

  static long countInversions(int[] arr) {
    int arrLength = arr.length - 1;
    mergeSort(arr, 0, arrLength);
    return inverts;
  }

  public static void main(String[] args) {
//    int[] A = {1, 1, 1, 2, 2};
    int[] A = {2, 1, 3, 1, 2};
    long inversions = countInversions(A);
    System.out.println(inversions);
  }
}
