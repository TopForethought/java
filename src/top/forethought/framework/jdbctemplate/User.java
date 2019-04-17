package top.forethought.framework.jdbctemplate;

public class User {
    private int id;
    private String name;
    private String sex;
    private String description;

    public User() {
    }

    public User(String name, String sex, String description) {
        this.name = name;
        this.sex = sex;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
