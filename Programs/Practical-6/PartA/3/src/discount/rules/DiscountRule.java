package discount.rules;

@FunctionalInterface
public interface DiscountRule {
    double apply(double p);
}
