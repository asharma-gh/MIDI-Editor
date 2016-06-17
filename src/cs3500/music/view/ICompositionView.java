package cs3500.music.view;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * Created by nbuqu on 6/16/2016.
 */
public interface ICompositionView {

  void playComposition();

  void buildComposition(MusicModel<Note> comp);
}
