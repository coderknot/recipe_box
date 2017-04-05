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

}
