package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.view.CompositionView;
import cs3500.music.view.GuiViewBuilder;
import cs3500.music.view.GuiViewFrame;

import static org.junit.Assert.*;

/**
 * Test for gui view builder.
 */
public class GuiViewBuilderTest {

  @Test
  public void testValidInput() {
    assertEquals(true, GuiViewBuilder.build("composite") instanceof CompositionView);
    assertEquals(true, GuiViewBuilder.build("visual") instanceof GuiViewFrame);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    GuiViewBuilder.build("javabeans");
  }
}