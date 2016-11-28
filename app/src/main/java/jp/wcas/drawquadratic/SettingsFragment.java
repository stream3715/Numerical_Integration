package jp.wcas.drawquadratic;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by strea on 2016/11/17.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
