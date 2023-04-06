package com.kamlesh.expandablelistview;

public class ChildData {

    String title;
    Boolean isChecked;

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChildData(String title, Boolean isChecked, int id) {
        this.title = title;
        this.isChecked = isChecked;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
