import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Tag implements DatabaseManagement {

  private int id;
  private String description;

  public Tag(String description) {
    this.description = description;
  }

  public int getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public static List<Tag> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags ORDER BY description;";
      return con.createQuery(sql)
        .executeAndFetch(Tag.class);
    }
  }

  public boolean equals(Object otherTag) {
    if(!(otherTag instanceof Tag)) {
      return false;
    } else {
      Tag newTag = (Tag) otherTag;
      return this.getId() == newTag.getId() &&
        this.getDescription().equals(newTag.getDescription());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tags (description) VALUES (:description);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", this.getDescription())
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tags WHERE :id = id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

}
