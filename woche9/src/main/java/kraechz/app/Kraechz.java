package kraechz.app;

public class Kraechz {
  private final String handle;
  private final String text;

  public Kraechz(String handle, String text) {
    this.handle = handle;
    this.text = text;
  }

  public String getHandle() {
    return handle;
  }

  public String getText() {
    return text;
  }


}
