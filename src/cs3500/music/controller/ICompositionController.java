package cs3500.music.controller;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.ICompositionView;

/**
 * Basic interface for a composition controller.
 * We do not allow for model manipulation through the controller.
 * We are just trying to display a preconstructed model, not actively
 * change it.
 */
public interface ICompositionController {

  void constructView();
}
