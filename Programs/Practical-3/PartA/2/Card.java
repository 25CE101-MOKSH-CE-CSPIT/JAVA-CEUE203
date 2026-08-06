import java.util.Objects;
public class Card {
    private String rank, suit;
    public Card(String r, String s) { rank = r; suit = s; }
    @Override public String toString() { return rank + " of " + suit; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card c = (Card) o;
        return rank.equals(c.rank) && suit.equals(c.suit);
    }
    @Override public int hashCode() { return Objects.hash(rank, suit); }
}
