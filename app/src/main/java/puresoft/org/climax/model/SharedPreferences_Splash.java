package puresoft.org.climax.model;

/**
 * Created by Peyman Razi on 22/07/2019.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferences_Splash {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public SharedPreferences_Splash(Context context){

        this.context=context;
        pref=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=pref.edit();

    }

    public void setFirstTimeLaunch(boolean isFirstTime) {

        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {

        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
