package de.fh_erfurt.ai.tutorium.eventbus.events;

import java.util.ArrayList;

import de.fh_erfurt.ai.tutorium.model.Contact;

/**
 * Created by Paul Cech on 31.03.16.
 */
public class ContactsLoadedEvent {

    // ----------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------

    public ContactsLoadedEvent(ArrayList<Contact> _contacts) {
        mContacts = _contacts;
    }

    // ----------------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------------

    public ArrayList<Contact> getContacts() {
        return mContacts;
    }

    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------

    private static final String LOG_TAG = ContactsLoadedEvent.class.getSimpleName();

    private ArrayList<Contact> mContacts;
}
