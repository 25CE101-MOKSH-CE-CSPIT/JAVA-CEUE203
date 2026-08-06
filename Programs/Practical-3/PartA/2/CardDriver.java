public class CardDriver {
    public static void main(String[] a) {
        Card[] c = { new Card("Ace", "Spades"), new Card("King", "Hearts"), new Card("Ace", "Spades") };
        for (int i=0; i<c.length; i++) {
            for (int j=0; j<i; j++) {
                if (c[i].equals(c[j])) {
                    System.out.println("Duplicate found: " + c[i]);
                    break;
                }
            }
        }
    }
}
