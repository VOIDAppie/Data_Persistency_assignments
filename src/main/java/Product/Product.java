package Product;

import ovchipkaart.OvChipKaart;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;

    private List<Integer> ovChipkaarten_ids = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product() {

    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

//    public List<OvChipKaart> getOVChipkaarten() {
//        return ovChipkaarten;
//    }
//
//    public void addOVChipkaart(OvChipKaart ovChipkaart) {
//        this.ovChipkaarten.add(ovChipkaart);
//    }
//
//    public void removeOVChipkaart(OvChipKaart ovChipkaart) {
//        this.ovChipkaarten.remove(ovChipkaart);
//    }

    public List<Integer> getOvChipkaarten_ids() {
        return ovChipkaarten_ids;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_nummer=" + product_nummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +

                '}';
    }
}
