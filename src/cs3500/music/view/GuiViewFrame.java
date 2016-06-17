package cs3500.music.view;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import javax.swing.*;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * To represent the main GUI View
 */
public class GuiViewFrame extends javax.swing.JFrame
        implements ICompositionView {

  private java.util.List<String> pitches;
  private java.util.List<Note> notes;
  private int maxBeats;
  private JPanel mainPanel;
  private JScrollBar sb;
  /**
   * Creates new GuiView
   */
  public GuiViewFrame() {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void displayComposition() {
    mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(new NotePanel(this.pitches, this.notes, maxBeats), BorderLayout.CENTER);
    mainPanel.add(new PitchPanel(this.pitches), BorderLayout.WEST);
    JScrollPane scroll = new JScrollPane(mainPanel);
    scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
    sb = scroll.getHorizontalScrollBar();
    mainPanel.setVisible(true);
    this.getContentPane().add(scroll);
    this.setSize(1200, (this.pitches.size() + 5) * 15);
    this.setVisible(true);
    updateScroll(1);
  }

  @Override
  public void updateScroll(int beat) {
    sb.setValue(beat * 40);
    this.repaint();
  }
  @Override
  public void buildComposition(MusicModelObserver<Note> model) {
    this.pitches = model.pitchRangeAsList();
    this.notes = model.getComposition();
    this.maxBeats = model.maxBeat();
  }
}
