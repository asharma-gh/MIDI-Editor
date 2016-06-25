package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * Created by nbuqu on 6/24/2016.
 */
public class JumpToEnd implements Runnable {
  private GuiView<INote> view;

  public JumpToEnd(GuiView<INote> view) {
    this.view = view;
  }


  @Override
  public void run() {
    view.updateHorizontalScroll(this.view.getWidth());
  }
}
