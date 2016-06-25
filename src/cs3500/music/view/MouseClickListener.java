package cs3500.music.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.PaintEvent;
import java.util.List;
import java.util.Map;

import cs3500.music.controller.ICompositionController;
import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;

/**
 * To represent a MouseClickListener
 */
public class MouseClickListener implements MouseListener {
  private ICompositionController<INote> controller;
  private GuiView<INote> view;

  public MouseClickListener() {

  }

  /**
   * Adds the given model to this mouse click Listener
   */
  public void addControllerAndView(ICompositionController<INote> controller, GuiView<INote> view) {
    this.controller = controller;
    this.view = view;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1 && !view.isPlaying()) {
      this.controller.removeNote(e.getX(), e.getY());
    }

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
