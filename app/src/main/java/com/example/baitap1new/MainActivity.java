package com.example.baitap1new;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView lbl_size, lbl_tortilla, lbl_fillings,lbl_beverage;
    RadioGroup rad_group_size, rad_group_tortilla;
    CheckBox ckbox_beef, ckbox_chicken,ckbox_white_fish,ckbox_cheese,ckbox_seafood,ckbox_rice,ckbox_beans,ckbox_pico_de_gallo,ckbox_guacamole,ckbox_lbt;
    CheckBox ckbox_soda,ckbox_cerveza,ckbo_margarita,ckbox_tequila;
    Button btn_send;
    String ContentOrder;
    final int REQUEST_SEND_SMS = 1, READ_PHONE_STATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreatePaddingTextView();
        GetControl();
        CheckPermission();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentOrder = GetContentOrder();
                SendOrder(ContentOrder,"0764553313");
            }
        });
    }
    //region CreatePaddingTextView
    private void CreatePaddingTextView(){
        lbl_size = (TextView)findViewById(R.id.lbl_size);
        lbl_tortilla = (TextView)findViewById(R.id.lbl_tortilla);
        lbl_fillings = (TextView)findViewById(R.id.lbl_fillings);
        lbl_beverage = (TextView)findViewById(R.id.lbl_beverage);
        lbl_size.setPadding(15,15,15,15);
        lbl_tortilla.setPadding(15,15,15,15);
        lbl_fillings.setPadding(15,15,15,15);
        lbl_beverage.setPadding(15,15,15,15);
    }
    //endregion

    //region GetControl
    private void GetControl(){
        rad_group_size = (RadioGroup)findViewById(R.id.rad_group_size);
        rad_group_tortilla = (RadioGroup)findViewById(R.id.rad_group_tortilla);
        ckbox_beef = (CheckBox)findViewById(R.id.ckbox_beef);
        ckbox_chicken = (CheckBox)findViewById(R.id.ckbox_chicken);
        ckbox_white_fish = (CheckBox)findViewById(R.id.ckbox_white_fish);
        ckbox_cheese = (CheckBox)findViewById(R.id.ckbox_cheese);
        ckbox_seafood = (CheckBox)findViewById(R.id.ckbox_seafood);
        ckbox_rice = (CheckBox)findViewById(R.id.ckbox_rice);
        ckbox_beans = (CheckBox)findViewById(R.id.ckbox_beans);
        ckbox_pico_de_gallo = (CheckBox)findViewById(R.id.ckbox_pico_de_gallo);
        ckbox_guacamole = (CheckBox)findViewById(R.id.ckbox_guacamole);
        ckbox_lbt = (CheckBox)findViewById(R.id.ckbox_lbt);
        ckbox_soda = (CheckBox)findViewById(R.id.ckbox_soda);
        ckbox_cerveza = (CheckBox)findViewById(R.id.ckbox_cerveza);
        ckbo_margarita = (CheckBox)findViewById(R.id.ckbo_margarita);
        ckbox_tequila = (CheckBox)findViewById(R.id.ckbox_tequila);
        btn_send = (Button)findViewById(R.id.btn_send);
    }
    //endregion

    //region GetContentOrder
    private String GetContentOrder(){
        String ContentOrder = "I want a ";
        int SelectedId = 0;
        int countFillings = 0, countBeverage = 0;
        RadioButton rad_btn = null;

        // Get Size
        SelectedId = rad_group_size.getCheckedRadioButtonId();
        rad_btn = (RadioButton)findViewById(SelectedId);
        ContentOrder += rad_btn.getText().toString().toLowerCase() + " Taco - ";

        // Get Tortilla
        SelectedId = rad_group_tortilla.getCheckedRadioButtonId();
        rad_btn = (RadioButton)findViewById(SelectedId);
        ContentOrder += rad_btn.getText().toString() + " tortilla, ";

        // Get Fillings
        if(ckbox_beef.isChecked()) {
            ContentOrder += ckbox_beef.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(ckbox_chicken.isChecked()){
            ContentOrder += ckbox_chicken.getText().toString().toLowerCase()+",";
            countFillings++;
        }
        if(ckbox_white_fish.isChecked()) {
            ContentOrder += ckbox_white_fish.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(ckbox_cheese.isChecked()) {
            ContentOrder += ckbox_cheese.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(ckbox_seafood.isChecked()) {
            ContentOrder += ckbox_seafood.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(ckbox_rice.isChecked()) {
            ContentOrder += ckbox_rice.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(ckbox_pico_de_gallo.isChecked()) {
            ContentOrder += ckbox_pico_de_gallo.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(ckbox_guacamole.isChecked()) {
            ContentOrder += ckbox_guacamole.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(ckbox_lbt.isChecked()) {
            ContentOrder += ckbox_lbt.getText().toString().toLowerCase() + ",";
            countFillings++;
        }
        if(countFillings >0 ) {
            ContentOrder = ContentOrder.substring(0, ContentOrder.length() - 1);
            ContentOrder += " Fillings.";
        }
        else if(countFillings == 0 ){
            ContentOrder += "No Fillings.";
        }

        // Get Beverage
        ContentOrder += "Beverage: ";
        if(ckbox_soda.isChecked()) {
            ContentOrder += ckbox_soda.getText().toString().toLowerCase()+ "-";
            countBeverage++;
        }
        if(ckbox_cerveza.isChecked()) {
            ContentOrder += ckbox_cerveza.getText().toString().toLowerCase()+ "-";
            countBeverage++;
        }
        if(ckbo_margarita.isChecked()) {
            ContentOrder += ckbo_margarita.getText().toString().toLowerCase()+ "-";
            countBeverage++;
        }
        if(ckbox_tequila.isChecked()){
            ContentOrder += ckbox_tequila.getText().toString().toLowerCase()+ "-";
            countBeverage++;
        }
        if(countBeverage > 0) {
            ContentOrder = ContentOrder.substring(0, ContentOrder.length() - 1);
            ContentOrder += ".";
        }
        else if(countBeverage == 0)
            ContentOrder += " no.";
        return ContentOrder;
    }
    //endregion

    //region SendOrder
    private void SendOrder(String Content, String Numphone){
        if(isSimAvailable() == true) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(
                    Numphone,
                    null,
                    Content,
                    null,
                    null);
            Toast.makeText(getApplicationContext(), "Send Message sucessfully", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "You don't have SIM to send", Toast.LENGTH_LONG).show();
        }

    }
    //endregion

    //region CheckPermission
    private void CheckPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},REQUEST_SEND_SMS);
        }
        else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE);
        }
    }
    //endregion

    //region onRequestPermissionsResult
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_SEND_SMS:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    btn_send.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(),"You can't order taco, because you accept permission SEND_SMS.",Toast.LENGTH_LONG).show();
                    btn_send.setEnabled(false);
                }
            }
            case READ_PHONE_STATE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    btn_send.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(),"You can't order taco, because you accept permission READ_PHONE_STATE.",Toast.LENGTH_LONG).show();
                    btn_send.setEnabled(false);
                }
            }
        }
    }
    //endregion

    public boolean isSimAvailable() {
        boolean isAvailable = false;
        TelephonyManager telMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT: //SimState = “No Sim Found!”;
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED: //SimState = “Network Locked!”;
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED: //SimState = “PIN Required to access SIM!”;
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED: //SimState = “PUK Required to access SIM!”; // Personal Unblocking Code
                break;
            case TelephonyManager.SIM_STATE_READY:
                isAvailable = true;
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN: //SimState = “Unknown SIM State!”;
                break;
        }
        return isAvailable;
    }
}
