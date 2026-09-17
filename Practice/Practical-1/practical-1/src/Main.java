import java.util.Scanner;

enum coin
{
   ONE,TWO,FIVE,TEN
};
class exception
{

}

class snacks
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t=0;
        do
        {
            System.out.print("Enter the Coin : ");
            try {
                coin c = coin.valueOf(sc.next().toUpperCase());
                int v = switch (c) {
                    case ONE -> 1;
                    case TWO -> 2;
                    case FIVE -> 5;
                    case TEN -> 10;
                    default -> 0;
                };

                t+=v;
            }
            catch(Exception e)
            {
                System.out.println("Invalid Entry Alert!");
            }
            System.out.println("Total : "+t);
        }while(t<15);
        System.out.println("Change Returned : " + (t-15) );
    }
}