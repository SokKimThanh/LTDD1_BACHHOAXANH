package tdc.edu.giohang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdc.edu.danhsachsp.HangHoa;

public class GioHang {
    private HashMap<HangHoa, Integer> hangHoaMap;

    public GioHang() {
        hangHoaMap = new HashMap<>();
    }

    public void add(HangHoa hangHoa) {
        int quantity = hangHoaMap.containsKey(hangHoa) ? hangHoaMap.get(hangHoa) : 0;
        hangHoaMap.put(hangHoa, quantity + 1);
    }

    /**
     * Phương thức này nhận vào một đối tượng HangHoa và trả về số lượng của sản phẩm đó trong giỏ hàng
     * @param hangHoa
     * @return Phương thức này nhận vào một đối tượng HangHoa và trả về số lượng của sản phẩm đó trong giỏ hàng
     */
    public int getQuantity(HangHoa hangHoa) {
        return hangHoaMap.containsKey(hangHoa) ? hangHoaMap.get(hangHoa) : 0;
    }

    /**
     * Phương thức này không nhận tham số nào và trả về tổng số lượng tất cả các sản phẩm trong giỏ hàng.
     * @return Phương thức này không nhận tham số nào và trả về tổng số lượng tất cả các sản phẩm trong giỏ hàng.
     */
    public int getQuantity() {
        int totalQuantity = 0;
        for (int quantity : hangHoaMap.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }
    // Các phương thức khác...
    public List<HangHoa> getHangHoaList() {
        // Trả về một danh sách các sản phẩm trong giỏ hàng
        return new ArrayList<>(hangHoaMap.keySet());
    }

    public boolean contains(HangHoa hangHoa) {
        // Kiểm tra xem một sản phẩm có trong giỏ hàng hay không
        return hangHoaMap.containsKey(hangHoa);
    }

    public void increaseQuantity(HangHoa hangHoa) {
        // Tăng số lượng của một sản phẩm trong giỏ hàng
        int quantity = getQuantity(hangHoa);
        hangHoaMap.put(hangHoa, quantity + 1);
    }

    public double getTongThanhTien() {
        double tongThanhTien = 0;
        for (Map.Entry<HangHoa, Integer> entry : hangHoaMap.entrySet()) {
            HangHoa hangHoa = entry.getKey();
            int quantity = entry.getValue();
            tongThanhTien += hangHoa.getGiaSp() * quantity;
        }
        return tongThanhTien;
    }
}

