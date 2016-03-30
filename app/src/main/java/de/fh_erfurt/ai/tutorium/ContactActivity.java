package de.fh_erfurt.ai.tutorium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mName  = (EditText) findViewById(R.id.name);
        mMail  = (EditText) findViewById(R.id.mail);
        mPhone = (EditText) findViewById(R.id.phone);
        mSave  = (Button)   findViewById(R.id.saveContact);

        mSave.setOnClickListener(mSaveClickListener);

        setResult(RESULT_CANCELED);
    }

    private void returnContactData() {
        String name = mName.getText().toString();
        String mail = mMail.getText().toString();
        String phone = mPhone.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(KEY_NAME, name);
        intent.putExtra(KEY_MAIL, mail);
        intent.putExtra(KEY_PHONE, phone);

        setResult(RESULT_OK, intent);
        finish();
    }

    // -----------------------------------------------------------------------------
    // Listener
    // -----------------------------------------------------------------------------

    private View.OnClickListener mSaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View _V) {
            returnContactData();
        }
    };

    // -----------------------------------------------------------------------------
    // Member
    // -----------------------------------------------------------------------------

    private static final String LOG_TAG = ContactActivity.class.getSimpleName();

    public static final int REQUEST_ADD_CONTACT = 10;

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";
    public static final String KEY_PHONE = "phone";

    private EditText mName;
    private EditText mMail;
    private EditText mPhone;
    private Button   mSave;

}
