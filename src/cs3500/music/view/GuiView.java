package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import cs3500.music.model.MusicModelObserver;

/**
 * This interface represents additional functionality for the GUI view.
 *
 * @param <K> represents the type note used in the view
 */
public interface GuiView<K> extends ICompositionView<K> {

  /**
   * Updates the view such that it scrolls once the red line is off the screen
   */
  void updateScroll();


  int getWidth();

  void setKeyListener(KeyListener kl);

  void setMouseListener(MouseListener ml);

  void pausePlayback();

  void resumePlayback();

  /**
   * recomposes the gui based on the given notes
   */
  void recompose(MusicModelObserver<K> model, List<K> notes);


  void scrollX(int x);

  void scrollY(int y);

  void jumpToStart();

  void updatePause();

  /**
   * Sets the position of this line
   *
   * @param pos the new position of the red line
   */
  void linePosition(int pos);

}
