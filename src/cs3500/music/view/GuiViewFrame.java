package cs3500.music.view;

import java.awt.*;
import java.awt.List;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.util.*;

import javax.swing.*;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * To represent the main GUI View
 */
// CHANGELOG: Now implements GuiView instead.
public class GuiViewFrame extends javax.swing.JFrame
        implements GuiView<INote> {

  private java.util.List<String> pitches;
  private java.util.List<INote> notes;
  private int maxBeats;
  private JPanel mainPanel;
  private JScrollBar sb;
  private NotePanel notePanel;
  private int scroll;
  /**
   * Creates new GuiView
   */
  public GuiViewFrame() {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void displayComposition() {
    mainPanel = new JPanel(new BorderLayout());
    this.notePanel = new NotePanel(this.pitches, this.notes, maxBeats);
    mainPanel.add(this.notePanel, BorderLayout.CENTER);
    mainPanel.add(new PitchPanel(this.pitches), BorderLayout.WEST);
    JScrollPane scroll = new JScrollPane(mainPanel);
    // scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
    sb = scroll.getHorizontalScrollBar();
    mainPanel.setVisible(true);
    this.getContentPane().add(scroll);
    this.setSize(1200, (this.pitches.size() + 5) * 15);
    this.setVisible(true);
    for (int i = 0; i < 10000; i++) {
      this.updateHorizontalScroll();
    }


  }

  @Override
  public void updateHorizontalScroll() {
    scroll++;
    notePanel.setShift(scroll);
    if (scroll % 304 == 0) {
      sb.setValue(scroll * 15 % 304);
    }
    this.repaint();
  }

  @Override
  public void buildComposition(MusicModelObserver<INote> model) {
    this.pitches = model.pitchRangeAsList();
    this.notes = model.getComposition();
    this.maxBeats = model.maxBeat();
    this.scroll = 1;

  }

  /**
   * To represent the notes visualized
   */
  private class NotePanel extends JPanel {
    private java.util.List<INote> notes;
    private java.util.List<String> pitches;
    private int numPitches;
    private int maxBeat;
    private Graphics panelWithNoLine;
    private int shift;
    /**
     * Construct a note panel
     *
     * @param pitches pitches to use to vertically align the notes
     * @param notes   the notes to display
     * @param maxBeat the maximum beat of the notes
     */
    private NotePanel(java.util.List<String> pitches, java.util.List<INote> notes, int maxBeat) {
      super();
      this.notes = notes;
      this.pitches = pitches;
      this.numPitches = pitches.size();
      this.maxBeat = maxBeat;
      this.setPreferredSize(new Dimension(maxBeat * 15, (numPitches) * 15));
      this.shift = 1;
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.generateGrid(g);
    }

    protected void setShift(int x) {
      this.shift = x;
      this.repaint();
    }

    /**
     * Generates the grid of notes, aligned to the pitches and labeled for each 16th beat.
     *
     * @param g the graphic onwhich everything is drawn on
     */
    private void generateGrid(Graphics g) {
      for (int j = 0; j <= maxBeat; j++) {
        if (j % 16 == 0)
          g.drawString(Integer.toString(j), j * 15, 10);
      }
      for (int i = 1; i <= numPitches; i++) {
        for (int j = 0; j < maxBeat; j++) {
          if (j % 4 == 0) {
            g.drawRect(j * 15, i * 15, 60, 15);
          }
        }
      }
      for (INote n : notes) {
        int yPos = this.pitches.size() -
                (this.pitches.indexOf(n.getPitch().toString() + n.getOctave()));
        int xPos = n.getStartingBeat();
        g.setColor(Color.BLACK);
        g.fillRect(xPos * 15, yPos * 15, 15, 15);
        xPos += 1;
        g.setColor(Color.GREEN);
        for (int i = xPos; i < xPos + n.getDuration() - 1; i++) {
          g.fillRect(i * 15, yPos * 15, 15, 15);
        }
      }
      this.panelWithNoLine = g.create();
      g.setColor(Color.RED);
      g.drawLine(shift + 15, 15, shift + 15, (this.pitches.size() + 1) * 15);
    }
  }

  /**
   * To represent a JPanel for the list of pitches
   */
  private class PitchPanel extends JPanel {
    private java.util.List<String> pitches;

    /**
     * To construct the Panel which displays the Pitch
     *
     * @param pitches the pitches to display on the panel
     */
    private PitchPanel(java.util.List<String> pitches) {
      super();
      this.pitches = pitches;
      this.setPreferredSize(new Dimension(25, (pitches.size() + 1) * 15));
    }


    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      int yPos = (pitches.size() + 1) * 15;
      for (String s : pitches) {
        g.drawString(s, 0, yPos);
        yPos -= 15;
      }
    }

  }

}
