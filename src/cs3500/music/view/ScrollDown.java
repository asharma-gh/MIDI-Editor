package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * A runnable class that scrolls the view down 5 pixels every time it is run.
 */
public class ScrollDown implements Runnable {
  private GuiView<INote> view;

  public ScrollDown(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    this.view.scrollY(5);
  }
}
