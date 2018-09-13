package domain.pets;

public class Husky extends Dog {
    private String Hair = "Black";

    private String Tooth = "White";

    public String getHair() {
        return Hair;
    }

    public void setHair(String hair) {
        Hair = hair;
    }

    public String getTooth() {
        return Tooth;
    }

    public void setTooth(String tooth) {
        Tooth = tooth;
    }
}
