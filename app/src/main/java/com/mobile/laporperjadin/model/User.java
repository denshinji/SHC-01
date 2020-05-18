package com.mobile.laporperjadin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String no;
    private String idUser;
    private String nama;
    private String jabatan;
    private String nip;
    private String email;
    private String username;



    protected User(Parcel in) {
        no = in.readString();
        idUser = in.readString();
        nama = in.readString();
        jabatan = in.readString();
        nip = in.readString();
        email = in.readString();
        username = in.readString();
        status = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(String no, String idUser, String nama, String jabatan, String nip, String email, String username, String status) {
        this.no = no;
        this.idUser = idUser;
        this.nama = nama;
        this.jabatan = jabatan;
        this.nip = nip;
        this.email = email;
        this.username = username;
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(no);
        dest.writeString(idUser);
        dest.writeString(nama);
        dest.writeString(jabatan);
        dest.writeString(nip);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(status);
    }
}
