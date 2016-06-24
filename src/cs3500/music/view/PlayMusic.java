package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.view.MidiViewImpl;

/**
 * Created by nbuqu on 6/24/2016.
 */
public class PlayMusic implements Runnable {
  private GuiView<INote> view;

  public PlayMusic(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    try {
      this.view.resumePlayback();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
