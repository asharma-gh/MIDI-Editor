package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * A runnable class that scrolls the view right 5 pixels every time it is run.
 */
public class ScrollRight implements Runnable {
  private GuiView<INote> view;

  public ScrollRight(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    this.view.scrollX(5);
  }
}
