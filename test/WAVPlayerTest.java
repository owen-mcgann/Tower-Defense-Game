import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WAVPlayerTest {
    private WAVPlayer player;

    @Before
    public void setup(){
        player = new WAVPlayer("Audio/TE_BGMUSIC.wav");
        player.setVolume(0.1f);
        player.play();
    }

    @Test
    public void getVolume() {
        float vol = player.getVolume();
        float expected = -71.39794f;
        Assert.assertEquals(expected, vol, expected-vol);
    }
}