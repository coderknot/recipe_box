import org.sql2o.*;
import java.util.List;

public class Step implements DatabaseManagement {

    private int id;
    private int recipeId;
    private int sequenceNumber;
    private int ingredientId;
    private String unit;
    private int quantity;
    private String description;

    public Step(int recipeId, int sequenceNumber, int ingredientId, String unit, int quantity, String description) {
      this.recipeId = recipeId;
      this.sequenceNumber = sequenceNumber;
      this.ingredientId = ingredientId;
      this.unit = unit;
      this.quantity = quantity;
      this.description = description;
    }

    public int getId()  {
      return this.id;
    }

    public int getRecipeId() {
      return this.recipeId;
    }

    public int getSequenceNumber() {
      return this.sequenceNumber;
    }

    public int getIngredientId() {
      return this.ingredientId;
    }

    public String getUnit() {
      return this.unit;
    }

    public int getQuantity() {
      return this.quantity;
    }

    public String getDescription() {
      return this.description;
    }

    public static List<Step> all() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM steps;";
        return con.createQuery(sql)
          .addColumnMapping("recipe_id", "recipeId")
          .addColumnMapping("seq_no", "sequenceNumber")
          .addColumnMapping("ingredient_id", "ingredientId")
          .executeAndFetch(Step.class);
      }
    }

    public static Step find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM steps WHERE id = :id;";
        Step step = con.createQuery(sql)
          .addColumnMapping("recipe_id", "recipeId")
          .addColumnMapping("seq_no", "sequenceNumber")
          .addColumnMapping("ingredient_id", "ingredientId")
          .addParameter("id", id)
          .executeAndFetchFirst(Step.class);
        return step;
      }
    }

    @Override
    public boolean equals(Object otherStep) {
      if(!(otherStep instanceof Step)) {
        return true;
      } else {
        Step newStep = (Step) otherStep;
        return this.getId() == newStep.getId() &&
          this.getRecipeId() == newStep.getRecipeId() &&
          this.getSequenceNumber() == newStep.getSequenceNumber() &&
          this.getIngredientId() == newStep.getIngredientId() &&
          this.getUnit().equals(newStep.getUnit()) &&
          this.getQuantity() == newStep.getQuantity() &&
          this.getDescription().equals(newStep.getDescription());
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO steps (recipe_id, seq_no, ingredient_id, unit, quantity, description) VALUES (:recipeId, :sequenceNumber, :ingredientId, :unit, :quantity, :description);";
        this.id = (int) con.createQuery(sql, true)
          .addColumnMapping("recipe_id", "recipeId")
          .addColumnMapping("seq_no", "sequenceNumber")
          .addColumnMapping("ingredient_id", "ingredientId")
          .addParameter("recipeId", this.getRecipeId())
          .addParameter("sequenceNumber", this.getSequenceNumber())
          .addParameter("ingredientId", this.getIngredientId())
          .addParameter("unit", this.getUnit())
          .addParameter("quantity", this.getQuantity())
          .addParameter("description", this.getDescription())
          .executeUpdate()
          .getKey();
      }
    }

    public void updateParser(int recipeId, int sequenceNumber, int ingredientId, String unit, int quantity, String description) {

      String column = "";
      String value = "";

      if(!(this.getRecipeId() == recipeId || recipeId <= 0)) {
        column = "recipe_id";
        value = String.valueOf(recipeId);
        update(column, value);
      }

      if(!(this.getSequenceNumber() == sequenceNumber || sequenceNumber <= 0)) {
        column = "seq_no";
        value = String.valueOf(sequenceNumber);
        update(column, value);
      }

      if(!(this.getIngredientId() == ingredientId || ingredientId <= 0)) {
        column = "ingredient_id";
        value = String.valueOf(ingredientId);
        update(column, value);
      }

      if(!(this.getUnit().equals(unit) || unit.isEmpty() || unit == null)) {
        column = "unit";
        value = "'" + unit + "'";
        update(column, value);
      }

      if(!(this.getQuantity() == quantity || quantity <= 0)) {
        column = "quantity";
        value = String.valueOf(quantity);
        update(column, value);
      }

      if(!(this.getDescription().equals(description) || description.isEmpty() || description == null)) {
        column = "description";
        value = "'" + description + "'";
        update(column, value);
      }

    }

    public void update(String column, String value){
      try (Connection con = DB.sql2o.open()){
        String sql = String.format("UPDATE steps SET %s = %s WHERE id = :id;", column, value);
        con.createQuery(sql)
          .addParameter("id", this.id)
          .executeUpdate();
      }
    }

    public void delete() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM steps WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", this.getId())
          .executeUpdate();
      }
    }
}
