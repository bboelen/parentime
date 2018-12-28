package be.atemi.decision.parentime;

/**
 * In mathematics, a ratio is a relationship between two numbers indicating how many times the first number contains
 * the second. For example, if a bowl of fruit contains eight oranges and six lemons, then the ratio of oranges to
 * lemons is eight to six (that is, 8:6, which is equivalent to the ratio 4:3). Similarly, the ratio of lemons to
 * oranges is 6:8 (or 3:4) and the ratio of oranges to the total amount of fruit is 8:14 (or 4:7).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Ratio">Ratio</a>
 *
 * In the context of alternate custody, the standard ratios are: 50:50, 60:40, 70:30 and 80:20.
 *
 * @author bb
 */
public class CustodyRatio {

    private int a;
    private int b;

    public CustodyRatio(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return new StringBuffer(a).append(":").append(b).toString();
    }

    /**
     * 50:50 - Enables schedules where each parent has the child 50% of the time.
     */
    public static CustodyRatio FIFTY_FIFTY = new CustodyRatio(50, 50);

    /**
     * 60:40 - Enables schedules where one parent has the child for 60% of the time and the other parent has the
     * child for 40%.
     */
    public static CustodyRatio SIXTY_FORTY = new CustodyRatio(60, 40);

    /**
     * 70:30 - Enables schedules where one parent has 70% of the time with the child and the other parent has 30%
     * of the time.
     */
    public static CustodyRatio SEVENTY_THIRTY = new CustodyRatio(70, 30);

    /**
     * 80:20 - Enables schedules where one parent has 80% of the time with the child and the other parent has 20%
     * of the time.
     */
    public static CustodyRatio EIGHTY_TWENTY = new CustodyRatio(80, 20);
}
