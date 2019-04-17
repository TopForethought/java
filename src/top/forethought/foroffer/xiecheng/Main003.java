package top.forethought.foroffer.xiecheng;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main003 {

	// 输入是 / 分隔的路径
	// 输出是该路径出现的次数
	//使用map,key 为路径,val 是出现次数
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (in.hasNextLine()) {
			Map<String,Integer> map=new HashMap<>();
			//int count=Integer.parseInt(in.nextLine());
			String [] input=in.nextLine().split(" ");
			int count=Integer.parseInt(input[0]);
			for(int i=1;i<=count && i<input.length;i++){
				String curr=input[i];
				solution(curr,map);
				System.out.println(getResult(curr,map));
			}


		}
	}

	// 如果是出现过,将计数+1
	// 如果没出现过,次数记为1
	public static void solution(String input,Map<String,Integer> map){
		int count=1;
		 if(map.containsKey(input)){
	      count+=map.get(input);
	 	 }
	 	map.put(input,count);
	}
	//拼凑结果
	// 如果是1层  返回 1
	// 两层 返回 11
	public static  String getResult(String input,Map<String,Integer> map){
		String [] array=input.split("/");
       if(array.length<=2){
       	return "1";
	   }
//	   // "/l/m/
//	   if(array.length<=3){
//       	return "11";
//	   }
		// 三层及以上
		StringBuilder result=new StringBuilder();
	   result.append("1");
       for(int i=1;i<array.length-2;i++){
	   	 result.append(map.get(input));
	   }
	   result.append("1");

		return  result.toString();
	}
}
