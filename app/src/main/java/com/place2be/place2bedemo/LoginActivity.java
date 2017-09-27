package com.place2be.place2bedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        HandleClick hc = new HandleClick();
        findViewById(R.id.buttonQR).setOnClickListener(hc);
    }

    private class HandleClick implements View.OnClickListener{
        public void onClick(View arg0) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            switch(arg0.getId()){
                case R.id.buttonQR:
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    break;
            }
            startActivityForResult(intent, 0);  //Barcode Scanner to scan for us
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            EditText editAPIkey=(EditText)findViewById(R.id.editAPIkey);
            if (resultCode == RESULT_OK) {
                editAPIkey.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                editAPIkey.setText("Scan cancelled.");
            }
        }
    }

    public void sendLoginMessage(View view){
        // Do something after login
        if(loginSuccess()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            // show error
        }

    }

    public boolean loginSuccess(){
        return true;
    }
}
