package puresoft.org.climax.viewes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import puresoft.org.climax.R;


public class Input extends Fragment implements AdapterView.OnItemSelectedListener {


    View view;
    Spinner spinner;
    String[] spinnerArray={"Normal Input","reversal Input","Fiducial Input","Input Type"};
    ArrayAdapter<String> spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_input,container,false);

       //spinner setting operations
        SpinnerOperation();


        return view;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //spinner setting operations
    private void SpinnerOperation(){
        spinner=(Spinner)view.findViewById(R.id.spinner_menu);
        spinnerAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,spinnerArray);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }


}
