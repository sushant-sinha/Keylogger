import java.util.*;
import java.lang.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int i=0;i<n;i++){
			int a=sc.nextInt();
			double fact=1;
			while(a!=1){
				fact*=a;
				--a;
			}
			System.out.println(fact);
		}
		
	}
}