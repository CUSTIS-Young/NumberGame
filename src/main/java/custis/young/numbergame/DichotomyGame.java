package custis.young.numbergame;

/**
 * Эталонное решение
 */
public class DichotomyGame implements Game {

    private static final int UNDEFINED = -1;

    @Override
    public int play(User user) {
        return playDichotomy(user, 0, user.getUpperBound());
    }

    public int playDichotomy(User user, int left, int right) {
        assert (left <= right) : "left > right";
        if (left == right) {
            return user.theNumberIs(left) ? left : UNDEFINED;
        } else {
            final int middle = (left + right) >>> 1;
            final int leftBranchTry = playDichotomy(user, left, middle);
            return (leftBranchTry != UNDEFINED) ? leftBranchTry : playDichotomy(user, middle + 1, right);
        }
    }
}
