package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * This interface represents additional functionality for the GUI view.
 *
 * @param <K> represents the type note used in the view
 */
public interface GuiView<K> extends ICompositionView<K> {

  /**
   * Updates the view such that it pans based on the given value
   */
  void updateHorizontalScroll(int pos);

  void updateLine(int pos);

  int getWidth();

  void setKeyListener(KeyListener kl);

  void setMouseListener(MouseListener ml);

  void pausePlayback();

  void resumePlayback();
}
