package simple;
import java.util.Scanner;
import java.util.InputMismatchException;
import util.Fib;
public class BS004_Fib {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int k = -1,n=0;
		try {
			n = sc.nextInt();
			k = sc.nextInt();
		}
		catch (InputMismatchException e){
			System.out.println("Input corrupted. Terminating.");
		}
		if (k!=-1)
			System.out.println(Fib.fib(n,k));
		sc.close();
	}
}
