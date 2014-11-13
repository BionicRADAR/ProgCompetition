import java.util.Scanner;

public class QuantumNumbers {
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);

		while(true){
			System.out.println("Enter three integers: ");
			String input = s.nextLine();
			String ints[] = input.split(" ");
			int n = Integer.parseInt(ints[0]);
			int p = Integer.parseInt(ints[1]);
			int m = Integer.parseInt(ints[2]);
			
			if(n == 0 && p == 0 && m == 0)
				System.exit(0);
			
			if(n < 1){
				System.out.println("invalid n");
			}
			else if(p < 0 || p > n-1){
				System.out.println("invalid p");
			}
			else if(m < -p || m > p){
				System.out.println("invalid m");
			}
			else{
				System.out.println("valid");
			}
			
		}
	}

}