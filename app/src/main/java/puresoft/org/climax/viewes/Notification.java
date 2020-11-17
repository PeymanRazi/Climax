package puresoft.org.climax.viewes;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import puresoft.org.climax.POJO.POJONotification;
import puresoft.org.climax.RecyclerViewClasses.RecyclerAdapter;
import puresoft.org.climax.ServerConnection.JsonReceiver;
import puresoft.org.climax.R;
import puresoft.org.climax.model.SharedPreference_Auth;

public class Notification extends Fragment {
    View view;
    String dataApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notification, container, false);

//        //get data api
//        GetDataApi();
//        //operating on received dataApi
//        OperationOnDataApi();

        return view;
    }


    //operating on received dataApi
    private void OperationOnDataApi() {
        BroadcastReceiver receiverUpdateDownload = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Get data from intent
                dataApi = intent.getStringExtra("key");
                //load data on recyclerview in OperationOnDataApi method
                RecyclerLoader();
//                Toast.makeText(getContext(), intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
            }
        };

        IntentFilter filter = new IntentFilter("STRING_ID_FOR_BRODCAST");
        getActivity().registerReceiver(receiverUpdateDownload, filter);
    }


    //get data api
    private void GetDataApi() {

//        try {
//            JsonReceiver jsonReceiver = new JsonReceiver((Activity) getContext(), "https://puresoftware.org/climax/en/api-v1/notifications.json");
//            Map<String, String> parser = new HashMap<String, String>();
//            String auth = new SharedPreference_Auth(getContext()).getAuth();
//            auth = "Bearer " + auth;
//            parser.put("Authorization", auth);
//            jsonReceiver.get(parser,);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    //load data on recyclerview in OperationOnDataApi method
    private void RecyclerLoader() {

        ArrayList<POJONotification> arrayList = new ArrayList<>();
        POJONotification pojoNotification = new POJONotification(getContext(),dataApi);

        arrayList.add(pojoNotification);

        RecyclerView recyclerView = view.findViewById(R.id.recycleNotification);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        RecyclerAdapter customAdapter = new RecyclerAdapter(getContext(), arrayList);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

}
