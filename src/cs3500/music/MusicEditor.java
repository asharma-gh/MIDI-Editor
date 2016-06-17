package cs3500.music;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.ICompositionView;
import cs3500.music.view.MidiViewImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    //GuiViewFrame view = new GuiViewFrame();
    ICompositionView midiView = new MidiViewImpl();
    FileInputStream file = new FileInputStream(
            "C:\\MusicFiles\\zoot-zl.txt");
    MusicModel<Note> model = MusicReader.parseFile(new BufferedReader(
            new InputStreamReader(file)),
            new MusicModelImpl.Builder());
    midiView.buildComposition(model);
    midiView.playComposition();
    // You probably need to connect these views to your model, too...
  }
}
