package de.fh_erfurt.ai.tutorium.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.fh_erfurt.ai.tutorium.R;
import de.fh_erfurt.ai.tutorium.model.Contact;

/**
 * Created by Paul Cech on 30.03.16.
 */
public class ContactAdapter extends BaseAdapter {

    // ----------------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------------

    public ContactAdapter(ArrayList<Contact> _contacts, Context _context) {
        mContacts = _contacts;
        mContext = _context;
        mInflater = LayoutInflater.from(mContext);
    }


    // ----------------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------------

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_contact, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.mName  = (TextView) convertView.findViewById(R.id.name);
            viewHolder.mMail  = (TextView) convertView.findViewById(R.id.mail);
            viewHolder.mPhone = (TextView) convertView.findViewById(R.id.phone);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = getItem(position);

        viewHolder.mName.setText(contact.getName());
        viewHolder.mMail.setText(contact.getMail());
        viewHolder.mPhone.setText(contact.getPhone());

        return convertView;
    }

    static class ViewHolder {
        public TextView mName;
        public TextView mMail;
        public TextView mPhone;
    }

    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------

    private static final String LOG_TAG = ContactAdapter.class.getSimpleName();

    private ArrayList<Contact> mContacts;
    private Context mContext;
    private LayoutInflater mInflater;
}
