package tdc.edu.danhsachdm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DanhMucList {


    // members
    private List<DanhMuc> danhMucList;


    public DanhMucList() {
        this.danhMucList = new ArrayList<>();
    }


    // properties
    public List<DanhMuc> getDanhMucList() {
        return danhMucList;
    }

    public void setDanhMucList(List<DanhMuc> danhMucList) {
        this.danhMucList = danhMucList;
    }


    public boolean Them(DanhMuc item) {

        return danhMucList.add(item);
    }

    public boolean Xoa(DanhMuc dm) {
        for (DanhMuc item : danhMucList) {
            if (item.getMa().toLowerCase().compareTo(dm.getMa().toLowerCase()) == 0) {
                danhMucList.remove(item);
                return true;
            }
        }
        return false;
    }

    public boolean Sua(DanhMuc i) {
        for (DanhMuc item : danhMucList) {
            if (item.getMa().toLowerCase().compareTo(i.getMa().toLowerCase()) == 0) {
                item.setTen(i.getTen());
                item.setGhichu(i.getGhichu());
                return true;
            }
        }
        return false;
    }

    public List<DanhMuc> TimKiemDanhMucTheoTen(String s) {
        List<DanhMuc> danhmucSearchList = new ArrayList<>();
        for (DanhMuc item : danhMucList) {
            if (item.getTen().contains(s)) {
                danhmucSearchList.add(item);
            }
        }
        return danhmucSearchList;
    }


    public void SapXepDanhMucTheoIDTangDan() {
        danhMucList.sort(Comparator.comparing(DanhMuc::getMa));
    }

    public void SapXepDanhMucTheoIDGiamDan() {
        danhMucList.sort((DanhMuc d1, DanhMuc d2) -> d2.getMa().compareTo(d1.getMa()));
    }
}
