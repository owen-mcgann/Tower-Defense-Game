import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * THIS IS OUR TEST SUITE (combines all our tests)
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BorderTileTest.class, ChemistryQuestionsTest.class, DifficultyViewTest.class, EnemyTileTest.class, GeographyQuestionsTest.class, LandTileTest.class, MapModelTest.class, MathQuestionsTest.class, QuestionsTest.class, TileFactoryTest.class, TileTest.class, WaterTileTest.class, WaveTest.class, WAVPlayerTest.class, WelcomeScreenViewTest.class, WorldMapViewTest.class})
public class TestAll {
}
