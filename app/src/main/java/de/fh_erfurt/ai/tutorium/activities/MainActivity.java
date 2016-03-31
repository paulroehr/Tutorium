package de.fh_erfurt.ai.tutorium.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.fh_erfurt.ai.tutorium.ContactDataHolder;
import de.fh_erfurt.ai.tutorium.eventbus.BusProvider;
import de.fh_erfurt.ai.tutorium.eventbus.events.ContactsLoadedEvent;
import de.fh_erfurt.ai.tutorium.services.ContactService;
import de.fh_erfurt.ai.tutorium.utils.ContactParser;
import de.fh_erfurt.ai.tutorium.R;
import de.fh_erfurt.ai.tutorium.adapter.ContactAdapter;
import de.fh_erfurt.ai.tutorium.model.Contact;
import de.fh_erfurt.ai.tutorium.utils.StorageWrapper;
import de.fh_erfurt.ai.tutorium.utils.permissions.PermissionRequest;
import de.fh_erfurt.ai.tutorium.utils.permissions.PermissionRequestCodes;
import de.fh_erfurt.ai.tutorium.utils.permissions.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddContact = (Button)   findViewById(R.id.addContact);
        mContactList = (ListView) findViewById(R.id.contactList);

        mAddContact.setOnClickListener(mAddContactClickListener);

        if (PermissionRequest.requestStorage(this)) {
            // Load contacts
            initializeContacts();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.register(this);
    }

    @Override
    protected void onStop() {
        BusProvider.unregister(this);
        super.onStop();
    }

    @Override
    protected void onActivityResult(int _RequestCode, int _ResultCode, Intent _Data) {
        super.onActivityResult(_RequestCode, _ResultCode, _Data);

         if (_RequestCode == ContactActivity.REQUEST_ADD_CONTACT) {
             if (_ResultCode == RESULT_OK) {
                 if (_Data != null) {

                     String name  = _Data.getStringExtra(ContactActivity.KEY_NAME);
                     String mail  = _Data.getStringExtra(ContactActivity.KEY_MAIL);
                     String phone = _Data.getStringExtra(ContactActivity.KEY_PHONE);

                     Contact contact = new Contact(name, mail, phone);
                     ContactDataHolder.getInstance().addContact(contact);

                     mContactAdapter.notifyDataSetChanged();

                     StorageWrapper.saveContacts(ContactDataHolder.getInstance().getContacts());

                     Log.d(LOG_TAG, ContactParser.loadFromContacts(ContactDataHolder.getInstance().getContacts()));
                 }
             }
             else {

             }
         }
    }

    @Override
    public void onRequestPermissionsResult(int _RequestCode, String[] _Permissions, int[] _GrantResults) {
        super.onRequestPermissionsResult(_RequestCode, _Permissions, _GrantResults);

        switch (_RequestCode) {
            case PermissionRequestCodes.STORAGE_RW: {

                if (PermissionUtils.verifyPermissions(_GrantResults)) {
                    // Load contacts
                    initializeContacts();
                }
                else {

                }
            }
        }

    }

    private void initializeContacts() {
        ContactService.startLoadContacts(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onContactsLoaded(ContactsLoadedEvent _event) {
        mContactAdapter = new ContactAdapter(
                _event.getContacts(),
                this
        );

        mContactList.setAdapter(mContactAdapter);
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
