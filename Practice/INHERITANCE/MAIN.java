import java.util.*;

enum category
{
	SILVER,GOLD,PLATINUM
}
abstract class Ticket
{
	int c;
	String s = new String();
	abstract double calculateAmt();
	final void printBill()
	{
		System.out.printf("Category : %s\nTotal Bill : %.2f",s,calculateAmt());
	}
	Ticket(String s,int c)
	{
		this.c=c;
		this.s=s;
	}
}

class Silver extends Ticket
{
	@Override 
	double calculateAmt()
	{
		return this.c*120;
	}
	Silver(int c)
	{
		super("SILVER",c);
	}
}

class Gold extends Ticket
{
	@Override 
	double calculateAmt()
	{
		return this.c*200+50;
	}
	Gold(int c)
	{
		super("GOLD",c);
	}
}

class Platinum extends Ticket
{
	@Override 
	double calculateAmt()
	{
		return this.c*350*1.18;
	}
	Platinum(int c)
	{
		super("PLATINUM",c);
	}
}
public class MAIN 
{
	public static void main()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter no. of Tickets : ");
		int n=sc.nextInt();
		Ticket [] t = new Ticket[n];
		for(int i=0;i<n;i++)
		{
			//String s = new String();
			System.out.print("Enter Category : ");
			category s=category.valueOf(sc.next().toUpperCase());
			System.out.print("Enter Count : ");
			int c = sc.nextInt();
			switch(s)
			{
				case SILVER:
					t[i]=new Silver(c);
					t[i].printBill();
					break;
				case GOLD:
					t[i]=new Gold(c);
					t[i].printBill();
					break;
				case PLATINUM:
					t[i]=new Platinum(c);
					t[i].printBill();
					break;
			}
		}

	}
}
