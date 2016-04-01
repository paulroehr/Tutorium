package de.fh_erfurt.ai.tutorium.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.fh_erfurt.ai.tutorium.R;
import de.fh_erfurt.ai.tutorium.dataholder.ContactDataHolder;
import de.fh_erfurt.ai.tutorium.model.Contact;

public class ContactActivity extends AppCompatActivity {

    public static Intent getEditIntent(Context _context, int _contactId) {
        Intent intent = new Intent(_context, ContactActivity.class);
        intent.putExtra(KEY_ID, _contactId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        if (intent != null) {
            mContactId = intent.getIntExtra(KEY_ID, DEFAULT_CONTACT_ID);
        }

        mName  = (EditText) findViewById(R.id.name);
        mMail  = (EditText) findViewById(R.id.mail);
        mPhone = (EditText) findViewById(R.id.phone);
        mSave  = (Button)   findViewById(R.id.saveContact);

        mSave.setOnClickListener(mSaveClickListener);

        if (mContactId != DEFAULT_CONTACT_ID) {
            Contact contact = ContactDataHolder.getInstance().getContactById(mContactId);
            mName.setText(contact.getName());
            mMail.setText(contact.getMail());
            mPhone.setText(contact.getPhone());
        }

        setResult(RESULT_CANCELED);
    }

    private void returnContactData() {
        String name  = mName.getText().toString();
        String mail  = mMail.getText().toString();
        String phone = mPhone.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(KEY_NAME, name);
        intent.putExtra(KEY_MAIL, mail);
        intent.putExtra(KEY_PHONE, phone);

        if (mContactId != DEFAULT_CONTACT_ID) {
            intent.putExtra(KEY_ID, mContactId);
        }

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

    private static final int DEFAULT_CONTACT_ID = -1;

    public static final int REQUEST_ADD_CONTACT  = 10;
    public static final int REQUEST_EDIT_CONTACT = 20;

    public static final String KEY_ID    = "contactId";

    public static final String KEY_NAME  = "name";
    public static final String KEY_MAIL  = "mail";
    public static final String KEY_PHONE = "phone";

    private EditText mName;
    private EditText mMail;
    private EditText mPhone;
    private Button   mSave;

    private int      mContactId;

}
