package cs3500.music.model;

import java.util.Comparator;

/**
 * This class is a comparator used to compare two Notes.
 */
public class NoteComparator implements Comparator<Note> {

  @Override
  public int compare(Note o1, Note o2) {
    return (o1.getPitch().ordinal() + (o1.getOctave()) * 100) -
            (o2.getPitch().ordinal() + (o2.getOctave() * 100));
  }
}
