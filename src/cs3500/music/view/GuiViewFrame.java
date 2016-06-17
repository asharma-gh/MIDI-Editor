package cs3500.music.view;

import java.awt.*;
import javax.swing.*;
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

    mainPanel.add(new NotePanel(this.pitches, this.notes, model.maxBeat()), BorderLayout.CENTER);
    mainPanel.add(new PitchPanel(this.pitches), BorderLayout.WEST);
    JScrollPane scroll = new JScrollPane(mainPanel);
    mainPanel.setVisible(true);
    this.getContentPane().add(scroll);
    this.setSize(1200, (this.pitches.size() + 5) * 15);
    this.setVisible(true);



  }
}
