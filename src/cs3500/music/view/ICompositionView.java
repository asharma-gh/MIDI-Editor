package cs3500.music.view;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * An interface for the different types of view for a given music model.
 */
public interface ICompositionView<K> {

  void playComposition();

  void buildComposition(K comp);
}
