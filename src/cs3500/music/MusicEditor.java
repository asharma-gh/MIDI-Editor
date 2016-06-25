package cs3500.music;

import cs3500.music.controller.CompositeController;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.controller.ICompositionController;
import cs3500.music.mock.MidiMockTracer;
import cs3500.music.model.*;
import cs3500.music.mock.MidiMockDevice;
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
   *
   * @param args should take two arguments, first the desired file, second the desired view type.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    ICompositionView<Note> view = ViewBuilder.build(args[1]);
    GuiView<INote> viewgui = new CompositionView(new GuiViewFrame(), new MidiViewImpl());
    GuiView<INote> views = new GuiViewFrame();
    FileInputStream file = new FileInputStream(args[0]);
    MusicModel<INote> model = MusicReader.parseFile(new BufferedReader(
                    new InputStreamReader(file)),
            new MusicModelImpl.Builder());
    ICompositionController<INote> controller = new CompositeController(model, viewgui);
    controller.constructView();
    controller.displayView();

  }
}
