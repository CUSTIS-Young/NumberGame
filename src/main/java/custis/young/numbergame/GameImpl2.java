package custis.young.numbergame;

/**
 * Элегантное решение со складывание половинок. Но к сожалению...
 * <p/>
 * Проверяет границы интервалов в каждом узле дерева, задавая такми образом много лишних вопросов.
 * <p/>
 * Уязвим к «недобросовестности пользователя» — возвращает ноль в этом случае.
 */
public class GameImpl2 implements Game {

    private boolean found;
    private User user;

    @Override
    public int play(User user) {
        this.user = user;
        return lookup(0, user.getUpperBound());
    }

    private int lookup(int left, int right) {
        if (found) {
            return 0;
        }
        if (user.theNumberIs(left)) {
            found = true;
            return left;
        }
        if (user.theNumberIs(right)) {
            found = true;
            return right;
        }
        if (left == right || left + 1 == right) {
            return 0;
        }

        int half = (right - left) / 2;

        return lookup(left, left + half) + lookup(left + half + 1, right);
    }
}
