package top.forethought.javagrammer.clone;

// 据说:浅拷贝,只会拷贝对象内部属性,以及引用,但是不会拷贝引用所指向的对象
//   深拷贝:也会拷贝引用指向的对象
public class People implements Cloneable  {

    private IDCard idCard;

//    @Override
//    protected Object clone() {
//       Object ob= null;
//       try{
//        ob=   super.clone();
//       }catch ( CloneNotSupportedException e){
//           e.printStackTrace();
//       }
//
//
//        return ob;
//    }
@Override
   protected Object clone() {
    People obj=null;
    try{
        obj= (People) super.clone();
        obj.idCard= (IDCard) idCard.clone();//将引用指向的对象也拷贝一份
    }catch (CloneNotSupportedException e){
        e.printStackTrace();
    }
    return obj;

   }

    public People(IDCard idCard) {
        this.idCard = idCard;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
    }

    public static void main(String[] args)  {
        IDCard card=new IDCard("1234","张三");
        People origin=new People(card);
        People clone=(People)origin.clone();
        card.setName("李白");
        System.out.println(clone.getIdCard().getName());
        System.out.println(card.toString());

    }
}
