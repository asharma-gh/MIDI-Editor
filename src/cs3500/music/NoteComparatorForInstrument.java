package cs3500.music;

import java.util.Comparator;

import cs3500.music.model.Note;

/**
 * Comparator to sort notes by the instrument that plays them.
 */
public class NoteComparatorForInstrument implements Comparator<Note> {

  public NoteComparatorForInstrument() {

  }

  @Override
  public int compare(Note n1, Note n2) {
    return n2.getInstrumentMIDI() - n1.getInstrumentMIDI();
  }

}
