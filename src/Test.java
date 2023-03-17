import java.util.*;


import calci.transformo.*;
//import calci.Setup;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		double a = 0;
//		System.out.println(1/0);
//		System.out.println(Double.isInfinite(1.0/0));
//
//		System.out.println(Double.isNaN(0.0/0));

		double ans = 1.2345678;
		int p = 6;
		System.out.println( Math.PI);
		System.out.println( Math.E
				);
		System.out.println( String.format(" %."+p+"f", ans));

		String c[] = "-i".split("i");
		try {
		System.out.println(c[0] + " 9 " + c[1]);
		}catch (Exception e) {
			System.out.println(c[0] + " 9 ");
		}

		Converto.findPostfix("((1+i)*i*(1-i))", 'A');
		List<String> pf = Computo.getPostfix();
		System.out.println(pf);
	
	}

}
