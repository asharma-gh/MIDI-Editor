package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModelObserver;

/**
 * To represent a view which combined the midi view and the gui view.
 */
public class CompositionView implements GuiView<INote> {
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
        this.updateHorizontalScroll(scrollAmt);
        this.updateLine(scrollAmt);
        scrollAmt += 1;
      }
    }
  }

  @Override
  public void buildComposition(MusicModelObserver<INote> model) {
    this.gui.buildComposition(model);
    this.midi.buildComposition(model);


  }

  @Override
  public void updateHorizontalScroll(int pos) {
    this.gui.updateHorizontalScroll(pos);
  }

  @Override
  public void updateLine(int pos) {
    this.gui.updateLine(pos);
  }

  @Override
  public int getWidth() {
    return this.gui.getWidth();
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    this.gui.addKeyListener(kl);
  }

  @Override
  public void setMouseListener(MouseListener ml) {
    this.gui.addMouseListener(ml);
  }

  @Override
  public void pausePlayback() {
    this.midi.sequencer.stop();
  }

  @Override
  public void resumePlayback() {
    this.midi.sequencer.start();
  }
}
