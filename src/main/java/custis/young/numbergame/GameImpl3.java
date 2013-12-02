package custis.young.numbergame;

/**
 * Очень красивое решение с проверкой в каждом узле бинарного дерева, а не только в теримнальных.
 * <p/>
 * Но к сожалению, решение не проверяет ноль и уязвимо к арифметическому переполнению,
 * что на больших границах приводит к переполнению стэка. (И то и другое поправимо
 * введеним дополнительных проверок.)
 * <p/>
 * Еще в плюс то, что решение повторно используемо, а если бы не хранило {@code currentUser}
 * (что лекго сделать), то было бы и потокобезопасным.
 * <p/>
 * Поскольку данное решение перебирает числе не подряд от нуля до ответа,
 * то может задать пользовотелю как меньше чем {@code (answer + 1)} так и больше вопросов,
 * в зависисмости от загаданного сичла, но в целом, лишних вопросов не задает
 * (т.е. не спрашивает два раза про одно и тоже число и не спрашивает, если число уже отгадано.)
 */
public class GameImpl3 implements Game {

    User currentUser;

    @Override
    public int play(User user) {
        currentUser = user;
        return tryGuessNumber(1);
    }

    private int tryGuessNumber(int number) {

        if (number > currentUser.getUpperBound()) {
            return -1;
        }

        if (currentUser.theNumberIs(number)) {
            return number;
        }

        int answer = tryGuessNumber(2 * number);
        if (answer == -1) {
            answer = tryGuessNumber(2 * number + 1);
        }
        return answer;
    }
}
