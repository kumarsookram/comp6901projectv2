package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String product_name;
    private double price;
    private int daily_capacity;

    public Product(String product_name, double price, int daily_capacity) {
        this.product_name = product_name;
        this.price = price;
        this.daily_capacity = daily_capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public int getDaily_capacity() {
        return daily_capacity;
    }
}
