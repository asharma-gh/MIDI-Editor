package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * Created by nbuqu on 6/24/2016.
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
