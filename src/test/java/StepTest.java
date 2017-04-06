import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class StepTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Step_instantiatesCorrectly_true() {
    Step testStep = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");
    assertTrue(testStep instanceof Step);
  }

  @Test
  public void getters_getsStepProperties_true() {
    Step testStep = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");
    testStep.save();

    assertTrue(testStep.getRecipeId() == 1);
    assertTrue(testStep.getSequenceNumber() == 1);
    assertTrue(testStep.getIngredientId() == 1);
    assertTrue(testStep.getUnit().equals("cup"));
    assertTrue(testStep.getQuantity() == 1);
    assertTrue(testStep.getDescription().equals("put the flour in the thing"));

    assertTrue(Step.find(testStep.getId()).getRecipeId() == 1);
    assertTrue(Step.find(testStep.getId()).getSequenceNumber() == 1);
    assertTrue(Step.find(testStep.getId()).getIngredientId() == 1);
    assertTrue(Step.find(testStep.getId()).getUnit().equals("cup"));
    assertTrue(Step.find(testStep.getId()).getQuantity() == 1);
    assertTrue(Step.find(testStep.getId()).getDescription().equals("put the flour in the thing"));
  }

  @Test
  public void equals_StepObjectsAreTheSame_true() {
    Step testStep1 = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");
    testStep1.save();
    Step testStep2 = testStep1;
    assertTrue(testStep1.equals(testStep2));
  }

  @Test
  public void all_returnsAllSavedSteps_true() {
    Step testStep1 = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");
    testStep1.save();
    Step testStep2 = new Step(1, 2, 2, "cup", 1, "put the milk in the thing");
    testStep2.save();
    assertEquals(testStep1.getId(), Step.find(testStep1.getId()).getId());
    assertEquals(testStep2.getId(), Step.find(testStep2.getId()).getId());
  }

  @Test
  public void save_returnsTrueIfStepSaved_true() {
    Step testStep = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");
    testStep.save();
    assertEquals(testStep.getId(), Step.find(testStep.getId()).getId());
  }

  @Test
  public void find_returnsStepWithSameId_Step() {
    Step testStep1 = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");
    testStep1.save();
    Step testStep2 = new Step(1, 2, 2, "cup", 1, "put the milk in the thing");
    testStep2.save();
    assertEquals(testStep1, Step.find(testStep1.getId()));
    assertEquals(testStep2, Step.find(testStep2.getId()));
  }

  @Test
  public void update_updatesStepProperties_true() {
    Step testStep = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");

    testStep.save();

    testStep.update("recipe_id", "2");
    testStep.update("seq_no", "2");
    testStep.update("ingredient_id", "2");
    testStep.update("unit", "'1/2 cup'");
    testStep.update("quantity", "2");
    testStep.update("description", "'put the thing in the flour'");

    assertTrue(Step.find(testStep.getId()).getRecipeId() == 2);
    assertTrue(Step.find(testStep.getId()).getSequenceNumber() == 2);
    assertTrue(Step.find(testStep.getId()).getIngredientId() == 2);
    assertTrue(Step.find(testStep.getId()).getUnit().equals("1/2 cup"));
    assertTrue(Step.find(testStep.getId()).getQuantity() == 2);
    assertTrue(Step.find(testStep.getId()).getDescription().equals("put the thing in the flour"));
  }

  @Test
  public void updateParser_passesCorrectlyFormattedArgumentsToUpdate_true() {
    Step testStep = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");

    testStep.save();

    testStep.updateParser(2, 2, 2, "1/2 cup", 2, "put the thing in the flour");

    assertTrue(Step.find(testStep.getId()).getRecipeId() == 2);
    assertTrue(Step.find(testStep.getId()).getSequenceNumber() == 2);
    assertTrue(Step.find(testStep.getId()).getIngredientId() == 2);
    assertTrue(Step.find(testStep.getId()).getUnit().equals("1/2 cup"));
    assertTrue(Step.find(testStep.getId()).getQuantity() == 2);
    assertTrue(Step.find(testStep.getId()).getDescription().equals("put the thing in the flour"));
  }

  @Test
  public void delete_deletesStep_true() {
    Step testStep = new Step(1, 1, 1, "cup", 1, "put the flour in the thing");
    testStep.save();
    int testStepId = testStep.getId();
    testStep.delete();
    assertEquals(null, Step.find(testStepId));
  }

}
