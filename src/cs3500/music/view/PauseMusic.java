package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * A runnable class used to pause the given view of a composition
 */
public class PauseMusic implements Runnable{
  GuiView<INote> view;

  public PauseMusic(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    try {
      this.view.pausePlayback();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
