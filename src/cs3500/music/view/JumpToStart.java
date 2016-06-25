package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * A runnable class that jumps to the beginning of a given gui view.
 */
public class JumpToStart implements Runnable {
  private GuiView<INote> view;

  public JumpToStart(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    System.out.println("fuck");
    view.jumpToStart();
  }
}
