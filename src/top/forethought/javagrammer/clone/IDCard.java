package top.forethought.javagrammer.clone;

public class IDCard  implements Cloneable{
    private String No;
    private String name;

    public IDCard(String no, String name) {
        No = no;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getName() {
        return name;
    }

}
