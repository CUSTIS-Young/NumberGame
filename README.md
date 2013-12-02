Игра в числа
============

Простая игра в числа выражается следующими интерфейсами:

    public interface User {
        int getUpperBound();
        boolean theNumberIs(int guess);
    }

    public interface Game {
        int play(User user);
    }

Вначале пользователь — `User` — задумывает число от `0` до `upperBound`; суть
игры — `Game.play()` — отгадать задуманное число, спрашивая пользователя
`theNumberIs`?, и вернуть угаданное значение. Для проверки написан следующий тест:

    public class GameTest {
        @Test
        public void game() {
            final SimpleUser user = new SimpleUser(1_000_000);
            final Game game = new GameImpl();
            final int answer = game.play(user);
            Assert.assertEquals(user.getTheNumber(), answer);
        }
    }

в котором используется простой эмулятор пользователя:

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

Напишите реализацию `Game` без использования циклов (`for`, `while`, `do..while`)
и инструкции `goto`, чтобы эта реализация стабильно проходила заданный `GameTest`
(Java 7, параметры JVM — по-умолчанию).

Решение
=======

Проанализируем задачу. При помощи цикла, она решалась бы тривальным перебором
от `0` до `upperBound` пока число не будет отгадано. Но, по условиям, циклы использовать нельзя,
тогда вспомним что любой цикл можно развернуть в рекурсию. Однако прямолинейная рекурсия в данном случае
даст переполнение стэка (в языке без обязательной оптимизации концевой рекурсии (tail call optimization),
которым является Java). Теперь ключевой момент — догадаться разложить прямую рекурсию в бинарное дерево.
При этом глубина стэка растет не линейно, а как логорифм `upperBound`, что вполне достаточно для любого
обозримого числа. Остается оптимальным и корректным способом реализовать такую рекурсию
(что совсем не тривиально, с учетом всех возможных подводных камней).

В итоге, «эталонное» решение выглядит следующим образом (см. `DichotomyGame`):

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

Разбор присланных решений
=========================

Было прислано пять вариантов решений, каждое из которых успешно проходило заданный тест.
Поэтому, дабы выделить одного победителя, не прибигая к слепому жребию, были сформулированы
дополнительные критерии.

Итак, решение должно (в порядке убывания важности):
 1) корретно обрабатывать граничный случай  `0`;
 2) корретно обрабатывать граничный случай  `Integer.MAX_VALUE`;
 3) быть устойчивым к «недобросовестности пользователя», когда он завбывает загаданое число;
 4) «не раздражать» пользователя лишними вопросами, т.е. не спрашивать про одно и тоже число несколько раз
    и не справшивать после того, как число точно отгадано;
 5) (желательно) быть повторно используемым (а еще лучше, потокобезопасным).

Эти критерии формализованы в откорректированом `GameTest`. «Эталонное» решение удовлетворяет им.
Присланные решения добавлены в проект (`GameImpl1...5`), без указания авторства.
По каждому в комментариях дается краткий разбор.

Надо отметить, что каждое решение по своему интересно, и заставляет поразмыслить над собой.
Но, победитель может быть только один, и им становится решение `GameImpl5`, которое удовлетворяет
всем дополнительным критериям кроме пятого, наменее важного.

Всем спасибо за участие!
