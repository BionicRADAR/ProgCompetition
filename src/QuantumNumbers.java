import java.util.Scanner;

public class QuantumNumbers {
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);

		while(true){
			System.out.print("Enter Three Integers: ");
			String input = s.nextLine();
			String ints[] = input.split(" ");
			int n = Integer.parseInt(ints[0]);
			int p = Integer.parseInt(ints[1]);
			int m = Integer.parseInt(ints[2]);
			
			if(n == 0 && p == 0 && m == 0)
				System.exit(0);
			
			if(n < 1){
				System.out.println("Invalid n");
			}
			else if(p < 0 || p > n-1){
				System.out.println("Invalid p");
			}
			else if(m < -p || m > p){
				System.out.println("Invalid m");
			}
			else{
				System.out.println("Valid");
			}
			
		}
	}

}