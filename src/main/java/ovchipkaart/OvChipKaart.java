package ovchipkaart;

import Product.Product;
import reiziger.Reiziger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OvChipKaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private double saldo;
    private int reiziger_id;

    private List<Integer> producten_id = new ArrayList<>();

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    // Constructor
    public OvChipKaart(int kaart_nummer, Date geldig_tot, int klasse, double saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger.getReiziger_id();

    }

    // Getters and Setters
    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void setReiziger(Reiziger reiziger) {
        this.reiziger_id = reiziger.getReiziger_id();
    }

//    public List<Product> getProducten() {
//        return producten;
//    }
//
//    public void addProduct(Product product) {
//        this.producten.add(product);
//    }
//
//    public void removeProduct(Product product) {
//        this.producten.remove(product);
//    }


    @Override
    public String toString() {
        return "OvChipKaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger_id=" + reiziger_id +
                '}';
    }
}

