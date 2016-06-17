package cs3500.music.view;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events

import javax.swing.*;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */

public class GuiViewFrame extends javax.swing.JFrame
        implements ICompositionView {

  private java.util.List<String> pitches;
  private java.util.List<Note> notes;

  /**
   * Creates new GuiView
   */

  public GuiViewFrame() {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  }

  public void initialize() {

  }

  @Override
  public void displayComposition() {

  }

  @Override
  public void buildComposition(MusicModelObserver<Note> model) {
    this.pitches = model.pitchRangeAsList();
    this.notes = model.getComposition();
    JPanel mainPanel = new JPanel(new BorderLayout());
    JScrollPane scrollNotes = new JScrollPane(new NotePanel(this.pitches, this.notes, model.maxBeat()));
    mainPanel.add(scrollNotes, BorderLayout.CENTER);
    mainPanel.add(new PitchPanel(this.pitches), BorderLayout.WEST);
    mainPanel.setVisible(true);
    this.getContentPane().add(mainPanel);
    this.setSize(1200, (this.pitches.size() + 5) * 15);
    this.setVisible(true);



  }
}
