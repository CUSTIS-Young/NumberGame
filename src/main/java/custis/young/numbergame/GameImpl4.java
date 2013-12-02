package custis.young.numbergame;

/**
 * В целом, такое «десятичное» дерево сильно излишне. Принципиальной разнице в логорифмическом росте нет,
 * будь то по основанию 2 или по основанию 10.
 * <p/>
 * Условно уязвим к «недобросовестности пользователя» — уходит в очень долгую, хотя и конечную, рекурсию
 * (с колчичеством проверок около 10^10), но возвращается с правильным ответом.
 * <p/>
 * Из-з не совсем корректной проверки, если загаданное число равно верхней границе,
 * уйдет в такую же длогую рекурсию, но уже выйдет из нее с неправильным ответом.
 * <p/>
 * Задает совсем немного лишних вопросов, когда число уже отгадано.
 * <p/>
 * Не повторно используемо и не потокобезопасно.
 */
public class GameImpl4 implements Game {
    private int i = 0;
    private boolean found = false;

    @Override
    public int play(User user) {
        return play(user, 0);
    }

    public int play(User user, int level) {
        if (found) {
            return i;
        }
        if (level > 10) {
            playFinal(user);
            playFinal(user);
            playFinal(user);
            playFinal(user);
            playFinal(user);
            playFinal(user);
            playFinal(user);
            playFinal(user);
            playFinal(user);
            playFinal(user);
            return playFinal(user);
        }
        play(user, level + 1);
        play(user, level + 1);
        play(user, level + 1);
        play(user, level + 1);
        play(user, level + 1);
        play(user, level + 1);
        play(user, level + 1);
        play(user, level + 1);
        play(user, level + 1);
        return play(user, level + 1);
    }

    public int playFinal(User user) {
        if (i >= user.getUpperBound()) {
            return -1;
        }
        if (user.theNumberIs(i)) {
            found = true;
            return i;
        }
        i++;
        return -1;
    }
}
