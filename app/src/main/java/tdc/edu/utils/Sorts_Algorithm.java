package tdc.edu.utils;

public class Sorts_Algorithm {

    // hàm sắp xếp
    //Chuong 3: Mot so thuat toan sap xep
    // bubble sort
    // test 712643
    /// <summary>
    /// Ham swap theo kieu du lieu int
    /// </summary>
    /// <param name="a"></param>
    /// <param name="b"></param>

    /**
     * Hàm đảo giá trị 2 vị trí
     *
     * @param a
     * @param b
     */
    public static void Swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    /// <summary>
    /// Bubble sort for j tu dau mang
    /// </summary>
    /// <param name="arr">mang chua sap  xep</param>
    public static void BubbleSort(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Swap(arr[j], arr[j + 1]);
                }
                count++;
            }
        }
//        Console.WriteLine("Power: " + count);
        System.out.println("Power: " + count);
    }

    /// <summary>
    /// Improved Giam so lan lap bubble sort
    /// </summary>
    /// <param name="arr">mang chua sap  xep</param>
    public static void BubbleSortImproved(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            // cai tien giam so lan lap (giam cong viec)
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Swap(arr[j], arr[j + 1]);
                }
                count++;
            }
        }
        System.out.println("Power: " + count);
    }

    /// <summary>
    /// Advance Giam so lan lap bubble sort
    /// </summary>
    /// <param name="arr">mang chua sap  xep</param>
    public static void BubbleSortAdmin(int[] arr) {
        int count = 0;//power
        boolean flag; // check mang chua sap xep
        for (int i = 0; i < arr.length - 1; i++) {
            flag = true;// check mang da duoc sap xep
            // cai tien giam so lan lap (giam cong viec)
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Swap(arr[j], arr[j + 1]);
                    flag = false;// mang nay van chua sap xep xong
                }
                count++;
            }
            // khi mang da duoc sap xep xong
            if (flag) {
                break;
            }
        }
        System.out.println("Power: " + count);
    }

    /// <summary>
    /// ham tim vi tri nho nhat trong mang
    /// </summary>
    /// <param name="arr">mang chua sap xep</param>
    /// <param name="start">vi tri bat dau</param>
    /// <returns></returns>
    public static int FindPosMin(int[] arr, int start) {
        if (start < 0 || start > arr.length) {
            return -1;
        }
        // khai bao
        int min = arr[start];// gia tri nho nhat
        int pos = start;// vi tri nho nhat

        for (int i = start + 1; i < arr.length - 1; i++) {
            // xet gia tri nho nhat den cuoi mang
            if (arr[i] < min) {
                //Tim thay gia tri nho nhat la i !!!
                pos = i;// luu vi tri phan tu nho nhat
                min = arr[i];
            }
        }
        return pos;
    }

    /// <summary>
    /// Lop co Thu: Ham sap xep selection sort (thuat toan sap xep tai cho)
    /// </summary>
    /// <param name="arr">danh sach chua sap xep</param>
    public static void SelectionSort2(int[] arr) {
        int count = 0;//power
        for (int i = 0; i < arr.length; i++) {
            int viTriNhoNhat = FindPosMin(arr, i);
            if (i != viTriNhoNhat) {
                Swap(arr[viTriNhoNhat], arr[i]);
                // check truong hop bi trung
                count++;
            }

        }
        System.out.println("Power: " + count);
    }

    /// <summary>
    /// Lop co Thu: Ham sap xep interchange sort
    /// </summary>
    /// <param name="arr">danh sach chua sap xep</param>
    public static void InterchangeSort(int[] arr) {
        int count = 0;//power
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    Swap(arr[i], arr[j]);
                }
                count++;
            }
        }
        System.out.println("Power: " + count);
    }

    /// <summary>
    /// Lop co Thu: Ham sap xep interchange sort cai tien
    /// </summary>
    /// <param name="arr">mang da co thu tu sap xep</param>
    public static void InterchangeSortImproved(int[] arr) {
        int count = 0;//power
        boolean check = false; // kiem tra ham da duoc sap xep
        for (int i = 0; i < arr.length - 1; i++) {
            check = false;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    Swap(arr[i], arr[j]);
                    check = true;
                }
                count++;
            }
            if (check == false)
                break;
        }
        System.out.println("Power: " + count);
    }
}
