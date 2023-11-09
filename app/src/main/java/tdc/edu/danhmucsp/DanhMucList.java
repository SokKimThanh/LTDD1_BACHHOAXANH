package tdc.edu.danhmucsp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DanhMucList {
      List<DanhMucSP> danhMucList = new ArrayList<>();

    public void Them(DanhMucSP item) {
        danhMucList.add(item);
    }

    public void Xoa(String ma) {
        for (DanhMucSP item : danhMucList
        ) {
            if (item.getMa().toLowerCase().compareTo(ma.toLowerCase()) == 0) {
                danhMucList.remove(item);
            }
        }
    }

    public void Sua(DanhMucSP i) {
        for (DanhMucSP item : danhMucList) {
            if (item.getMa().toLowerCase().compareTo(i.getMa().toLowerCase()) == 0) {
                item.setTen(i.getTen());
                item.setGhichu(i.getGhichu());
            }
        }
    }

    public List<DanhMucSP> TimKiemDanhMucTheoTen(String s) {
        List<DanhMucSP> danhmucSearchList = new ArrayList<>();
        for (DanhMucSP item : danhMucList) {
            if (item.getTen().contains(s)) {
                danhmucSearchList.add(item);
            }
        }
        return danhmucSearchList;
    }


    public void SapXepDanhMucTheoIDTangDan() {
        danhMucList.sort(Comparator.comparing(DanhMucSP::getMa));
    }

    public void SapXepDanhMucTheoIDGiamDan() {
        danhMucList.sort((DanhMucSP d1, DanhMucSP d2) -> d2.getMa().compareTo(d1.getMa()));
    }
}
