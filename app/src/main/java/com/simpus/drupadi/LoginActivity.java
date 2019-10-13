package com.simpus.drupadi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.simpus.drupadi.Utils.Network.ApiClient;
import com.simpus.drupadi.Utils.Network.ApiInterface;
import com.simpus.drupadi.Utils.SharedPrefManager;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_user) EditText et_user;
    @BindView(R.id.et_pass) EditText et_pass;
    @BindView(R.id.btndaftar)  Button btndaftar;
    @BindView(R.id.btnlogin) Button btnmasuk;
    ProgressDialog progressDialog;
    Context mContext;
    SharedPrefManager sharedPrefManager;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // sharedPrefManager = new SharedPrefManager(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mContext = this;
    }

    @OnClick(R.id.btnlogin) public void login(){
        startActivity(new Intent(mContext, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

    }

    @OnClick(R.id.btndaftar) public void register(){
        startActivity(new Intent(mContext, RegistrasiActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

    }

}
