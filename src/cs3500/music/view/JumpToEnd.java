package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * A runnable class that jumps to the end of a given gui view.
 */
public class JumpToEnd implements Runnable {
  private GuiView<INote> view;

  public JumpToEnd(GuiView<INote> view) {
    this.view = view;
  }


  @Override
  public void run() {
    view.scrollX(this.view.getWidth());
  }
}
