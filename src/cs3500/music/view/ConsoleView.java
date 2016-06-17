package cs3500.music.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * The class for representing a model in the console.
 */
public class ConsoleView implements ICompositionView {
  private String view;

  public ConsoleView() {
    this.view = "";
  }

  @Override
  public void displayComposition() {
    System.out.println(this.view);
  }

  @Override
  public void buildComposition(MusicModelObserver<Note> model) {
    this.view = model.viewComposition();
  }
}
