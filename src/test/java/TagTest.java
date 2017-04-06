import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class TagTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Tag_instantiatesCorrectly_true() {
    Tag testTag = new Tag("dinner");
    assertTrue(testTag instanceof Tag);
  }

  @Test
  public void getId_getsTagId_true() {
    Tag testTag = new Tag("dinner");
    testTag.save();
    assertTrue(testTag.getId() > 0);
  }

  @Test
  public void getDescription_getsTagDescription_true() {
    Tag testTag = new Tag("dinner");
    testTag.save();
    assertTrue(testTag.getDescription().equals("dinner"));
    assertTrue(Tag.all().get(0).getDescription().equals("dinner"));
  }

  @Test
  public void equals_TagObjectsAreTheSame_true() {
    Tag testTag = new Tag("dinner");
    testTag.save();
    assertTrue(Tag.all().get(0).equals(testTag));
  }

  @Test
  public void all_returnsAllSavedTags_true() {
    Tag testTag1 = new Tag("dinner");
    testTag1.save();
    Tag testTag2 = new Tag("lunch");
    testTag2.save();
    assertEquals(testTag1.getId(), Tag.find(testTag1.getId()).getId());
    assertEquals(testTag2.getId(), Tag.find(testTag2.getId()).getId());
  }

  @Test
  public void save_returnsTrueIfTagSaved_true() {
    Tag testTag = new Tag("breakfast");
    testTag.save();
    assertTrue(Tag.all().get(0).equals(testTag));
  }

  @Test
  public void find_returnsTagWithSameId_Tag() {
    Tag testTag1 = new Tag("dinner");
    testTag1.save();
    Tag testTag2 = new Tag("lunch");
    testTag2.save();
    assertEquals(testTag2, Tag.find(testTag2.getId()));
  }

  @Test
  public void update_updatesTagProperties_true() {
    Tag testTag = new Tag("dinner");
    testTag.save();
    String newDescription = "supper";
    testTag.update(newDescription);
    assertTrue(Tag.find(testTag.getId()).getDescription().equals("supper"));
  }

  @Test
  public void delete_deletesTag_true() {
    Tag testTag = new Tag("dinner");
    testTag.save();
    int testTagId = testTag.getId();
    testTag.delete();
    assertEquals(null, Tag.find(testTagId));
  }

}
