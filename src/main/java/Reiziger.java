import java.util.Date;

public class Reiziger {
    private int reizigerId;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;


    public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam) {
        this.reizigerId = reizigerId;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;

    }

    public int getReizigerId() {
        return reizigerId;
    }


    public String getVoorletters() {
        return voorletters;
    }


    public String getTussenvoegsel() {
        return tussenvoegsel;
    }


    public String getAchternaam() {
        return achternaam;
    }


    @Override
    public String toString() {
        return "Reiziger{" +
                "reizigerId=" + reizigerId +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                '}';
    }
}
