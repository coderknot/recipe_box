import org.sql2o.*;
import java.util.List;

public class Rating {

  private int id;
  private int rating;

  public Rating (int rating) {
    this.rating = rating;
  }

  public int getId() {
    return this.id;
  }

  public int getRating() {
    return this.rating;
  }

  public static List<Rating> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM ratings ORDER BY rating;";
      return con.createQuery(sql)
        .executeAndFetch(Rating.class);
    }
  }

  public static Rating find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM ratings WHERE id = :id;";
      Rating rating = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Rating.class);
      return rating;
    }
  }

  public boolean equals(Object otherRating) {
    if(!(otherRating instanceof Rating)) {
      return false;
    } else {
      Rating newRating = (Rating) otherRating;
      return this.getId() == newRating.getId() &&
        this.getRating() == newRating.getRating();
    }
  }

  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO ratings (rating) VALUES (:rating);";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("rating", this.getRating())
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public void update(int rating) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE ratings SET rating = :rating WHERE id = :id;";
  //     con.createQuery(sql)
  //       .addParameter("rating", rating)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM ratings WHERE id = :id;";
  //     con.createQuery(sql)
  //       .addParameter("id", this.getId())
  //       .executeUpdate();
  //   }
  // }

}
