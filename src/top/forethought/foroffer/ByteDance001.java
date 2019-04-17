package top.forethought.foroffer;
// 计算硬币数


public class ByteDance001 {


    public static void main(String[] args) {
   System.out.println(new ByteDance001().findCointCount(1024-200));
    }

    int findCointCount(int money){
        if(money<=0){
            return 0;
        }
        int coint64=money/64;
        money-=coint64*64;
        int coint16=money/16;
        money-=coint16*16;
        int coint4= money/4;
        money-=coint4*4;


        return   coint64+coint16+coint4+money;


    }
}
