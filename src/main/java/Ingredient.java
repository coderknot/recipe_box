import org.sql2o.*;
import java.util.List;

public class Ingredient implements DatabaseManagement {

  private int id;
  private String description;

  public Ingredient (String description) {
    this.description = description;
  }

  public int getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public static List<Ingredient> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM ingredients ORDER BY description;";
      return con.createQuery(sql)
        .executeAndFetch(Ingredient.class);
    }
  }

  public static Ingredient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM ingredients WHERE id = :id;";
      Ingredient ingredient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Ingredient.class);
      return ingredient;
    }
  }

  public boolean equals(Object otherIngredient) {
    if(!(otherIngredient instanceof Ingredient)) {
      return false;
    } else {
      Ingredient newIngredient = (Ingredient) otherIngredient;
      return this.getId() == newIngredient.getId() &&
        this.getDescription().equals(newIngredient.getDescription());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO ingredients (description) VALUES (:description);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", this.getDescription())
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE ingredients SET description = :description WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM ingredients WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

}
