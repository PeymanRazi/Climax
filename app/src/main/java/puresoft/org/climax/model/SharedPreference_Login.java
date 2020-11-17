package puresoft.org.climax.model;

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

    //this method only save data that entered by user
    public void saveDetail(String email, String password) {


        editor.putString("email", email);
        editor.putString("password", password);
        editor.commit();

    }

    //this method used when user & pass been match to authorization of server
    public void saveDetailOriginal(String email, String password) {


        editor.putString("emailOriginal", email);
        editor.putString("passwordOriginal", password);
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

    public String emailOriginal() {

        String ema;
        ema = pref.getString("emailOriginal", "");
        return ema;
    }

    public String passwordOriginal() {

        String pas;
        pas = pref.getString("passwordOriginal", "");
        return pas;
    }

}
