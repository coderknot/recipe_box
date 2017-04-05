import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/recipe_box_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteTagQuery = "DELETE FROM tags *;";
      con.createQuery(deletesTagQuery).executeUpdate();

      String deleteRecipesTagsQuery = "DELETE FROM recipe_tags *;";
      con.createQuery(deleteRecipesTagsQuery).executeUpdate();

      String deleteIngredientsQuery = "DELETE FROM ingredients *;";
      con.createQuery(deleteIngredientsQuery).executeUpdate();

      String deleteDirectionsQuery = "DELETE FROM directions *;";
      con.createQuery(deleteDirectionsQuery).executeUpdate();

      String deleteRatingsQuery = "DELETE FROM ratings *;";
      con.createQuery(deleteRatingsQuery).executeUpdate();

      String deleteRecipesQuery = "DELETE FROM recipes *;";
      con.createQuery(deleteRecipesQuery).executeUpdate();
    }
  }
}
