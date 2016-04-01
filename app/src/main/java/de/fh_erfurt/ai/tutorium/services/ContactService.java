package de.fh_erfurt.ai.tutorium.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.util.ArrayList;

import de.fh_erfurt.ai.tutorium.dataholder.ContactDataHolder;
import de.fh_erfurt.ai.tutorium.model.Contact;
import de.fh_erfurt.ai.tutorium.utils.StorageWrapper;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class ContactService extends IntentService {

    private static final String ACTION_LOAD_CONTACTS = "de.fh_erfurt.ai.tutorium.services.action.LOAD_CONTACTS";
    private static final String ACTION_SAVE_CONTACTS = "de.fh_erfurt.ai.tutorium.services.action.SAVE_CONTACTS";

    public ContactService() {
        super("ContactService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startLoadContacts(Context _context) {
        Intent intent = new Intent(_context, ContactService.class);
        intent.setAction(ACTION_LOAD_CONTACTS);
        _context.startService(intent);
    }


    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startSaveContacts(Context _context) {
        Intent intent = new Intent(_context, ContactService.class);
        intent.setAction(ACTION_SAVE_CONTACTS);
        _context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            switch (action) {
                case ACTION_LOAD_CONTACTS:
                    handleLoadContacts();
                    break;

                case ACTION_SAVE_CONTACTS:
                    handleSaveContacts();
                    break;
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleLoadContacts() {
        ArrayList<Contact> contacts = StorageWrapper.loadContacts();
        ContactDataHolder.getInstance().setContacts(contacts);
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleSaveContacts() {
        StorageWrapper.saveContacts(ContactDataHolder.getInstance().getContacts());
    }
}
