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

  public Timestamp getCreated() {
    return this.created;
  }

  public static List<Recipe> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipes;";
      return con.createQuery(sql)
        .addColumnMapping("rating_id", "ratingId")
        .executeAndFetch(Recipe.class);
    }
  }

  public static Recipe find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipes WHERE id = :id;";
      Recipe tag = con.createQuery(sql)
        .addColumnMapping("rating_id", "ratingId")
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
        this.getTitle().equals(newRecipe.getTitle()) &&
        this.getDescription().equals(newRecipe.getDescription()) &&
        this.getNote().eqauls(newRecipe.getNote()) &&
        this.getRatingId() == newRecipe.getRatingId() &&
        this.getCreated().equals(newRecipe.getCreated());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes (title, description, note, ratingId, created) VALUES (:title, :description, :note, :ratingId, now())";
      this.id = (int) con.createQuery(sql, true)
        .addColumnMapping("rating_id", "ratingId")
        .addParameter("title", this.getTitle())
        .addParameter("description", this.getDescription())
        .addParameter("note", this.getNote())
        .addParameter("ratingId", this.getRatingId())
        .executeUpdate()
        .getKey();
    }
  }

  public void updateParser(String title, String description, String note, int ratingId) {

    String column = "";
    String value = "";

    if(!(this.getTitle().equals(title) || title.isEmpty() || title == null)) {
      column = "title";
      value = String.valueOf(title);
      update(column, value);
    }

    if(!(this.getDescription().equals(description) || description.isEmpty() || description == null)) {
      column = "description";
      value = String.valueOf(description);
      update(column, value);
    }

    if(!(this.getNote().equals(note) || note.isEmpty() || note == null)) {
      column = "note";
      value = String.valueOf(note);
      update(column, value);
    }

    if(!(this.getRatingId() == ratingId || ratingId <= 0)) {
      column = "rating_id";
      value = String.valueOf(ratingId);
      update(column, value);
    }

  }

  public void update(String column, String value){
    try (Connection con = DB.sql2o.open()){
      String sql = String.format("UPDATE steps SET %s = %s WHERE id = :id;", column, value);
      con.createQuery(sql)
        .addParameter("id", this.id)
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
