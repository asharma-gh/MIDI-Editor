package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * A runnable class that scrolls the view left 5 pixels every time it is run.
 */
public class ScrollLeft implements Runnable {
  private GuiView<INote> view;

  public ScrollLeft(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    this.view.scrollX(-5);
  }

}
