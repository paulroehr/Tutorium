package de.fh_erfurt.ai.tutorium.eventbus;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by Paul Cech on 19.04.15.
 */
public final class BusProvider {

    /**
     * Should be called in activities onStart() method
     *
     * @param _Object
     */
    public static void register(Object _Object) {
        EventBus.getDefault().register(_Object);
    }

    /**
     * Should be called in activities onStop() method
     *
     * @param _Object
     */
    public static void unregister(Object _Object) {
        EventBus.getDefault().unregister(_Object);
    }

    public static void post(Object _Event) {
        EventBus.getDefault().post(_Event);
    }

    // ----------------------------------------------------------------------------
    // Listener
    // ----------------------------------------------------------------------------

    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------

    private static final String LOG_TAG = BusProvider.class.getSimpleName();
}
