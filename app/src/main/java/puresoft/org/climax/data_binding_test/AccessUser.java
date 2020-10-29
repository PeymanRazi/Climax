package puresoft.org.climax.data_binding_test;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessUser {

    Context context;
    public String notification, input, output, stuff, warehouse, company, customer, driver, employee, user, setting;

    public AccessUser(Context context, String dataJson) {

        this.context = context;

        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(dataJson);

            notification = jsonObject.getString("notification");
            input = jsonObject.getString("input");
            output = jsonObject.getString("output");
            stuff = jsonObject.getString("stuff");
            warehouse = jsonObject.getString("warehouse");
            company = jsonObject.getString("company");
            customer = jsonObject.getString("customer");
            driver = jsonObject.getString("driver");
            employee = jsonObject.getString("employee");
            user = jsonObject.getString("user");
            setting = jsonObject.getString("setting");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
