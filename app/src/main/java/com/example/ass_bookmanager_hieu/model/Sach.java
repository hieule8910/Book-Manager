package com.example.ass_bookmanager_hieu.model;

public class Sach {
    private String baoveASM;
    private String maTheLoai;
    private String tenSach;
    private String tacGia;
    private String NXB;
    private double giaBia;
    private int soLuong;


    public Sach() {
    }

    public Sach(String baoveASM, String maTheLoai, String tenSach, String tacGia,
                String NXB, double giaBia, int soLuong) {
        this.baoveASM = baoveASM;
        this.maTheLoai = maTheLoai;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.NXB = NXB;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return baoveASM;
    }

    public void setMaSach(String maSach) {
        this.baoveASM = maSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}