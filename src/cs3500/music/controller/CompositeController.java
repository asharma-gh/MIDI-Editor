package cs3500.music.controller;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.view.CompositionView;
import cs3500.music.view.GuiView;
import cs3500.music.view.ICompositionView;
import cs3500.music.view.JumpToEnd;
import cs3500.music.view.JumpToStart;
import cs3500.music.view.KeyboardHandler;
import cs3500.music.view.PauseMusic;

import static java.awt.event.KeyEvent.*;

/**
 * Created by nbuqu on 6/24/2016.
 */
public class CompositeController implements ICompositionController<INote> {
  private GuiView<INote> view;
  private MusicModel<INote> model;
  private KeyboardHandler kbh;

  public CompositeController(GuiView<INote> view, MusicModel<INote> model) {
    this.view = view;
    this.model = model;
    this.kbh = new KeyboardHandler();
    this.kbh.addTypedEvent(VK_HOME, new JumpToStart(this.view));
    this.kbh.addTypedEvent(VK_END, new JumpToEnd(this.view));
    this.kbh.addTypedEvent(VK_P, new PauseMusic(this.view.getMidi()));
  }

  @Override
  public void constructView() {

  }

  @Override
  public void displayView() {

  }
}
