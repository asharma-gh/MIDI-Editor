package cs3500.music.model;

import java.util.Comparator;

/**
 * Comparator to sort notes by the instrument that plays them.
 */
public class NoteComparatorForInstrument implements Comparator<Note> {

  public NoteComparatorForInstrument() {

  }

  @Override
  public int compare(Note n1, Note n2) {
    return n1.getInstrumentMIDI() - n2.getInstrumentMIDI();
  }

}
