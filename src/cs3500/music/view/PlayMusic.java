package cs3500.music.view;


import cs3500.music.model.INote;

/**
 * A runnable class that resumes playback on a given view of the composition
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
