package ma.ensaf.pharmacie.model;

import javax.persistence.*;

@Entity
@Table(name = "detail_produit")
public class DetailProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fabricant;
    private String paysOrigine;
    private String composition;
    private String dosage;
    private String indications;

    public DetailProduit() {
    }

    public DetailProduit(String fabricant, String paysOrigine, String composition, String dosage, String indications) {
        this.fabricant = fabricant;
        this.paysOrigine = paysOrigine;
        this.composition = composition;
        this.dosage = dosage;
        this.indications = indications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public String getPaysOrigine() {
        return paysOrigine;
    }

    public void setPaysOrigine(String paysOrigine) {
        this.paysOrigine = paysOrigine;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }
}
