import org.sql2o.*;
import java.util.List;
import java.sql.Timestamp;

public class Recipe implements DatabaseManagement {

  private int id;
  private String title;
  private String description;
  private String note;
  private int ratingId;
  private Timestamp created;

  public Recipe (String title, String description, String note, int ratingId) {
    this.title = title;
    this.description = description;
    this.note = note;
    this.ratingId = ratingId;
  }

  public int getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public String getNote() {
    return this.note;
  }

  public int getRatingId() {
    return this.ratingId;
  }

  public static List<Recipe> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags ORDER BY description;";
      return con.createQuery(sql)
        .executeAndFetch(Recipe.class);
    }
  }

  public static Recipe find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags WHERE id = :id;";
      Recipe tag = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Recipe.class);
      return tag;
    }
  }

  public boolean equals(Object otherRecipe) {
    if(!(otherRecipe instanceof Recipe)) {
      return false;
    } else {
      Recipe newRecipe = (Recipe) otherRecipe;
      return this.getId() == newRecipe.getId() &&
        this.getDescription().equals(newRecipe.getDescription());
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
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

}
