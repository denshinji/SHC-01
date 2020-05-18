package com.mobile.laporperjadin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PengajuanAdmin implements Parcelable {
    private String no;
    private String id;
    private String id_pengajuan;
    private String id_user;
    private String nama;
    private String kotaTujuan;
    private String tglBerangkat;
    private String tglKembali;
    private String biayaPesawat;
    private String biayaPenginapan;
    private String biayaTaksiBandara;
    private String biayaTaksiDaerah;
    private String uangTunai;
    private String tglPengajuan;
    private String statusPengajuan;


    public PengajuanAdmin(String no, String id, String id_pengajuan, String id_user, String nama, String kotaTujuan, String tglBerangkat, String tglKembali, String biayaPesawat, String biayaPenginapan, String biayaTaksiBandara, String biayaTaksiDaerah, String uangTunai, String tglPengajuan, String statusPengajuan) {
        this.no = no;
        this.id = id;
        this.id_pengajuan = id_pengajuan;
        this.id_user = id_user;
        this.nama = nama;
        this.kotaTujuan = kotaTujuan;
        this.tglBerangkat = tglBerangkat;
        this.tglKembali = tglKembali;
        this.biayaPesawat = biayaPesawat;
        this.biayaPenginapan = biayaPenginapan;
        this.biayaTaksiBandara = biayaTaksiBandara;
        this.biayaTaksiDaerah = biayaTaksiDaerah;
        this.uangTunai = uangTunai;
        this.tglPengajuan = tglPengajuan;
        this.statusPengajuan = statusPengajuan;
    }



    public PengajuanAdmin() {
    }

    protected PengajuanAdmin(Parcel in) {
        no = in.readString();
        id = in.readString();
        id_pengajuan = in.readString();
        id_user = in.readString();
        nama = in.readString();
        kotaTujuan = in.readString();
        tglBerangkat = in.readString();
        tglKembali = in.readString();
        biayaPesawat = in.readString();
        biayaPenginapan = in.readString();
        biayaTaksiBandara = in.readString();
        biayaTaksiDaerah = in.readString();
        uangTunai = in.readString();
        tglPengajuan = in.readString();
        statusPengajuan = in.readString();
    }

    public static final Creator<PengajuanAdmin> CREATOR = new Creator<PengajuanAdmin>() {
        @Override
        public PengajuanAdmin createFromParcel(Parcel in) {
            return new PengajuanAdmin(in);
        }

        @Override
        public PengajuanAdmin[] newArray(int size) {
            return new PengajuanAdmin[size];
        }
    };

    public String getId_pengajuan() {
        return id_pengajuan;
    }

    public void setId_pengajuan(String id_pengajuan) {
        this.id_pengajuan = id_pengajuan;
    }

    public String getTglPengajuan() {
        return tglPengajuan;
    }

    public void setTglPengajuan(String tglPengajuan) {
        this.tglPengajuan = tglPengajuan;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getTglBerangkat() {
        return tglBerangkat;
    }

    public void setTglBerangkat(String tglBerangkat) {
        this.tglBerangkat = tglBerangkat;
    }

    public String getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(String tglKembali) {
        this.tglKembali = tglKembali;
    }

    public String getBiayaPesawat() {
        return biayaPesawat;
    }

    public void setBiayaPesawat(String biayaPesawat) {
        this.biayaPesawat = biayaPesawat;
    }

    public String getBiayaPenginapan() {
        return biayaPenginapan;
    }

    public void setBiayaPenginapan(String biayaPenginapan) {
        this.biayaPenginapan = biayaPenginapan;
    }

    public String getBiayaTaksiBandara() {
        return biayaTaksiBandara;
    }

    public void setBiayaTaksiBandara(String biayaTaksiBandara) {
        this.biayaTaksiBandara = biayaTaksiBandara;
    }

    public String getBiayaTaksiDaerah() {
        return biayaTaksiDaerah;
    }

    public void setBiayaTaksiDaerah(String biayaTaksiDaerah) {
        this.biayaTaksiDaerah = biayaTaksiDaerah;
    }

    public String getUangTunai() {
        return uangTunai;
    }

    public void setUangTunai(String uangTunai) {
        this.uangTunai = uangTunai;
    }

    public String getStatusPengajuan() {
        return statusPengajuan;
    }

    public void setStatusPengajuan(String statusPengajuan) {
        this.statusPengajuan = statusPengajuan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(no);
        dest.writeString(id);
        dest.writeString(id_pengajuan);
        dest.writeString(id_user);
        dest.writeString(nama);
        dest.writeString(kotaTujuan);
        dest.writeString(tglBerangkat);
        dest.writeString(tglKembali);
        dest.writeString(biayaPesawat);
        dest.writeString(biayaPenginapan);
        dest.writeString(biayaTaksiBandara);
        dest.writeString(biayaTaksiDaerah);
        dest.writeString(uangTunai);
        dest.writeString(tglPengajuan);
        dest.writeString(statusPengajuan);
    }
}
