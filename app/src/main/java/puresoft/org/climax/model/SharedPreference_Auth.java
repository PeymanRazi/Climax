package puresoft.org.climax.model;

import android.content.Context;
import android.content.SharedPreferences;


import org.json.JSONException;
import org.json.JSONObject;

public class SharedPreference_Auth {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPreference_Auth(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("authentication data", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    //save auth json
    public boolean saveDetail(String json) {

        JSONObject jsonObject = null;
        boolean isJson = false;

        try {

            jsonObject = new JSONObject(json);

            //if json has requested authentication data then saving those
            if (jsonObject.getString("token_type").equals("bearer")) {

                isJson = true;

                editor.putString("token_type", jsonObject.getString("token_type"));
                editor.putString("access_token", jsonObject.getString("access_token"));
                editor.putString("refresh_token", jsonObject.getString("refresh_token"));
                //set time expire
                String expire = jsonObject.getString("expires_in");
                long end = System.currentTimeMillis() + (Integer.valueOf(expire) * 1000);
                editor.putString("expires_in", String.valueOf(end));
                editor.commit();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isJson;
    }

    public boolean IsExpired() {
        long expireTime = 0;
        long end = System.currentTimeMillis();
        boolean result = false;
        try {
            expireTime = Long.valueOf(pref.getString("expires_in", ""));
            result = expireTime > end;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public String getAuth() {

        return pref.getString("access_token", "");
    }

    public String getAuthRefresh() {

        return pref.getString("refresh_token", "");
    }

    public long getAuthTime() {

        long beTime = Long.valueOf(pref.getString("access_token", "")) - System.currentTimeMillis();


        return beTime;
    }


}
