// import org.sql2o.*;
// import org.junit.*;
// import static org.junit.Assert.*;
// import java.util.List;
//
// public class RatingTest {
//
//   @Rule
//   public DatabaseRule databse = new DatabaseRule();
//
//   @Test
//   public void Rating_instantiatesCorrectly_true() {
//     Rating testRating = new Rating(5);
//     assertTrue(testRating instanceof Rating);
//   }
//
//   @Test
//   public void getId_getsTagId_true() {
//     Rating testRating = new Rating(5);
//     testRating.save();
//     assertTrue(testRating.getId() > 0);
//   }
//
//   @Test
//   public void getRating_getsRatingRating_true() {
//     Rating testRating = new Rating(5);
//     testRating.save();
//     assertTrue(testRating.getRating() == 5);
//     assertTrue(Rating.all().get(0).getRating() == 5);
//   }
//
//   @Test
//   public void equals_RatingObjectsAreTheSame_true() {
//     Rating testRating1 = new Rating(5);
//     testRating1.save();
//     Rating testRating2 = testRating1;
//     assertTrue(testRating1.equals(testRating2));
//   }
//
//   @Test
//   public void all_returnsAllSavedRatings_true() {
//     Rating testRating1 = new Rating(5);
//     testRating1.save();
//     Rating testRating2 = new Rating(4);
//     testRating2.save();
//     assertTrue(Rating.all().get(0).equals(testRating1));
//     assertTrue(Rating.all().get(1).equals(testRating2));
//   }
//
//   @Test
//   public void save_returnsTrueIfRatingSaved_true() {
//     Rating testRating = new Rating(5);
//     testRating.save();
//     assertTrue(Rating.all().get(0).equals(testRating));
//   }
//
//   @Test
//   public void find_returnsRatingWithSameId_Rating() {
//     Rating testRating1 = new Rating(5);
//     testRating1.save();
//     Rating testRating2 = new Rating(4);
//     testRating2.save();
//     assertEquals(testRating2, Rating.find(testRating2.getId()));
//   }
//
//   @Test
//   public void update_updatesRatingProperties_true() {
//     Rating testRating = new Rating(5);
//     testRating.save();
//     int newRating = 4;
//     testRating.update(newRating);
//     assertTrue(Rating.find(testRating.getId()).getRating() == 4);
//   }
//
//   @Test
//   public void delete_deletesRating_true() {
//     Rating testRating = new Rating(5);
//     testRating.save();
//     int testRatingId = testRating.getId();
//     testRating.delete();
//     assertEquals(null, Rating.find(testRatingId));
//   }
//
// }
