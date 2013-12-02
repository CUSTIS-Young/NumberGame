package custis.young.numbergame;

/**
 * Собственно, победитель, наиболее близок к эталонному решению.
 * Удовлетворяет всем дополнительным критериям кроме повторной используемости.
 */
public class GameImpl5 implements Game {

    // Флаг, для прекращения перебора если число уже угадано
    private boolean flag = true;
    private int answer = -1;

    @Override
    public int play(User user) {
        binarySearch(user, 0, user.getUpperBound());
        return answer;
    }

    // Обычное бинарное дерево с прерыванием, когда число найдено
    void binarySearch(User user, int begin, int end) {
        if (end != begin && flag) {
            binarySearch(user, begin, (int) (((long) end + begin) / 2));
            binarySearch(user, (int) (((long) end + begin) / 2) + 1, end);
        } else if (user.theNumberIs(end)) {
            answer = end;
            flag = false;
//            System.out.print(answer);
        }
    }
}
