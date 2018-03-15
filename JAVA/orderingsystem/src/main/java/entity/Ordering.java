package entity;

public class Ordering {
    private Integer id;
    private Integer userId;
    private String name;

    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
