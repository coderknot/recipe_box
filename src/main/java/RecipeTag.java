import org.sql2o.*;
import java.util.List;

public class RecipeTag implements DatabaseManagement {

  private int id;
  private int recipeId;
  private int tagId;

  public RecipeTag(int recipeId, int tagId) {
    this.recipeId = recipeId;
    this.tagId = tagId;
  }

  public int getId() {
    return this.id;
  }

  public int getRecipeId() {
    return this.recipeId;
  }

  public int getTagId() {
    return this.tagId;
  }

  public static List<RecipeTag> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipe_tags;";
      return con.createQuery(sql)
        .addColumnMapping("recipe_id", "recipeId")
        .addColumnMapping("tag_id", "tagId")
        .executeAndFetch(RecipeTag.class);
    }
  }

  public static RecipeTag find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipe_tags WHERE id = :id;";
      RecipeTag recipeTag = con.createQuery(sql)
        .addColumnMapping("recipe_id", "recipeId")
        .addColumnMapping("tag_id", "tagId")
        .addParameter("id", id)
        .executeAndFetchFirst(RecipeTag.class);
      return recipeTag;
    }
  }

  @Override
  public boolean equals(Object otherRecipeTag) {
    if(!(otherRecipeTag instanceof RecipeTag)) {
      return false;
    } else {
      RecipeTag newRecipeTag = (RecipeTag) otherRecipeTag;
      return this.getId() == newRecipeTag.getId() &&
        this.getRecipeId() == newRecipeTag.getRecipeId() &&
        this.getTagId() == newRecipeTag.getTagId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipe_tags (recipe_id, tag_id) VALUES (:recipeId, :tagId);";
      this.id = (int) con.createQuery(sql, true)
        .addColumnMapping("recipe_id", "recipeId")
        .addColumnMapping("tag_id", "tagId")
        .addParameter("recipeId", this.getRecipeId())
        .addParameter("tagId", this.getRecipeId())
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM recipe_tags WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  

}
