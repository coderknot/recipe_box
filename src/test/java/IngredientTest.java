import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class IngredientTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void Ingredient_instantiatesCorrectly_true() {
    Ingredient testIngredient = new Ingredient("chicken");
    assertTrue(testIngredient  instanceof Ingredient);
  }

  @Test
  public void getId_getsTagId_true() {
    Ingredient testIngredient = new Ingredient("chicken");
    testIngredient.save();
    assertTrue(testIngredient.getId() > 0);
  }

  @Test
  public void getDescription_getsIngredientDescription_true() {
    Ingredient testIngredient = new Ingredient("chicken");
    testIngredient.save();
    assertTrue(testIngredient.getDescription().equals("chicken"));
    assertTrue(Ingredient.all().get(0).getDescription().equals("chicken"));
  }

  @Test
  public void equals_IngredientObjectsAreTheSame_true() {
    Ingredient testIngredient1 = new Ingredient("chicken");
    testIngredient1.save();
    Ingredient testIngredient2 = testIngredient1;
    assertTrue(testIngredient1.equals(testIngredient2));
  }

  @Test
  public void all_returnsAllSavedIngredients_true() {
    Ingredient testIngredient1 = new Ingredient("chicken");
    testIngredient1.save();
    Ingredient testIngredient2 = new Ingredient("chicken");
    testIngredient2.save();
    assertTrue(Ingredient.all().get(0).equals(testIngredient1));
    assertTrue(Ingredient.all().get(1).equals(testIngredient2));
  }

  @Test
  public void save_returnsTrueIfIngredientSaved_true() {
    Ingredient testIngredient = new Ingredient("chicken");
    testIngredient.save();
    assertTrue(Ingredient.all().get(0).equals(testIngredient));
  }

  @Test
  public void find_returnsIngredientWithSameId_Ingredient() {
    Ingredient testIngredient1 = new Ingredient("chicken");
    testIngredient1.save();
    Ingredient testIngredient2 = new Ingredient("chicken");
    testIngredient2.save();
    assertEquals(testIngredient2, Ingredient.find(testIngredient2.getId()));
  }

  @Test
  public void update_updatesIngredientProperties_true() {
    Ingredient testIngredient = new Ingredient("chicken");
    testIngredient.save();
    String newDescription = "pork";
    testIngredient.update(newDescription);
    assertTrue(Ingredient.find(testIngredient.getId()).getDescription().equals("pork"));
  }

  @Test
  public void delete_deletesIngredient_true() {
    Ingredient testIngredient = new Ingredient("chicken");
    testIngredient.save();
    int testIngredientId = testIngredient.getId();
    testIngredient.delete();
    assertEquals(null, Ingredient.find(testIngredientId));
  }

}
