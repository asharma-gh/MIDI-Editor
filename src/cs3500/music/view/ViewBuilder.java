package cs3500.music.view;

import java.util.IllegalFormatException;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * A View builder for the various types of composition views.
 */
public class ViewBuilder {

  ViewBuilder() {

  }

  public static ICompositionView<MusicModel<Note>> build(String view) {
    if (view.equals("console")) {
      return new ConsoleView();
    }
    else if (view.equals("visual")) {
      return null;
    }
    else if (view.equals("midi")) {
      return new MidiViewImpl();
    }
    else {
      throw new IllegalArgumentException("Unsupported view implementation");
    }
  }
}
