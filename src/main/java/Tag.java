import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Tag {

  private int id;
  private String description;

  public Tag(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

}
