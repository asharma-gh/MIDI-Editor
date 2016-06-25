package cs3500.music.controller;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.CompositionView;
import cs3500.music.view.GuiView;
import cs3500.music.view.ICompositionView;
import cs3500.music.view.JumpToEnd;
import cs3500.music.view.JumpToStart;
import cs3500.music.view.KeyboardHandler;
import cs3500.music.view.PauseMusic;
import cs3500.music.view.PlayMusic;

import static java.awt.event.KeyEvent.*;

/**
 * Created by nbuqu on 6/24/2016.
 */
public class CompositeController implements ICompositionController<INote> {
  private GuiView<INote> view;
  private MusicModel<INote> model;
  private KeyboardHandler kbh;

  public CompositeController(MusicModel<INote> model, GuiView<INote> view) {
    this.view = view;
    this.model = model;
    this.kbh = new KeyboardHandler();
    this.kbh.addPressedEvent(VK_HOME, new JumpToStart(this.view));
    this.kbh.addPressedEvent(VK_END, new JumpToEnd(this.view));
    this.kbh.addPressedEvent(VK_P, new PauseMusic(this.view));
    this.kbh.addPressedEvent(VK_S, new PlayMusic(this.view));
    this.view.setKeyListener(kbh);
  }

  @Override
  public void constructView() {
    this.view.buildComposition(model);
  }

  @Override
  public void displayView() {
    this.view.displayComposition();
  }
}
