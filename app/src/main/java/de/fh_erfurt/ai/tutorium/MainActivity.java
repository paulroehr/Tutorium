package de.fh_erfurt.ai.tutorium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName       = (TextView) findViewById(R.id.name);
        mMail       = (TextView) findViewById(R.id.mail);
        mPhone      = (TextView) findViewById(R.id.phone);
        mAddContact = (Button)   findViewById(R.id.addContact);

        mAddContact.setOnClickListener(mAddContactClickListener);
    }

    @Override
    protected void onActivityResult(int _RequestCode, int _ResultCode, Intent _Data) {
        super.onActivityResult(_RequestCode, _ResultCode, _Data);
         if (_RequestCode == ContactActivity.REQUEST_ADD_CONTACT) {
             if (_ResultCode == RESULT_OK) {
                 if (_Data != null) {
                     String name = _Data.getStringExtra(ContactActivity.KEY_NAME);
                     String mail = _Data.getStringExtra(ContactActivity.KEY_MAIL);
                     String phone = _Data.getStringExtra(ContactActivity.KEY_PHONE);

                     mName.setText(name);
                     mMail.setText(mail);
                     mPhone.setText(phone);
                 }
             }
             else {

             }
         }
    }

    // -----------------------------------------------------------------------------
    // Listener
    // -----------------------------------------------------------------------------

    private View.OnClickListener mAddContactClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View _V) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivityForResult(intent, ContactActivity.REQUEST_ADD_CONTACT);
        }
    };

    // -----------------------------------------------------------------------------
    // Member
    // -----------------------------------------------------------------------------

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView mName;
    private TextView mPhone;
    private TextView mMail;
    private Button   mAddContact;
}
