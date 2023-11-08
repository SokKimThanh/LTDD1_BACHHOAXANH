package tdc.edu.utils;

public class Search_Algorithm {

    /**
     * Hàm tìm kiếm tuyến tính O(n)
     *
     * @param arr mảng chưa sẵp xếp
     * @param key số cần tìm
     * @return
     */
    public static int UnorderLinearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Hàm tìm kiếm nhị phân (logn) tăng dần
     * @param arr mảng có sắp xếp
     * @param key số cần tìm
     * @return
     */
    public static int BinarySearch(int[] arr, int key) {
        int mid = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            // tim mid
            mid = (left + right) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                // key > mid, left dich qua phai
                left = mid + 1;
            } else {
                // right dich qua trai
                right = mid - 1;
            }
        }
        return -1;
    }

}
