package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.util.List;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.*;

import static java.awt.event.KeyEvent.*;

/**
 * A composite controller used to control a view implementation with possibly
 * both the midi and the gui active at the same time.
 */
public class CompositeController implements ICompositionController<INote> {
  private GuiView<INote> view;
  private MusicModel<INote> model;
  private KeyboardHandler kbh;
  private MouseClickListener mcl;

  public CompositeController(MusicModel<INote> model, GuiView<INote> view) {
    this.view = view;
    this.model = model;
    this.kbh = new KeyboardHandler();
    this.kbh.addPressedEvent(VK_HOME, new JumpToStart(this.view));
    this.kbh.addPressedEvent(VK_END, new JumpToEnd(this.view));
    this.kbh.addPressedEvent(VK_P, new PauseMusic(this.view));
    this.kbh.addPressedEvent(VK_S, new PlayMusic(this.view));
    this.kbh.addPressedEvent(VK_LEFT, new ScrollLeft(this.view));
    this.kbh.addPressedEvent(VK_RIGHT, new ScrollRight(this.view));
    this.kbh.addPressedEvent(VK_DOWN, new ScrollDown(this.view));
    this.kbh.addPressedEvent(VK_UP, new ScrollUp(this.view));
    this.view.setKeyListener(kbh);

  }

  @Override
  public void constructView() {
    this.view.buildComposition(model);
    this.mcl = new MouseClickListener();
    this.mcl.addModelAndView(this);
    this.view.setMouseListener(mcl);
  }

  @Override
  public void displayView() {
    this.view.displayComposition();
  }

  @Override
  public void removeNote(int x, int y) {
    System.out.println(model.getComposition());
    int xPos = ((x + 15 - (x % 15)) / 15) - 1;
    int yPos = y + 15 - (y % 15);
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

    System.out.println(model.getComposition());
    this.view.setNotes(this.model.getComposition());
    this.view.buildComposition(this.model);
  }
}
