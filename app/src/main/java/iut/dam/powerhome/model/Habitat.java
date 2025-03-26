package iut.dam.powerhome.model;
import java.util.ArrayList;
import java.util.List;
import iut.dam.powerhome.model.Appliance;

public class Habitat {
    private int id;
    private String residentName;
    private int floor;
    private double area;
    private List<Appliance> appliances;
    private String urlString = "http:// [IP] /powerhome_server/getHabitats.php";


    // Constructeur
    public Habitat(int id, String residentName, int floor, double area, List<Appliance> appliances) {
        this.id = id;
        this.residentName = residentName;
        this.floor = floor;
        this.area = area;
        this.appliances = appliances;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getResidentName() {
        return residentName;
    }

    public int getFloor() {
        return floor;
    }

    public double getArea() {
        return area;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    // Setters (optionnels)
    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setAppliances(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    @Override
    public String toString() {
        return residentName + " - Étage " + floor + " (" + area + " m²)";
    }

}
