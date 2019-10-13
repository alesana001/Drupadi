package com.simpus.drupadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simpus.drupadi.Utils.Network.ApiClient;
import com.simpus.drupadi.Utils.Network.ApiInterface;
import com.simpus.drupadi.Utils.Tools;
import com.simpus.drupadi.adapter.JenisPetugasAdapter;
import com.simpus.drupadi.model.ListJenis;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrasiActivity extends AppCompatActivity {

    @BindView(R.id.et_nik) EditText et_nik;
    @BindView(R.id.et_jenisptgs) EditText et_jenisptgs;
    @BindView(R.id.et_nip) EditText et_nip;
    @BindView(R.id.et_pass) EditText et_pass;
    @BindView(R.id.et_nama) EditText et_nama;
    @BindView(R.id.et_notelp) EditText et_notelp;
    @BindView(R.id.et_kecamatan) EditText et_kecamatan;
    @BindView(R.id.et_desa) EditText et_desa;
    @BindView(R.id.bt_submit) Button bt_submit;

    RecyclerView listcheckbox;
    ArrayList<ListJenis> listKecamatan, listDesa, listJenis;
    JenisPetugasAdapter adapter;
    public static ArrayList<String> idptgs;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        ButterKnife.bind(this);
        initToolbar();
        idptgs = new ArrayList<>();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mContext = this;

    }
    @OnClick(R.id.bt_submit)void register(){
        if (et_nik.getText().toString().isEmpty() || et_jenisptgs.getText().toString().isEmpty()
        || et_pass.getText().toString().isEmpty()||et_nama.getText().toString().isEmpty()||et_nip.getText().toString().isEmpty()
        ||et_notelp.getText().toString().isEmpty()||et_desa.getText().toString().isEmpty()||et_kecamatan.getText().toString().isEmpty()){
            Toast.makeText(mContext, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else {
//            new AsyncTask<Void,Void,Void>(){
//                @Override
//                protected void onPreExecute() {
//                    super.onPreExecute();
//                    progressDialog.show();
//                }
//
//                @Override
//                protected void onPostExecute(Void aVoid) {
//                    super.onPostExecute(aVoid);
//                }
//
//                @Override
//                protected Void doInBackground(Void... voids) {
//                    logintask();
//                    return null;
//                }
//            }.execute();
            Toast.makeText(mContext, "sudah lengkap", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.et_jenisptgs) void jenisptgs(final View v){
        dialog  = new AlertDialog.Builder(RegistrasiActivity.this);
        inflater =getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_data,null);
        dialog.setView(dialogView);
        //dialog.show();
        dialog.setCancelable(true);
        dialog.setTitle("Pilih Jenis Petugas");
        listcheckbox = (RecyclerView) dialogView.findViewById(R.id.listcheckbox);
        getDataPtgs();

        dialog.setPositiveButton("SUBMIT", (dialog, which) -> {
            //menaruh handler ketika di klik
            dialog.dismiss();
        });

        dialog.setNegativeButton("CANCEL", (dialog, which) -> {
            //handling ketika cancel di klik
            dialog.dismiss();
        });
        dialog.show();
        Log.d("DEBUGS", String.valueOf(idptgs));
        et_jenisptgs.setText(idptgs.toString());
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daftar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.light_green_700);
    }
    public void getDataPtgs(){
        listJenis = new ArrayList<ListJenis>();
        listJenis.add(new ListJenis("B","Bidan"));
        listJenis.add(new ListJenis("NB","Non Bidan"));
        listJenis.add(new ListJenis("BK","Bidan Koordinator"));
        listJenis.add(new ListJenis("BM","Bidan Praktek Mandiri"));
        listJenis.add(new ListJenis("L","Lurah"));
        listJenis.add(new ListJenis("C","Camat"));
        listJenis.add(new ListJenis("RS","Rumah Sakit"));
        listJenis.add(new ListJenis("KL","Klinik"));
        listJenis.add(new ListJenis("LN","Lain-lain"));

        //menambah data ke recycleview
        LinearLayoutManager layoutManager= new
                LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,
                false);
        listcheckbox.setHasFixedSize(true);
        listcheckbox.setLayoutManager(layoutManager);
        adapter = new JenisPetugasAdapter(RegistrasiActivity.this,listJenis);
        adapter.notifyDataSetChanged();
        listcheckbox.setAdapter(adapter);
        }

    }