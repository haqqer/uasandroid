package com.haqqer.uasandroid.models;

public class data_mahasiswa {

    //Deklarasi Variable
    private String id;
    private String nim;
    private String nama;
    private String semester;
    private Double ipk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Double getIpk() {
        return ipk;
    }

    public void setIpk(Double ipk) {
        this.ipk = ipk;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_mahasiswa(String getNIM, String getNama, String getJurusan){
    }

    public data_mahasiswa() {

    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_mahasiswa(String nim, String nama, String semester, Double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.ipk = ipk;
    }
}