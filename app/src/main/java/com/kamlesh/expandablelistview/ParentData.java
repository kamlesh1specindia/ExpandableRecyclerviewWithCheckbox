package com.kamlesh.expandablelistview;

import java.util.List;

public class ParentData {

    String parentTitle;

    List<ChildData> childData;
    Boolean isExpanded = false;
    Boolean isChecked = false;

    public ParentData(String parentTitle, List<ChildData> childData, int id,boolean isChecked,boolean isExpanded) {
        this.parentTitle = parentTitle;
        this.childData = childData;
        this.isExpanded = isExpanded;
        this.isChecked = isChecked;
        this.id = id;
    }

    public List<ChildData> getChildData() {
        return childData;
    }

    public void setChildData(List<ChildData> childData) {
        this.childData = childData;
    }

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }
}
