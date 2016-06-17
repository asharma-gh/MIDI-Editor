package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import java.util.List;

import cs3500.music.model.Note;

/**
 * To represent the notes visualized
 */
public class NotePanel extends JPanel {
  private List<Note> notes;
  private List<String> pitches;
  private int numPitches;
  private int maxBeat;

  public NotePanel(List<String> pitches, List<Note> notes, int maxBeat) {
    super();
    this.notes = notes;
    this.pitches = pitches;
    this.numPitches = pitches.size();
    this.maxBeat = maxBeat;
    System.out.println(maxBeat);
    this.setPreferredSize(new Dimension(maxBeat * 15, (numPitches) * 15));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.generateGrid(g);
  }

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
    for (Note n : notes) {
      int yPos = this.pitches.size() - (this.pitches.indexOf(n.getPitch().toString() + n.getOctave()));
      int xPos = n.getStartingBeat();
      g.setColor(Color.BLACK);
      g.fillRect(xPos * 15, yPos * 15, 15, 15);
      xPos += 1;
      g.setColor(new Color(150, 255 - yPos * 15 % 255, 255 - yPos * 15 % 255));
      for (int i = xPos; i < xPos + n.getDuration() - 1; i++) {
        g.fillRect(i * 15, yPos * 15, 15, 15);
      }
    }

  }
}
