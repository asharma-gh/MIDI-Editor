package cs3500.music.controller;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.ICompositionView;

/**
 * An implementation of the ICompositionController interface. Supports basic commands for
 * translating the model to a view.
 */
public class ControllerImpl implements ICompositionController<Note> {
  private MusicModel<Note> model;
  private ICompositionView<Note> view;

  public ControllerImpl(MusicModel<Note> model, ICompositionView<Note> view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void constructView() {
    view.buildComposition(model);
  }

  @Override
  public void displayView() {
    view.displayComposition();
  }

}
