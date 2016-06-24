package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModelObserver;

/**
 * To represent a view which combined the midi view and the gui view.
 */
public class CompositionView implements ICompositionView<INote> {
  private GuiViewFrame gui;
  private MidiViewImpl midi;

  public CompositionView(GuiViewFrame gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;

  }

  @Override
  public void displayComposition() {
    this.gui.displayComposition();
    this.midi.displayComposition();
    int scrollAmt = 0;
    while (midi.sequencer.isRunning()) {
      while (scrollAmt < (int) midi.sequencer.getTickPosition() / 16 * 15) {
        gui.updateHorizontalScroll((int) midi.sequencer.getTickPosition() / 16 * 15);
        scrollAmt++;
      }
    }
  }

  @Override
  public void buildComposition(MusicModelObserver<INote> model) {
    this.gui.buildComposition(model);
    this.midi.buildComposition(model);


  }

}
