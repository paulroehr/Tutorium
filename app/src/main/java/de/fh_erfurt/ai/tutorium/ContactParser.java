package de.fh_erfurt.ai.tutorium;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import de.fh_erfurt.ai.tutorium.model.Contact;

/**
 * Created by Paul Cech on 30.03.16.
 */
public class ContactParser {

    // ----------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------

    public ContactParser() {
    }

    // ----------------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------------

    public static String loadFromContacts(ArrayList<Contact> _contacts) {
        return new GsonBuilder().create().toJson(_contacts);
    }

    public static ArrayList<Contact> loadFromJson(String _json) {
        return new ArrayList<>(Arrays.asList(new GsonBuilder().create().fromJson(_json, Contact[].class)));
    }

    // ----------------------------------------------------------------------------
    // Private methods
    // ----------------------------------------------------------------------------

    // ----------------------------------------------------------------------------
    // Listener
    // ----------------------------------------------------------------------------

    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------

    private static final String LOG_TAG = ContactParser.class.getSimpleName();
}
