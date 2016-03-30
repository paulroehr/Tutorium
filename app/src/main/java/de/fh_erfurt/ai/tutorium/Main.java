package de.fh_erfurt.ai.tutorium;

import android.app.Application;

/**
 * Created by Paul Cech on 30.03.16.
 */
public class Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

    }

    // ----------------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------------

    public static Application getApplication() {
        return mApplication;
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

    private static final String LOG_TAG = Main.class.getSimpleName();

    private static Application mApplication;
}
