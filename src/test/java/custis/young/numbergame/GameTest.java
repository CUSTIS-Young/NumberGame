package custis.young.numbergame;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void game() {

        final SimpleUser user = new SimpleUser(1_000_000);

        final Game game = new GameImpl();

        final int answer = game.play(user);

        Assert.assertEquals(user.getTheNumber(), answer);
    }
}
