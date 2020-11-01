package puresoft.org.climax.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference_Login {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPreference_Login(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("login data", Context.MODE_PRIVATE);
        editor = pref.edit();
    }


    public void saveDetail(String email, String password) {


        editor.putString("email", email);
        editor.putString("password", password);
        editor.commit();

    }

    public void removeDetails(){
        editor.clear();
        editor.commit();
    }

    public Boolean IsSaved() {

        return !pref.getString("email", "").isEmpty();
    }


    public String email() {

        String ema;
        ema = pref.getString("email", "");
        return ema;
    }

    public String password() {

        String pas;
        pas = pref.getString("password", "");
        return pas;
    }

}
