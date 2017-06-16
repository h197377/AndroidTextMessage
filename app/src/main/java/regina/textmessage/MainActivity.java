package regina.textmessage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView msg;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private String message, phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = "1234567890";
        //this is for US number, omit the (+1) and put in what you need!

        btn = (Button) findViewById(R.id.send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = (TextView) findViewById(R.id.text_message);
                message = msg.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                Toast.makeText(getApplicationContext(), "SMS sent. :)",
                        Toast.LENGTH_SHORT).show();
                // this app will NOT work on an emulator, it must be run from a working
                // phone due to the emulator has no real phone number
                smsManager.sendTextMessage(phoneNumber ,null, message, null, null);

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                Toast.makeText(getApplicationContext(), "package manager code is: "+ grantResults[0], Toast.LENGTH_SHORT).show();
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //PackageManager.PERMISSION_GRANTED
                    SmsManager smsManager = SmsManager.getDefault();

                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent. :)",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

    }


}
