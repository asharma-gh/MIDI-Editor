package cs3500.music.model;

/**
 * Factory class for the music model.
 */
public class MusicModelBuilder {

  /**
   * Constructs a MusicModelImpl. Since there is only one variant of the implementation,
   * we create that.
   * @return a new MusicModelImpl
   */
  public static MusicModel<Note> build() {
    return new MusicModelImpl();
  }
}
