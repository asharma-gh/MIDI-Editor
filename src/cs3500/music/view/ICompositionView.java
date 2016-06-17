package cs3500.music.view;

import java.util.List;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * An interface for the different types of view for a given music model.
 */
public interface ICompositionView {

  void displayComposition();

  void buildComposition(MusicModelObserver<Note> model);
}
