package cs3500.music.view;

import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * To represent a JPanel for the list of pitches
 */
public class PitchPanel extends JPanel {
  private List<String> pitches;

  public PitchPanel(List<String> pitches) {
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
