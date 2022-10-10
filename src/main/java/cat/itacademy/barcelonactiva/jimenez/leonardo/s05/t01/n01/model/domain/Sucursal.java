package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk_SucursalID;

    @NotBlank(message = "Nom is mandatory")
    @Column(name = "nom")
    private String nomSucursal;

    @NotBlank(message = "Pais is mandatory")
    @Column(name = "pais")
    private String paisSucursal;

    public Sucursal() {
    }

    public Sucursal(String nomSucursal, String paisSucursal) {
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
    }

    public Integer getPk_SucursalID() {
        return pk_SucursalID;
    }

    public void setPk_SucursalID(Integer pk_SucursalID) {
        this.pk_SucursalID = pk_SucursalID;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getPaisSucursal() {
        return paisSucursal;
    }

    public void setPaisSucursal(String paisSucursal) {
        this.paisSucursal = paisSucursal;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "pk_SucursalID=" + pk_SucursalID +
                ", nomSucursal='" + nomSucursal + '\'' +
                ", paisSucursal='" + paisSucursal + '\'' +
                '}';
    }
}
