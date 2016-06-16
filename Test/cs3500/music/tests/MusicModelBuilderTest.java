package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelBuilder;
import cs3500.music.model.MusicModelImpl;

import static org.junit.Assert.*;

/**
 * Test class used to ensure correctness of the builder class.
 */
public class MusicModelBuilderTest {
  @Test
  public void testBuild() {
    assertEquals(true, MusicModelBuilder.build() instanceof MusicModel);
    // Since this is the only implementation currently supported...
    assertEquals(true, MusicModelBuilder.build() instanceof MusicModelImpl);
  }
}