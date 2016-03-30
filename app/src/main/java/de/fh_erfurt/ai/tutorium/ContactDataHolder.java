package de.fh_erfurt.ai.tutorium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.fh_erfurt.ai.tutorium.model.Contact;

/**
 * Created by Paul Cech on 30.03.16.
 */
public class ContactDataHolder {

    // ----------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------

    private ContactDataHolder() {
        mContacts = new ArrayList<Contact>();
    }

    // ----------------------------------------------------------------------------
    // Singleton
    // ----------------------------------------------------------------------------

    private static ContactDataHolder sInstance;

    public static ContactDataHolder getInstance() {
        if (sInstance == null) {
            sInstance = new ContactDataHolder();
        }
        return sInstance;
    }

    // ----------------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------------

    public ArrayList<Contact> getContacts() {
        return mContacts;
    }

    public void setContacts(ArrayList<Contact> _contacts) {
        mContacts = _contacts;
    }

    public void addContact(Contact _contact) {
        mContacts.add(_contact);
    }

    // ----------------------------------------------------------------------------
    // Listener
    // ----------------------------------------------------------------------------

    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------


    private static final String LOG_TAG = ContactDataHolder.class.getSimpleName();

    private ArrayList<Contact> mContacts;
}
