import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class RecipeTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void Recipe_instantiatesCorrectly_true() {
    Recipe testRecipe = new Recipe("Grilled Chicken", "it's chicken, grilled", "make sure it's grilled", 1);
    
  }

}
