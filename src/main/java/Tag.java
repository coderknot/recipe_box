import org.sql2o.*;
import java.util.List;

public class Tag implements DatabaseManagement {

  private int id;
  private String description;

  public Tag (String description) {
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
      String sql = "SELECT * FROM tags;";
      return con.createQuery(sql)
        .executeAndFetch(Tag.class);
    }
  }

  public static Tag find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags WHERE id = :id;";
      Tag tag = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Tag.class);
      return tag;
    }
  }

  @Override
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
        .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tags SET description = :description WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tags WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
