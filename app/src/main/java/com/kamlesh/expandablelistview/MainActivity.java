package com.kamlesh.expandablelistview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ParentAdapter.PassingData {

    RecyclerView recyclerViewParent;

    List<ParentData> parentDataList = new ArrayList<>();

    ParentAdapter parentAdapter;

    AppCompatCheckBox mCheckBoxMain;

    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewParent = findViewById(R.id.recyclerViewParent);
        mCheckBoxMain = findViewById(R.id.checkBoxMain);
        parentDataList = new ArrayList<ParentData>();
        setData();

        mCheckBoxMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked) {
                    isChecked = false;
                    mCheckBoxMain.setChecked(false);
                    for (int i = 0; i < parentDataList.size(); i++) {
                        List<ChildData> addChildData = new ArrayList<>();
                        for (int j = 0; j < parentDataList.get(i).getChildData().size(); j++) {
                            addChildData.add(new ChildData(parentDataList.get(i).getChildData().get(j).getTitle(), false, parentDataList.get(i).getChildData().get(j).getId()));
                        }
                        parentDataList.set(i, new ParentData(parentDataList.get(i).getParentTitle(), addChildData, parentDataList.get(i).getId(), false, parentDataList.get(i).isExpanded));
                    }
                } else {
                    isChecked = true;
                    mCheckBoxMain.setChecked(true);
                    for (int i = 0; i < parentDataList.size(); i++) {
                        List<ChildData> addChildData = new ArrayList<>();
                        for (int j = 0; j < parentDataList.get(i).getChildData().size(); j++) {
                            addChildData.add(new ChildData(parentDataList.get(i).getChildData().get(j).getTitle(), true, parentDataList.get(i).getChildData().get(j).getId()));
                        }
                        parentDataList.set(i, new ParentData(parentDataList.get(i).getParentTitle(), addChildData, parentDataList.get(i).getId(), true, parentDataList.get(i).isExpanded));
                    }
                }

                parentAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addData() {
        parentDataList = new ArrayList<ParentData>();
        List<ChildData> Movies = new ArrayList<>();
        Movies.add(new ChildData("ABCD", false, 1));
        Movies.add(new ChildData("FF7", false, 2));
        Movies.add(new ChildData("Dhamal", false, 3));
        Movies.add(new ChildData("Horror", false, 4));

        List<ChildData> Foods = new ArrayList<>();
        Foods.add(new ChildData("Pizza", false, 1));
        Foods.add(new ChildData("Hot-Dog", false, 2));
        Foods.add(new ChildData("Burger", false, 3));
        Foods.add(new ChildData("Chapati", false, 4));

        List<ChildData> Spots = new ArrayList<>();
        Spots.add(new ChildData("Cricket", false, 1));
        Spots.add(new ChildData("Football", false, 2));
        Spots.add(new ChildData("Hockey", false, 3));
        Spots.add(new ChildData("Tenish", false, 4));

        List<ChildData> County = new ArrayList<>();
        County.add(new ChildData("India", false, 1));
        County.add(new ChildData("Russia", false, 2));
        County.add(new ChildData("America", false, 3));
        County.add(new ChildData("Pakistan", false, 4));

        List<ChildData> State = new ArrayList<>();
        State.add(new ChildData("Gujarat", false, 1));
        State.add(new ChildData("Maharashtra", false, 2));
        State.add(new ChildData("Uttar Pradesh", false, 3));
        State.add(new ChildData("Bihar", false, 4));

        List<ChildData> City = new ArrayList<>();
        City.add(new ChildData("Ahmedabad", false, 1));
        City.add(new ChildData("Rajkot", false, 2));
        City.add(new ChildData("Surat", false, 3));
        City.add(new ChildData("Baroda", false, 4));


      /*  parentDataList.add(new ParentData("Movies", Movies, 1));
        parentDataList.add(new ParentData("Foods", Foods, 2));
        parentDataList.add(new ParentData("Spots", Spots, 3));
        parentDataList.add(new ParentData("County", County, 4));
        parentDataList.add(new ParentData("State", State, 5));
        parentDataList.add(new ParentData("City", City, 6));*/

        parentDataList.add(new ParentData("Movies", Movies, 1, false, false));
        parentDataList.add(new ParentData("Foods", Foods, 2, false, false));
        parentDataList.add(new ParentData("Spots", Spots, 3, false, false));
        parentDataList.add(new ParentData("County", County, 4, false, false));
        parentDataList.add(new ParentData("State", State, 5, false, false));
        parentDataList.add(new ParentData("City", City, 6, false, false));
    }

    private void setData() {
        addData();
        parentAdapter = new ParentAdapter(parentDataList, MainActivity.this, this);
        recyclerViewParent.setAdapter(parentAdapter);
    }

    @Override
    public void SendData(List<ParentData> parentData, String type, int position) {
        Log.e("DATA", String.valueOf(parentData));
        Log.e("DATA", type);
        int mainCount = 0;
        List<ChildData> childData = parentData.get(position).getChildData();
        List<ChildData> addChildData = new ArrayList<>();
        if (type.equals("parent")) {
            if (parentData.get(position).isChecked) {
                for (int j = 0; j < childData.size(); j++) {
                    addChildData.add(new ChildData(childData.get(j).getTitle(), true, childData.get(j).getId()));
                }
            } else {
                for (int j = 0; j < childData.size(); j++) {
                    addChildData.add(new ChildData(childData.get(j).getTitle(), false, childData.get(j).getId()));
                }
            }
            parentDataList.set(position, new ParentData(parentData.get(position).getParentTitle(), addChildData, parentData.get(position).getId(), parentData.get(position).getChecked(), parentData.get(position).isExpanded));
        } else {
            int count = 0;
            for (int j = 0; j < childData.size(); j++) {
                if (childData.get(j).getChecked()) {
                    count++;
                }
            }
            if (count == childData.size()) {
                parentDataList.set(position, new ParentData(parentData.get(position).getParentTitle(), childData, parentData.get(position).getId(), true, parentData.get(position).isExpanded));
            } else {
                parentDataList.set(position, new ParentData(parentData.get(position).getParentTitle(), childData, parentData.get(position).getId(), false, parentData.get(position).isExpanded));
            }
        }
        parentAdapter.notifyDataSetChanged();

        for (int j = 0; j < parentDataList.size(); j++) {
            if (parentDataList.get(j).getChecked()) {
                mainCount++;
            }
        }
        if (mainCount == parentDataList.size()) {
            isChecked = true;
            mCheckBoxMain.setChecked(true);
        } else {
            isChecked = false;
            mCheckBoxMain.setChecked(false);
        }
    }

    @Override
    public void ChildData(List<ParentData> parentData, String type, List<ChildData> childData, int position) {
        int count = 0;
        int mainCount = 0;
        for (int i = 0; i < childData.size(); i++) {
            if (childData.get(i).getChecked()) {
                count++;
            }
        }
        if (count == childData.size()) {
            parentDataList.set(position, new ParentData(parentData.get(position).getParentTitle(), childData, parentData.get(position).getId(), parentData.get(position).getChecked(), parentData.get(position).isExpanded));
        } else {
            parentDataList.set(position, new ParentData(parentData.get(position).getParentTitle(), childData, parentData.get(position).getId(), parentData.get(position).getChecked(), parentData.get(position).isExpanded));
        }
        parentAdapter.notifyDataSetChanged();
        for (int j = 0; j < parentDataList.size(); j++) {
            if (parentDataList.get(j).getChecked()) {
                mainCount++;
            }
        }
        if (mainCount == parentDataList.size()) {
            isChecked = true;
            mCheckBoxMain.setChecked(true);
        } else {
            isChecked = false;
            mCheckBoxMain.setChecked(false);
        }
    }
}