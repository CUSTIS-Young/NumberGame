package custis.young.numbergame;

public class SimpleUser implements User {

    private final int theNumber;
    private final int upperBound;

    public SimpleUser(int upperBound) {
        this.upperBound = upperBound;
        this.theNumber = (int) (Math.random() * upperBound);
    }

    public int getTheNumber() {
        return theNumber;
    }

    @Override
    public int getUpperBound() {
        return upperBound;
    }

    @Override
    public boolean theNumberIs(int guess) {
        return (theNumber == guess);
    }
}
