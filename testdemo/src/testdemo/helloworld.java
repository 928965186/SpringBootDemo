package testdemo;

import java.util.Iterator;

public class helloworld {
	public static void main(String[] args) {
		
		String i=",1,2,3,4";
		int i2=0;
		String[] split = i.split(",");
		for (String string : split) {
			
		}

		for (int j = 1; j < split.length; j++) {
			System.out.println("s"+split[j]);
			if (split[0]!=null&&split[0]!="") {
				int i1=Integer.valueOf(split[j]);
				i2+=i1;
			}
		}
		
		System.out.println(i2);
	}

}
