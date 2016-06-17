package cs3500.music.controller;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.ICompositionView;

/**
 * Created by nbuqu on 6/17/2016.
 */
public class ControllerImpl implements ICompositionController {
  private MusicModel<Note> model;
  private ICompositionView view;

  public ControllerImpl(MusicModel<Note> model, ICompositionView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void constructView() {
    view.buildComposition(model);
  }

}
