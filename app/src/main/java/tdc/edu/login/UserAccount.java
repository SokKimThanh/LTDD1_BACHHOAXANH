package tdc.edu.login;

import java.util.Date;

public class UserAccount {


    private int mataikhoan;
    private String tentaikhoan;
    private String matkhau;
    private Date ngayhethantruycap;
    private int capdotaikhoan;
    private String email;
    private boolean isEmailVerified;

    public UserAccount() {
    }


    public UserAccount(int mataikhoan, String tentaikhoan, String matkhau, Date ngayhethantruycap, int capdotaikhoan, String email) {
        this.mataikhoan = mataikhoan;
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.ngayhethantruycap = ngayhethantruycap;
        this.capdotaikhoan = capdotaikhoan;
        this.email = email;
    }
    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public Date getNgayhethantruycap() {
        return ngayhethantruycap;
    }

    public void setNgayhethantruycap(Date ngayhethantruycap) {
        this.ngayhethantruycap = ngayhethantruycap;
    }

    public int getCapdotaikhoan() {
        return capdotaikhoan;
    }

    public void setCapdotaikhoan(int capdotaikhoan) {
        this.capdotaikhoan = capdotaikhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
