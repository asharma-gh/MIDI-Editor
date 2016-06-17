package cs3500.music;

import cs3500.music.model.*;
import cs3500.music.util.MusicReader;
import cs3500.music.view.*;

import java.io.*;
import javax.sound.midi.InvalidMidiDataException;

/**
 * A class used for playing the midiview / displaying gui view
 */
public class MusicEditor {
  /**
   * The main() event ...
   * @param args should take two arguments, first the desired file, second the desired view type.
   * @throws IOException
   * @throws InvalidMidiDataException
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    //GuiViewFrame view = new GuiViewFrame();
    ICompositionView view = ViewBuilder.build(args[1]);
    FileInputStream file = new FileInputStream(args[0]);
    MusicModel<Note> model = MusicReader.parseFile(new BufferedReader(
            new InputStreamReader(file)),
            new MusicModelImpl.Builder());
    view.buildComposition(model);
    view.displayComposition();
  }
}
