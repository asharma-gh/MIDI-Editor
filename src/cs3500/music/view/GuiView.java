package cs3500.music.view;

/**
 * This interface represents additional functionality for the GUI view.
 *
 * @param <K> represents the type note used in the view
 */
public interface GuiView<K> extends ICompositionView<K> {

  /**
   * Updates the view such that it pans based on the given value
   */
  void updateHorizontalScroll();
}
