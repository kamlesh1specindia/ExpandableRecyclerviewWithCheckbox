package com.kamlesh.expandablelistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> {
    private final List<ParentData> parentList;

    private final Context mContext;

    PassingData passingData;


    public ParentAdapter(List<ParentData> parentList, Context context, PassingData passingData) {
        this.mContext = context;
        this.parentList = parentList;
        this.passingData = passingData;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_parent, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewParentHeader.setText(parentList.get(position).getParentTitle());
        holder.checkBoxParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentList.get(position).setChecked(!parentList.get(position).getChecked());
                Toast.makeText(mContext, String.valueOf(1), Toast.LENGTH_LONG).show();
                passingData.SendData(parentList, "parent", position);
            }
        });
        /*holder.checkBoxParent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parentList.get(position).setChecked(!parentList.get(position).getChecked());
                Toast.makeText(mContext, String.valueOf(isChecked), Toast.LENGTH_LONG).show();
                passingData.SendData(parentList);
            }
        });*/

        holder.textViewParentHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentList.get(position).setExpanded(!parentList.get(position).isExpanded);
                if (parentList.get(position).isExpanded) {
                    holder.imageViewArrow.setImageResource(R.drawable.ic_arrow_drop_up);
                    holder.recyclerViewChild.setVisibility(View.VISIBLE);
                } else {
                    holder.imageViewArrow.setImageResource(R.drawable.ic_arrow_drop_down);
                    holder.recyclerViewChild.setVisibility(View.GONE);
                }
            }
        });

        holder.imageViewArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentList.get(position).setExpanded(!parentList.get(position).isExpanded);
                if (parentList.get(position).isExpanded) {
                    holder.imageViewArrow.setImageResource(R.drawable.ic_arrow_drop_up);
                    holder.recyclerViewChild.setVisibility(View.VISIBLE);
                } else {
                    holder.imageViewArrow.setImageResource(R.drawable.ic_arrow_drop_down);
                    holder.recyclerViewChild.setVisibility(View.GONE);
                }
            }
        });

        if (parentList.get(position).isExpanded) {
            holder.recyclerViewChild.setVisibility(View.VISIBLE);
        } else {
            holder.recyclerViewChild.setVisibility(View.GONE);
        }


        holder.checkBoxParent.setChecked(parentList.get(position).getChecked());
        ChildAdapter childAdapter = new ChildAdapter(parentList.get(position).getChildData(), mContext, new ChildAdapter.ChildItemClickListener() {
            @Override
            public void ChildClick(List<ChildData> childData, String type) {
                int count = 0;
                for (int i = 0; i < childData.size(); i++) {
                    if (childData.get(i).getChecked()) {
                        count++;
                    }
                }
                if (count == childData.size()) {
                    parentList.get(position).setChecked(true);
                }
                else {
                    parentList.get(position).setChecked(false);
                }
                passingData.SendData(parentList, "child", position);
            }
        });
        holder.recyclerViewChild.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewParentHeader;
        LinearLayoutCompat linearLayoutParentList;

        AppCompatImageView imageViewArrow;

        AppCompatCheckBox checkBoxParent;

        ConstraintLayout clParent;

        RecyclerView recyclerViewChild;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewParentHeader = itemView.findViewById(R.id.textViewParentHeader);
            linearLayoutParentList = itemView.findViewById(R.id.linearLayoutParentList);
            imageViewArrow = itemView.findViewById(R.id.imageViewArrow);
            checkBoxParent = itemView.findViewById(R.id.checkBoxParent);
            clParent = itemView.findViewById(R.id.clParent);
            recyclerViewChild = itemView.findViewById(R.id.recyclerViewChild);
        }
    }

    interface PassingData {
        void SendData(List<ParentData> parentData, String type, int position);

        void ChildData(List<ParentData> parentData, String type, List<ChildData> childData, int position);
    }

}
