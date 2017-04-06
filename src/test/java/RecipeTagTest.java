import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class RecipeTagTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void RecipeTag_instantiatesCorrectly_true() {
    RecipeTag testRecipeTag = new RecipeTag(1,1);
    assertTrue(testRecipeTag instanceof RecipeTag);
  }

  @Test
  public void getRecipeId_getsRecipeId_true() {
    RecipeTag testRecipeTag = new RecipeTag(1,1);
    testRecipeTag.save();
    assertTrue(testRecipeTag.getRecipeId() == 1);
    assertTrue(RecipeTag.all().get(0).getRecipeId() == 1);
  }

  @Test
  public void getTagId_getsTagId_true() {
    RecipeTag testRecipeTag = new RecipeTag(1,1);
    testRecipeTag.save();
    assertTrue(testRecipeTag.getTagId() == 1);
    assertTrue(RecipeTag.all().get(0).getTagId() == 1);
  }

  @Test
  public void equals_RecipeTagObjectsAreTheSame_true() {
    RecipeTag testRecipeTag1 = new RecipeTag(1,1);
    testRecipeTag1.save();
    RecipeTag testRecipeTag2 = testRecipeTag1;
    assertTrue(testRecipeTag1.equals(testRecipeTag2));
  }

  @Test
  public void all_returnsAllSavedRecipeTags_true() {
    RecipeTag testRecipeTag1 = new RecipeTag(1,1);
    testRecipeTag1.save();
    RecipeTag testRecipeTag2 = new RecipeTag(1,1);
    testRecipeTag2.save();
    assertEquals(testRecipeTag1.getId(), RecipeTag.find(testRecipeTag1.getId()).getId());
    assertEquals(testRecipeTag2.getId(), RecipeTag.find(testRecipeTag2.getId()).getId());
  }

  @Test
  public void save_returnsTrueIfRecipeTagSaved_true() {
    RecipeTag testRecipeTag = new RecipeTag(1,1);
    testRecipeTag.save();
    assertEquals(testRecipeTag.getId(), RecipeTag.find(testRecipeTag.getId()).getId());
  }

  @Test
  public void find_returnsRecipeTagWithSameId_RecipeTag() {
    RecipeTag testRecipeTag1 = new RecipeTag(1,1);
    testRecipeTag1.save();
    RecipeTag testRecipeTag2 = new RecipeTag(1,1);
    testRecipeTag2.save();
    assertEquals(testRecipeTag1, RecipeTag.find(testRecipeTag1.getId()));
    assertEquals(testRecipeTag2, RecipeTag.find(testRecipeTag2.getId()));
  }

  @Test
  public void delete_deletesRecipeTag_true() {
    RecipeTag testRecipeTag = new RecipeTag(1,1);
    testRecipeTag.save();
    int testRecipeTagId = testRecipeTag.getId();
    testRecipeTag.delete();
    assertEquals(null, RecipeTag.find(testRecipeTagId));
  }

}
