package reiziger;

import adres.Adres;
import ovchipkaart.OvChipKaart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Adres adres;

    List<OvChipKaart> chipKaarten = new ArrayList<>();


    private java.sql.Date geboortedatum;


    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Adres adres, List<OvChipKaart> chipKaarten, java.sql.Date geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.adres = adres;
        this.chipKaarten = chipKaarten;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = reizigerId;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum= (java.sql.Date) geboortedatum;

    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public java.sql.Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(java.sql.Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString() {
        return "reiziger.Reiziger{" +
                "reizigerId=" + reiziger_id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                '}';
    }
}
