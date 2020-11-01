package puresoft.org.climax.RecyclerViewClasses;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import puresoft.org.climax.POJO.POJONotification;
import puresoft.org.climax.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolderView> {

    ArrayList<POJONotification> arrayList;
    Context context;

    public RecyclerAdapter(Context context, ArrayList<POJONotification> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_notify, parent, false);
        MyHolderView holderView = new MyHolderView(v);
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder, int position) {

        POJONotification pojNotify=arrayList.get(position);
        holder.txtTitle.setText(pojNotify.title);
        holder.txtDescription.setText(pojNotify.description);
        holder.rxtCreated.setText(pojNotify.createdAt);

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription, rxtCreated;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            txtDescription = itemView.findViewById(R.id.description);
            rxtCreated = itemView.findViewById(R.id.createdAt);

        }
    }
}
