package cs3500.music.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.PaintEvent;
import java.util.List;
import java.util.Map;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;

/**
 * To represent a MouseClickListener
 */
public class MouseClickListener implements MouseListener {
  private MusicModel<INote> model;
  private GuiView<INote> view;

  public MouseClickListener() {

  }

  /**
   * Adds the given model to this mouse click Listener
   */
  public void addModelAndView(MusicModel<INote> model, GuiView<INote> view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println(model.getComposition());
    if (MouseEvent.BUTTON1 == e.getButton()) {
      int xPos = ((e.getX() + 15 - (e.getX() % 15)) / 15) - 1;
      int yPos = e.getY() + 15 - (e.getY() % 15);
      int counter = (model.pitchRangeAsList().size() + 1) * 15;
      String pitch = "";
      for (int i = 0; i < model.pitchRangeAsList().size(); i++) {
        if (yPos == counter) {
          pitch = model.pitchRangeAsList().get(i);
        }
        counter -= 15;
      }
      List<INote> oldNotes = model.getComposition();
      for (INote n : oldNotes) {
        if (n.getStartingBeat() == xPos && (n.getPitch().toString() + n.getOctave()).equals(pitch)) {
          model.removeNote(n);
        }
      }
    }
    System.out.println(model.getComposition());
    this.view.setNotes(this.model.getComposition());
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {


  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
