package de.fh_erfurt.ai.tutorium.dataholder;

import java.util.ArrayList;

import de.fh_erfurt.ai.tutorium.eventbus.BusProvider;
import de.fh_erfurt.ai.tutorium.eventbus.events.ContactsLoadedEvent;
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

        BusProvider.post(new ContactsLoadedEvent(mContacts));
    }

    public Contact getContactById(int _contactId) {
        // Check if id is valid and available in the contact list.
        if (_contactId >= mContacts.size()) {
            return null;
        }
        return mContacts.get(_contactId);
    }

    public void addContact(Contact _contact) {
        mContacts.add(_contact);
    }

    public boolean removeContact(Contact _contact) {
        return mContacts.remove(_contact);
    }

    public boolean removeContact(int _contactId) {
        // Check if id is valid and available in the contact list.
        if (_contactId >= mContacts.size()) {
            return false;
        }
        return removeContact(mContacts.get(_contactId));
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
