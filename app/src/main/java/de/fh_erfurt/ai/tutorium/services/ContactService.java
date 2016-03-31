package de.fh_erfurt.ai.tutorium.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.util.ArrayList;

import de.fh_erfurt.ai.tutorium.ContactDataHolder;
import de.fh_erfurt.ai.tutorium.eventbus.BusProvider;
import de.fh_erfurt.ai.tutorium.eventbus.events.ContactsLoadedEvent;
import de.fh_erfurt.ai.tutorium.model.Contact;
import de.fh_erfurt.ai.tutorium.utils.StorageWrapper;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class ContactService extends IntentService {

    private static final String ACTION_LOAD_CONTACTS = "de.fh_erfurt.ai.tutorium.services.action.FOO";

    public ContactService() {
        super("ContactService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startLoadContacts(Context context) {
        Intent intent = new Intent(context, ContactService.class);
        intent.setAction(ACTION_LOAD_CONTACTS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LOAD_CONTACTS.equals(action)) {
                handleLoadContacts();
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
}
