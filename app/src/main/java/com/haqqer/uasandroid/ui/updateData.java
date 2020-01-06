package com.haqqer.uasandroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.haqqer.uasandroid.R;
import com.haqqer.uasandroid.models.data_mahasiswa;

public class updateData extends AppCompatActivity {

    //Deklarasi Variable
    private EditText nimBaru, namaBaru, semesterBaru, ipkBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekNIM, cekNama, cekSemester;
    private Double cekIpk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getSupportActionBar().setTitle("Update Data");
        nimBaru = findViewById(R.id.new_nim);
        namaBaru = findViewById(R.id.new_nama);
        semesterBaru = findViewById(R.id.new_semester);
        ipkBaru = findViewById(R.id.new_ipk);
        update = findViewById(R.id.update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekNIM = nimBaru.getText().toString();
                cekNama = namaBaru.getText().toString();
                cekSemester = semesterBaru.getText().toString();
                cekIpk = Double.parseDouble(ipkBaru.getText().toString());

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekNIM) || isEmpty(cekNama) || isEmpty(cekSemester) || cekIpk < 1){
                    Toast.makeText(updateData.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                      Menjalankan proses update data.
                      Method Setter digunakan untuk mendapakan data baru yang diinputkan User.
                    */
                    data_mahasiswa setMahasiswa = new data_mahasiswa();
                    setMahasiswa.setNim(nimBaru.getText().toString());
                    setMahasiswa.setNama(namaBaru.getText().toString());
                    setMahasiswa.setSemester(semesterBaru.getText().toString());
                    setMahasiswa.setIpk(Double.parseDouble(ipkBaru.getText().toString()));
                    System.out.print(setMahasiswa);
                    updateMahasiswa(setMahasiswa);
                }
            }
        });
    }

    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    //Menampilkan data yang akan di update
    private void getData(){
        //Menampilkan data dari item yang dipilih sebelumnya
        final String getNIM = getIntent().getExtras().getString("dataNIM");
        final String getNama = getIntent().getExtras().getString("dataNama");
        final String getSemester = getIntent().getExtras().getString("dataSemester");
        final Double getIpk = getIntent().getExtras().getDouble("dataIpk");
        nimBaru.setText(getNIM);
        namaBaru.setText(getNama);
        semesterBaru.setText(getSemester);
        ipkBaru.setText(getIpk.toString());
    }

    //Proses Update data yang sudah ditentukan
    private void updateMahasiswa(data_mahasiswa mahasiswa){
        String userID = auth.getUid();
        String getId = getIntent().getExtras().getString("getId");
        database.child("Admin")
                .child(userID)
                .child("Mahasiswa")
                .child(getId)
                .setValue(mahasiswa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nimBaru.setText("");
                        namaBaru.setText("");
                        semesterBaru.setText("");
                        ipkBaru.setText("");
                        Toast.makeText(updateData.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
