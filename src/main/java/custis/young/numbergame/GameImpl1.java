package custis.young.numbergame;

/**
 * Здесь глубина стэка ограничена корнем квадратным из {@code upperBound},
 * что на дольших границах даст переполнение.
 * <p/>
 * Не хранит состояние, поэтому является и повторно используемым и потокобезопасным.
 */
public class GameImpl1 implements Game {

    @Override
    public int play(User user) {
        user_ = user;
        sqrt_max_ = (int) (Math.sqrt(user.getUpperBound()) + 1.0);
        return check_number_1(0);
    }

    private User user_;
    private int sqrt_max_;

    private int check_number_1(int n) {
        int answer = check_number_2(sqrt_max_ * n);
        if (answer != -1) {
            return answer;
        }
        if (n + 1 > sqrt_max_) {
            return -1;
        }

        return check_number_1(n + 1);
    }

    private int check_number_2(int n) {
        if (user_.theNumberIs(n)) {
            return n;
        }

        if ((n + 1) % sqrt_max_ == 0) {
            return -1;
        }

        return check_number_2(n + 1);
    }
}
