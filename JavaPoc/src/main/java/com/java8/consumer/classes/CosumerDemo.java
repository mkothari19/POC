package com.java8.consumer.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/*some primitive specializations of Consumer interface:

IntConsumer having one abstract method ' accept(int)' and one default method ' default IntConsumer andThen(IntConsumer after)'
DoubleConsumer having one abstract method ' accept(double)' and one default method ' default DoubleConsumer andThen(DoubleConsumer after)'
LongConsumer having one abstract method ' accept(long)' and one default method ' default LongConsumer andThen(LongConsumer after)'
*/
public class CosumerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int index=118 & 15;
		
		System.out.println(index);
		
		Consumer<Integer> con1=(n)->{System.out.println(n);};
		
		con1.accept(10);
		
		Consumer<List<Integer>> modifylist=(list)->{
		for(int i=0;i<list.size();i++) {
			list.set(i,2*list.get(i));
		}
			
		};
		
		Consumer<List<Integer>> displaylist=(list)->{list.forEach(n->System.out.println(n));};
		
		List<Integer> list=new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(4);
		
		modifylist.accept(list);
		displaylist.accept(list);
		
		//use of andThen method of Consumer default method
		modifylist.andThen(displaylist).accept(list);
		
		
	}

}
