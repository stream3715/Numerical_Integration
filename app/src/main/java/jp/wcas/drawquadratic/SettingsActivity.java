package jp.wcas.drawquadratic;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by strea on 2016/11/17.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
            .replace(android.R.id.content, new SimplePreferenceFragment())
            .commit();
    }
}
