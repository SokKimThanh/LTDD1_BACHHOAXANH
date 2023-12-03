package tdc.edu.giohang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdc.edu.danhsachsp.HangHoa;

public class GioHang {

    private HashMap<HangHoa, Integer> giohang;

    public GioHang() {
        giohang = new HashMap<>();
    }

    /**
     * Thêm 1 sản phẩm vào danh sách sản phẩm trong hóa đơn
     * @param hangHoa
     */
    public void add(HangHoa hangHoa) {
        int quantity = giohang.containsKey(hangHoa) ? giohang.get(hangHoa) : 0;
        giohang.put(hangHoa, quantity + 1);
    }

    /**
     * Phương thức này nhận vào một đối tượng HangHoa và trả về số lượng của sản phẩm đó trong giỏ hàng
     * @param hangHoa
     * @return Phương thức này nhận vào một đối tượng HangHoa và trả về số lượng của sản phẩm đó trong giỏ hàng
     */
    public int getQuantity(HangHoa hangHoa) {
        return giohang.containsKey(hangHoa) ? giohang.get(hangHoa) : 0;
    }

    /**
     * Phương thức này không nhận tham số nào và trả về tổng số lượng tất cả các sản phẩm trong giỏ hàng.
     * @return Phương thức này không nhận tham số nào và trả về tổng số lượng tất cả các sản phẩm trong giỏ hàng.
     */
    public int getQuantity() {
        int totalQuantity = 0;
        for (int quantity : giohang.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }

    /**
     * Đem danh sách sản phẩm trong hóa đơn (giỏ hàng) đi in cho người ta xem
     * @return giỏ hàng sản phẩm(thanh toán hóa đơn)
     */
    public List<HangHoa> getHangHoaList() {
        // Trả về một danh sách các sản phẩm trong giỏ hàng
        return new ArrayList<>(giohang.keySet());
    }

    /**
     * Kiểm tra sản phẩm trong giỏ hàng còn không
     * @param hangHoa
     * @return
     */
    public boolean contains(HangHoa hangHoa) {
        // Kiểm tra xem một sản phẩm có trong giỏ hàng hay không
        return giohang.containsKey(hangHoa);
    }

    /**
     * Tăng số lượng sản phẩm trong giỏ hàng
     * @param hangHoa
     */
    public void increaseQuantity(HangHoa hangHoa) {
        // Tăng số lượng của một sản phẩm trong giỏ hàng
        int quantity = getQuantity(hangHoa);
        giohang.put(hangHoa, quantity + 1);
    }

    /**
     * Giảm số lượng sản phẩm trong giỏ hàng
     * @param hangHoa
     */
    public void decreaseQuantity(HangHoa hangHoa) {
        // Tăng số lượng của một sản phẩm trong giỏ hàng
        int quantity = getQuantity(hangHoa);
        giohang.put(hangHoa, quantity - 1);
    }
    /**
     * Tính tiền thanh toán giỏ hàng
     * @return Tổng tiền giỏ hàng
     */
    public double getTongThanhTien() {
        double tongThanhTien = 0;
        for (Map.Entry<HangHoa, Integer> entry : giohang.entrySet()) {
            HangHoa hangHoa = entry.getKey();
            int quantity = entry.getValue();
            tongThanhTien += hangHoa.getGiaSp() * quantity;
        }
        return tongThanhTien;
    }
}

