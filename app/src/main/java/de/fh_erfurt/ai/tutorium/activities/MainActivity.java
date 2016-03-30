package de.fh_erfurt.ai.tutorium.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import de.fh_erfurt.ai.tutorium.ContactDataHolder;
import de.fh_erfurt.ai.tutorium.ContactParser;
import de.fh_erfurt.ai.tutorium.R;
import de.fh_erfurt.ai.tutorium.adapter.ContactAdapter;
import de.fh_erfurt.ai.tutorium.model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddContact = (Button)   findViewById(R.id.addContact);
        mContactList = (ListView) findViewById(R.id.contactList);

        mAddContact.setOnClickListener(mAddContactClickListener);

        mContactAdapter = new ContactAdapter(
                ContactDataHolder.getInstance().getContacts(),
                this
        );

        mContactList.setAdapter(mContactAdapter);
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

                     Contact contact = new Contact(name, mail, phone);
                     ContactDataHolder.getInstance().addContact(contact);

                     mContactAdapter.notifyDataSetChanged();

                     Log.d(LOG_TAG, ContactParser.loadFromContacts(ContactDataHolder.getInstance().getContacts()));
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

    private Button   mAddContact;
    private ListView mContactList;

    private ContactAdapter mContactAdapter;
}
