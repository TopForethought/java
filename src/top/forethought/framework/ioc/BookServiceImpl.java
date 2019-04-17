package top.forethought.framework.ioc;

public class BookServiceImpl implements BookService {
    @Override
    public void addBook() {
        System.out.println(" addBook @ BookServiceImpl");
    }
    public BookServiceImpl(){
        System.out.println("constructor BookServiceImpl");
    }
}
