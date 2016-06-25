package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.controller.ICompositionController;
import cs3500.music.model.INote;
import cs3500.music.model.Pitch;

/**
 * A class used to createa a JFrame that allows the user to build a note.
 */
public class NoteBuilderFrame extends javax.swing.JFrame {
  private JPanel mainPanel;
  private JList<String> pitchList;
  private JTextField instrument, octave, startBeat, duration, volume;
  private JButton noteBuilder;
  private int[] noteVars;

  public NoteBuilderFrame() {
    this.setSize(400, 400);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setLayout(new FlowLayout());
    this.instrument = new JTextField("Enter instrument here (0-127)");
    this.octave = new JTextField("Enter octave here (0-99)");
    this.startBeat = new JTextField("Enter start beat here");
    this.duration = new JTextField("Enter duration of beat here");
    this.volume = new JTextField("Enter volume of beat here");
    this.noteBuilder = new JButton("Build");
    this.noteBuilder.setActionCommand("Build");
    this.noteVars = new int[6];
  }

 /* private boolean validBuild() {
    try {
      Integer.parseInt(octave.getText());
      Integer.parseInt(duration.getText());
      Integer.parseInt(startBeat.getText());
      Integer.parseInt(volume.getText());
      Integer.parseInt(instrument.getText());
      return Integer.parseInt(octave.getText()) >= 0 &&
    }
    catch (Exception e) {
      return false;
    }
  }*/

  /**
   *
   * @param controller
   */
  public void buildFrame(ICompositionController<INote> controller) {
    String[] pitches = new String[Pitch.values().length];
    this.mainPanel = new JPanel();
    this.setLayout(new FlowLayout());
    int n = 0;
    for (Pitch p : Pitch.values()) {
      pitches[n] = p.toString();
      n += 1;
    }
    this.pitchList = new JList<String>(pitches);
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          noteVars[0] = pitchList.locationToIndex(e.getPoint());
        }
      }
    };
    this.pitchList.addMouseListener(mouseListener);
    this.mainPanel.add(this.pitchList);
    this.mainPanel.add(this.instrument);
    this.mainPanel.add(this.octave);
    this.mainPanel.add(this.startBeat);
    this.mainPanel.add(this.duration);
    this.mainPanel.add(this.volume);
    this.noteBuilder.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
          case "Build":
            noteVars[1] = Integer.parseInt(octave.getText());
            noteVars[2] = Integer.parseInt(duration.getText());
            noteVars[3] = Integer.parseInt(startBeat.getText());
            noteVars[4] = Integer.parseInt(volume.getText());
            noteVars[5] = Integer.parseInt(instrument.getText());
            controller.addNote(noteVars);
            break;
          default: System.err.print("This shouldn't happen");
            break;
        }
      }
    });
    this.mainPanel.add(this.noteBuilder);
    this.add(mainPanel);
    this.pack();
    this.setVisible(true);
  }
}
