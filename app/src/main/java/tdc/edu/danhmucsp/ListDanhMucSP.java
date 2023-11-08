package tdc.edu.danhmucsp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListDanhMucSP {
      List<DanhMucSP> danhMucSPList = new ArrayList<>();

    public void Them(DanhMucSP item) {
        danhMucSPList.add(item);
    }

    public void Xoa(String ma) {
        for (DanhMucSP item : danhMucSPList
        ) {
            if (item.getMa().toLowerCase().compareTo(ma.toLowerCase()) == 0) {
                danhMucSPList.remove(item);
            }
        }
    }

    public void Sua(DanhMucSP i) {
        for (DanhMucSP item : danhMucSPList) {
            if (item.getMa().toLowerCase().compareTo(i.getMa().toLowerCase()) == 0) {
                item.setTen(i.getTen());
                item.setGhichu(i.getGhichu());
            }
        }
    }

    public List<DanhMucSP> TimKiemDanhMucTheoTen(String s) {
        List<DanhMucSP> danhmucSearchList = new ArrayList<>();
        for (DanhMucSP item : danhMucSPList) {
            if (item.getTen().contains(s)) {
                danhmucSearchList.add(item);
            }
        }
        return danhmucSearchList;
    }


    public void SapXepDanhMucTheoIDTangDan() {
        danhMucSPList.sort(Comparator.comparing(DanhMucSP::getMa));
    }

    public void SapXepDanhMucTheoIDGiamDan() {
        danhMucSPList.sort((DanhMucSP d1, DanhMucSP d2) -> d2.getMa().compareTo(d1.getMa()));
    }
}
