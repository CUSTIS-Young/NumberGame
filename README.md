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
