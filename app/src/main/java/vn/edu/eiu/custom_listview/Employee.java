package vn.edu.eiu.custom_listview;

/**
 * Created by Admin on 5/3/2017.
 */

public class Employee {
    private String id;
    private String name;
    private boolean gender;

    public Employee(String id, String name, boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Employee(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return getId() + " " + getName();
    }

}
