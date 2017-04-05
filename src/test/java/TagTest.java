import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TagTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void Tag_instantiatesCorrectly_true() {
    Tag testTag = new Tag("dinner");
    assertTrue(testTag  instanceof Tag);
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
    Tag testTag1 = new Tag("dinner");
    testTag1.save();
    Tag testTag2 = testTag1;
    assertTrue(testTag1.equals(testTag2));
  }

  @Test
  public void all_returnsAllSavedTags_true() {
    Tag testTag1 = new Tag("dinner");
    testTag1.save();
    Tag testTag2 = new Tag("dinner");
    testTag2.save();
    assertTrue(Tag.all().get(0).equals(testTag1));
    assertTrue(Tag.all().get(1).equals(testTag2));
  }

  @Test
  public void save_returnsTrueIfTagSaved_true() {
    Tag testTag = new Tag("dinner");
    testTag.save();
    assertTrue(Tag.all().get(0).equals(testTag));
  }
}
