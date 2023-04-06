package com.kamlesh.expandablelistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ParentViewHolder> {

    private final List<ChildData> childDataList;

    private final Context mContext;

    private final ChildItemClickListener childItemClickListener;

    public ChildAdapter(List<ChildData> childDataList, Context context, ChildItemClickListener childItemClickListener) {
        this.mContext = context;
        this.childDataList = childDataList;
        this.childItemClickListener = childItemClickListener;
    }


    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_child, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewChildHeader.setText(childDataList.get(position).getTitle());
        holder.checkBoxChild.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               /* childDataList.get(position).setChecked(!childDataList.get(position).getChecked());
                childItem.ChildClick(childDataList);
                Toast.makeText(mContext, String.valueOf(isChecked), Toast.LENGTH_LONG).show();*/
            }
        });

        holder.checkBoxChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childDataList.get(position).setChecked(!childDataList.get(position).getChecked());
                childItemClickListener.ChildClick(childDataList, "child");
            }
        });

        holder.checkBoxChild.setChecked(childDataList.get(position).getChecked());

    }

    @Override
    public int getItemCount() {
        return childDataList.size();
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewChildHeader;
        LinearLayoutCompat linearLayoutChildList;

        AppCompatCheckBox checkBoxChild;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewChildHeader = itemView.findViewById(R.id.textViewChildHeader);
            linearLayoutChildList = itemView.findViewById(R.id.linearLayoutChildList);
            checkBoxChild = itemView.findViewById(R.id.checkBoxChild);
        }
    }

    interface ChildItemClickListener {
        void ChildClick(List<ChildData> childData, String type);
    }
}
