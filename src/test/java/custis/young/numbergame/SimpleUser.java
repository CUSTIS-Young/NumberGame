package custis.young.numbergame;

public class SimpleUser implements User {

    private final int upperBound;
    private final int theNumber;
    private long askCount = 0;

    public SimpleUser(int upperBound) {
        this(upperBound, (int) (Math.random() * upperBound));
    }

    public SimpleUser(int upperBound, int theNumber) {
        this.upperBound = upperBound;
        this.theNumber = theNumber;
    }

    @Override
    public int getUpperBound() {
        return upperBound;
    }

    public int getTheNumber() {
        return theNumber;
    }

    public long getAskCount() {
        return askCount;
    }

    @Override
    public boolean theNumberIs(int guess) {
        askCount++;
        return (guess == theNumber);
    }
}
