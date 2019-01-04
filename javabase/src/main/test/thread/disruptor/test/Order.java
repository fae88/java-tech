package thread.disruptor.test;

public class Order {

    String id;

    int price;

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
