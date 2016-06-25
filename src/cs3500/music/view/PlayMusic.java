package cs3500.music.view;


import cs3500.music.model.INote;

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
    System.out.println("FUCK");
    try {
      this.view.resumePlayback();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
