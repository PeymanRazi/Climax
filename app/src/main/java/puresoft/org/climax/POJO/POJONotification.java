package puresoft.org.climax.POJO;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

public class POJONotification {
    Context context;
    JSONArray jsonArray;
    JSONObject jsonObject;
    public String id = "0", read = "0", type = "0", title = "title", description = "description", createdAt = "created At", updatedAt = "";

    public POJONotification(Context context, String dataJson) {
        this.context = context;

//        if (!dataJson.equals("[]")) {
//            try {
//
//                id = jsonObject.getString("id");
//                read = jsonObject.getString("read");
//                type = jsonObject.getString("type");
//                title = jsonObject.getString("title");
//                description = jsonObject.getString("description");
//                createdAt = jsonObject.getString("created_At");
//                updatedAt = jsonObject.getString("updated_At");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
    }

}
