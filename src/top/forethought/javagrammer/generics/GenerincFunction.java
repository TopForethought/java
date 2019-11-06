package top.forethought.javagrammer.generics;

import java.util.Objects;
// 这是泛型类，不是泛型方法
public class GenerincFunction<T> {
    T []vals;

    public GenerincFunction(T[] vals) {
        this.vals = vals;
    }
    // 实现查找功能
    public boolean search(T t){
      if(Objects.isNull(vals)){
          return false;
      }
        for(T data:vals){
            if(data.equals(t)){
                return true;
            }
        }
        return false;
    }
}
