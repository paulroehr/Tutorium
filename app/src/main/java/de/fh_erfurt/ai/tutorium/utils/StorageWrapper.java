package de.fh_erfurt.ai.tutorium.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import de.fh_erfurt.ai.tutorium.model.Contact;

/**
 * Created by Paul Cech on 31.03.16.
 */
public class StorageWrapper {

    public static ArrayList<Contact> loadContacts() {
        ArrayList<Contact> contacts;

        String         jsonString = "";
        File           sdCard     = Environment.getExternalStorageDirectory();
        FileReader     fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader      = new FileReader(sdCard.getAbsolutePath() + File.separator + FILE_NAME);
            bufferedReader  = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                jsonString += line;
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!jsonString.isEmpty()) {
            contacts = ContactParser.loadFromJson(jsonString);
        }
        else {
            contacts = new ArrayList<>();
        }

        Log.i(LOG_TAG, "Loaded Contacts: ");

        return contacts;
    }

    public static void saveContacts(ArrayList<Contact> contacts) {
        Log.i(LOG_TAG, "Saving Contacts: " + contacts);

        String          jsonContacts = ContactParser.loadFromContacts(contacts);
        File            sdCard       = Environment.getExternalStorageDirectory();
        FileWriter      fileWriter;
        BufferedWriter  bufferedWriter;

        try {
            fileWriter     = new FileWriter(sdCard.getAbsolutePath() + File.separator + FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jsonContacts);
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------

    private static final String LOG_TAG = StorageWrapper.class.getSimpleName();

    private static final String FILE_NAME = "contacts.json";
}
