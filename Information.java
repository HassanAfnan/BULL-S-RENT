package change.com.animationwithsplash;

public class Information {
    private String Name;
    private String Car;
    private String Model;
    private String Rent;

    public Information() {
    }

    public Information(String name, String car, String model, String rent) {
        Name = name;
        Car = car;
        Model = model;
        Rent = rent;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCar() {
        return Car;
    }

    public void setCar(String car) {
        Car = car;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getRent() {
        return Rent;
    }

    public void setRent(String rent) {
        Rent = rent;
    }
}
