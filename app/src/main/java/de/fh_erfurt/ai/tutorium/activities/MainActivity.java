package de.fh_erfurt.ai.tutorium.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.fh_erfurt.ai.tutorium.dataholder.ContactDataHolder;
import de.fh_erfurt.ai.tutorium.eventbus.BusProvider;
import de.fh_erfurt.ai.tutorium.eventbus.events.ContactsLoadedEvent;
import de.fh_erfurt.ai.tutorium.services.ContactService;
import de.fh_erfurt.ai.tutorium.R;
import de.fh_erfurt.ai.tutorium.adapter.ContactAdapter;
import de.fh_erfurt.ai.tutorium.model.Contact;
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
        mContactList.setOnItemClickListener(mContactClickListener);
        mContactList.setOnItemLongClickListener(mContactLongClickListener);

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

        switch (_RequestCode) {
            case ContactActivity.REQUEST_ADD_CONTACT: {
                if (_ResultCode == RESULT_OK) {
                    if (_Data != null) {

                        String name  = _Data.getStringExtra(ContactActivity.KEY_NAME);
                        String mail  = _Data.getStringExtra(ContactActivity.KEY_MAIL);
                        String phone = _Data.getStringExtra(ContactActivity.KEY_PHONE);

                        Contact contact = new Contact(name, mail, phone);
                        ContactDataHolder.getInstance().addContact(contact);

                        mContactAdapter.notifyDataSetChanged();

                        ContactService.startSaveContacts(this);
                    }
                }
            }
            break;

            case ContactActivity.REQUEST_EDIT_CONTACT: {
                if (_ResultCode == RESULT_OK) {
                    if (_Data != null) {

                        int contactId = _Data.getIntExtra(ContactActivity.KEY_ID, -1);
                        String name   = _Data.getStringExtra(ContactActivity.KEY_NAME);
                        String mail   = _Data.getStringExtra(ContactActivity.KEY_MAIL);
                        String phone  = _Data.getStringExtra(ContactActivity.KEY_PHONE);

                        if (contactId >= 0) {
                            Contact contact = ContactDataHolder.getInstance().getContactById(contactId);
                            contact.setName(name);
                            contact.setMail(mail);
                            contact.setPhone(phone);

                            mContactAdapter.notifyDataSetChanged();

                            ContactService.startSaveContacts(this);
                        }
                    }
                }
            }
            break;
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

    private void deleteContact(int _contactId) {
        ContactDataHolder.getInstance().removeContact(_contactId);
        mContactAdapter.notifyDataSetChanged();
        ContactService.startSaveContacts(this);
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

    private AdapterView.OnItemClickListener mContactClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = ContactActivity.getEditIntent(MainActivity.this, position);
            startActivityForResult(intent, ContactActivity.REQUEST_EDIT_CONTACT);
        }
    };

    private AdapterView.OnItemLongClickListener mContactLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            deleteContact(position);
            return true;
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
