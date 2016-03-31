package de.fh_erfurt.ai.tutorium.utils.permissions;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import de.fh_erfurt.ai.tutorium.Main;


/**
 * Created by Paul Cech on 30.09.15.
 */
public class PermissionRequest {

    // ----------------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------------

    /**
     * Checks for the calendar permission. If it is already granted the return value is true.
     * If not the request for  >= Android 6 devices is started and the user gets prompted if the
     * permission should be allowed or not.
     * @param _Activity
     * @return
     */
    public static boolean requestActivityCalendar(AppCompatActivity _Activity) {

        if (!PermissionUtils.hasSelfPermission(_Activity,
                new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR})) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(_Activity,
                    Manifest.permission.READ_CALENDAR)) {
                // TODO: Explain to the user why we need to read the calendar
            }

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(_Activity,
                    Manifest.permission.WRITE_CALENDAR)) {
                // TODO: Explain to the user why we need to write the calendar
            }

            ActivityCompat.requestPermissions(_Activity, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR},
                    PermissionRequestCodes.CALENDAR);

            return false;
        }

        return true;
    }

    /**
     * Checks for the calendar permission. If it is already granted the return value is true.
     * If not the request for  >= Android 6 devices is started and the user gets prompted if the
     * permission should be allowed or not.
     * @param _Fragment
     * @return
     */
    public static boolean requestFragmentCalendar(Fragment _Fragment) {

        if (!PermissionUtils.hasSelfPermission(Main.getAppContext(),
                new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR})) {

            // Should we show an explanation?
            if (_Fragment.shouldShowRequestPermissionRationale(Manifest.permission.READ_CALENDAR)) {
                // TODO: Explain to the user why we need to read the calendar
            }

            // Should we show an explanation?
            if (_Fragment.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CALENDAR)) {
                // TODO: Explain to the user why we need to write the calendar
            }

            _Fragment.requestPermissions(new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR},
                    PermissionRequestCodes.CALENDAR);

            return false;
        }

        return true;
    }

    public static boolean requestCallPhone(AppCompatActivity _Activity) {
        if (!PermissionUtils.hasSelfPermission(_Activity, Manifest.permission.CALL_PHONE)) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(_Activity,
                    Manifest.permission.CALL_PHONE)) {
                // TODO: Explain to the user why we need to use the internet
            }


            ActivityCompat.requestPermissions(_Activity, new String[]{Manifest.permission.CALL_PHONE},
                    PermissionRequestCodes.CALL_PHONE);

            return false;
        }

        return true;
    }

    public static boolean requestInternet(AppCompatActivity _Activity) {
        if (!PermissionUtils.hasSelfPermission(_Activity, Manifest.permission.INTERNET)) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(_Activity,
                    Manifest.permission.INTERNET)) {
                // TODO: Explain to the user why we need to use the internet
            }


            ActivityCompat.requestPermissions(_Activity, new String[]{Manifest.permission.INTERNET},
                    PermissionRequestCodes.INTERNET);

            return false;
        }

        return true;
    }

    public static boolean requestStorage(AppCompatActivity _Activity) {
        if (!PermissionUtils.hasSelfPermission(_Activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(_Activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // TODO: Explain to the user why we need to use the external storage
            }


            ActivityCompat.requestPermissions(
                    _Activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PermissionRequestCodes.STORAGE_RW);

            return false;
        }

        return true;
    }

    public static boolean requestLocation(AppCompatActivity _Activity) {
        if (!PermissionUtils.hasSelfPermission(_Activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})) {

//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(_Activity,
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//                // TODO: Explain to the user why we need to use the internet
//            }
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(_Activity,
//                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
//                // TODO: Explain to the user why we need to use the internet
//            }


            ActivityCompat.requestPermissions(_Activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PermissionRequestCodes.LOCATION);

            return false;
        }

        return true;
    }

    // ----------------------------------------------------------------------------
    // Private methods
    // ----------------------------------------------------------------------------


    // ----------------------------------------------------------------------------
    // Member
    // ----------------------------------------------------------------------------

    private static final String LOG_TAG = PermissionRequest.class.getSimpleName();
}
