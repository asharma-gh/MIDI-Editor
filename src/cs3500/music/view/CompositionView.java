package cs3500.music.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModelObserver;

/**
 * To represent a view which combined the midi view and the gui view.
 */
public class CompositionView implements GuiView<INote> {
  private GuiViewFrame gui;
  private MidiViewImpl midi;
  private int progress;

  public CompositionView(GuiViewFrame gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;
    this.progress = 0;
  }

  @Override
  public void displayComposition() {
    this.gui.displayComposition();
    this.midi.displayComposition();
    this.beginPlayback();
  }

  private void beginPlayback() {
    this.gui.setFocusable(true);
  }

  @Override
  public void buildComposition(MusicModelObserver<INote> model) {
    this.gui.buildComposition(model);
    this.midi.buildComposition(model);
    Timer t = new Timer(midi.sequencer.getSequence().getResolution(), new SynchronizeLineAction());
    t.start();

  }

  @Override
  public void updateScroll() {
    this.gui.updateScroll();
  }

  @Override
  public void linePosition(int pos) {
    this.gui.linePosition(pos);
  }

  @Override
  public int getWidth() {
    return this.gui.getWidth();
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    this.gui.setKeyListener(kl);
  }

  @Override
  public void setMouseListener(MouseListener ml) {
    this.gui.setMouseListener(ml);
  }

  @Override
  public void pausePlayback() {
    if (this.midi.sequencer.isRunning()) {
      this.midi.sequencer.stop();
      this.gui.updatePause();
    }
  }

  @Override
  public void resumePlayback() {
    if (!this.midi.sequencer.isRunning()) {
      this.midi.sequencer.start();
      this.beginPlayback();
      this.gui.updatePause();
      System.out.println("unpaused");
    }
  }

  @Override
  public void scrollX(int x) {
    this.gui.scrollX(x);
  }

  @Override
  public void scrollY(int y) {
    this.gui.scrollY(y);
  }

  @Override
  public void updatePause() {
    this.gui.updatePause();
  }

  @Override
  public void setNotes(List<INote> notes) {
    this.gui.setNotes(notes);
  }

  @Override
  public void jumpToStart() {
    this.gui.jumpToStart();
  }

  /**
   * to represent movement of a red line every beat
   */
  private class SynchronizeLineAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      CompositionView.this.gui.linePosition((int) ((midi.sequencer.getTickPosition() + 1) * 15) / 16);
    }
  }
}
