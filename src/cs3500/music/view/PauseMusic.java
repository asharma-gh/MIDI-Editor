package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.view.MidiViewImpl;

/**
 * Created by nbuqu on 6/24/2016.
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
