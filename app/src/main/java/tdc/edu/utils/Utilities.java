package tdc.edu.utils;

import java.util.Random;
import java.util.Scanner;

public class Utilities {
    /**
     * Hàm in mảng menu arr
     * @param arr mảng menu arr các chức năng
     */
    public static void XuatArrMenu(String[] arr) {
        //WriteLine($"{"STT",-10}{"Chuc nang",-15}");
        System.out.printf("%10s%15s", "STT", "Chuc nang");
        for (int i = 0; i < arr.length; i++) {
            //WriteLine($"{i + 1,-10}{arr[i],-15}");
            System.out.println(Integer.toString(i + 1) + "\t" + arr[i]);
        }
        System.out.println();
    }

    /**
     * Hàm in mảng số nguyên
     *
     * @param arr
     */
    public static void XuatIntArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //Write($"{arr[i]} ");
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Hàm in mảng chuỗi bất kỳ
     *
     * @param arr
     * @return
     */
    public static String XuatStringArr(int[] arr) {
        String sum = "";
        for (int i = 0; i < arr.length; i++) {
            sum += String.format("%s ", arr[i]);
        }
        return sum.trim();
    }

    /**
     * Hàm nhập số nguyên
     *
     * @return Trả về 1 số nguyên ( có bắt lỗi)
     */
    public static int NhapSoNguyen() {
        int n;
        boolean isNumber = false;
        Scanner a = new Scanner(System.in);
        do {
            try {
                isNumber = true; // lan nay se nhap dung
                System.out.print("Nhap n");
                n = a.nextInt();
            } catch (NumberFormatException ex) {
                System.out.println("Nhap n: ");
                n = a.nextInt();
                isNumber = false;// lan nay da nhap sai
            }
        } while (isNumber == false || n < 0);
        return n;
    }

    public static String NhapChuoi() {
        String n;
        boolean isString = false;
        Scanner a = new Scanner(System.in);
        do {
            try {
                isString = true; // lan nay se nhap dung
                System.out.print("Nhap string");
                n = a.next();
            } catch (Exception ex) {
                System.out.println("Nhap string: ");
                n = a.next();
                isString = false;// lan nay da nhap sai
            }
        } while (isString == false || n.isEmpty() || n == null);// always true
        return n;
    }


    /// <summary>
    /// Hàm nhập mảng số nguyên random
    /// </summary>
    /// <param name="n"></param>
    /// <param name="d"></param>
    /// <returns></returns>
    public static int[] NhapRandomMangSoNguyen(int n) {
        Random d = new Random();
        int[] arr = new int[n];
        // nhap ran dom thong tin
        for (int i = 0; i < arr.length; i++) {
            arr[i] = d.nextInt();
        }
        return arr;
    }

    /**
     * Hàm tạo chuỗi ký tự ngẫu nhiên
     *
     * @param length Độ dài của chuỗi ký tự
     * @return Hàm trả về một chuỗi ký tự ngẫu nhiên
     */
    public static String NhapChuoiRandom(int length) {
        char[] alphabet = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
        Random d = new Random();
        int index = d.nextInt(alphabet.length);
        String sum = null;
        for (int i = 0; i < length; i++) {
            String sum1 = String.valueOf(alphabet[index]).toUpperCase();
            String sum2 = String.valueOf(alphabet[index]).toLowerCase();
            if (i % 2 == 0) {
                sum += sum1;
            } else {
                sum += sum2;
            }
        }
        return sum;
    }

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
}
