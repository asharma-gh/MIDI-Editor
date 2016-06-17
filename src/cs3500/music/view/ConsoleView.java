package cs3500.music.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * Created by nbuqu on 6/17/2016.
 */
public class ConsoleView implements ICompositionView<MusicModel<Note>> {
  private String view;

  public ConsoleView() {
    this.view = "";
  }

  @Override
  public void playComposition() {
    System.out.println(this.view);
  }

  @Override
  public void buildComposition(MusicModel<Note> comp) {
    this.view = comp.viewComposition();
  }
}
