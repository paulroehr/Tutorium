package de.fh_erfurt.ai.tutorium.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul Cech on 30.03.16.
 */
public class Contact {

    // ----------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------

    public Contact() {
    }

    public Contact(String _name, String _mail, String _phone) {
        mName = _name;
        mMail = _mail;
        mPhone = _phone;
    }

    // ----------------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------------

    public String getName() {
        return mName;
    }

    public void setName(String _name) {
        mName = _name;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String _mail) {
        mMail = _mail;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String _phone) {
        mPhone = _phone;
    }


    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------

    private static final String LOG_TAG = Contact.class.getSimpleName();

    @SerializedName("name")
    private String mName;

    @SerializedName("mail")
    private String mMail;

    @SerializedName("phone")
    private String mPhone;
}
