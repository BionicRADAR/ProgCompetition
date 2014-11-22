import java.util.Scanner;


public class RationalNumbers {
	private static Rational f1, f2, f3;
	private static char op;

	public static void main(String args[]){
		Scanner s = new Scanner(System.in);

		while(true){
			String line = s.nextLine();
			String arguments[] = line.split(" ");
			f1 = new Rational(arguments[0]);
			op = arguments[1].charAt(0);
			f2 = new Rational(arguments[2]);

			if(f1.n == 0 && f1.d == 0 && op == '+' && f2.n == 0 && f2.d == 0){
				System.exit(0);
			}	
			
			commonDenominator();
			switch(op) {
				case '+' : f3 = new Rational(f1.n + f2.n, f1.d);
							break;
				case '-' : f3 = new Rational(f1.n - f2.n, f1.d);
							break;
				case '*' : f3 = new Rational(f1.n * f2.n, f1.d * f1.d);
							break;
				case '/' : 	f3 = new Rational(f1.n * f2.d, f2.n * f1.d);
							break;
			}
			simplify();
			printAnswer();
		}
	}

	private static void simplify(){
		int num = (f3.n * f3.n < f3.d * f3.d) ? f3.n : f3.d;
		
		for(int i = num; i > 1; i--){
			if(f3.n % i == 0 && f3.d % i == 0){
				f3.n = f3.n / i;
				f3.d = f3.d / i;
			}
		}
	}
	
	private static void printAnswer(){
		if(f3.d < 0){
			f3.n = -f3.n;
			f3.d = -f3.d;
		}
		if(f3.d == 1){
			System.out.println(f3.n);
		}else{
			System.out.println(f3);
		}
	}
	
	//Finds the simplist common denominator - will be simplified later 
	private static void commonDenominator(){
		if(f1.d != f2.d){
			f1.n = f1.n * f2.d;
			f1.d = f1.d * f2.d;
			f2.n = f2.n * f1.d;
			f2.d = f2.d * f1.d;
		}
	}

	static class Rational{
		int n, d;

		Rational(int numerator, int denominator){
			n = numerator;
			d = denominator;
		}

		Rational(String fraction){
			String f[] = fraction.split("/");
			n = Integer.parseInt(f[0]);
			d = Integer.parseInt(f[1]);
		}

		public String toString(){
			return n + "/" + d;
		}
	}
}
