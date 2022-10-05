package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk_SucursalID;

    @Column(name = "nom")
    private String nomSucursal;

    @Column(name = "pais")
    private String paisSucursal;

    public Sucursal() {
    }

    public Sucursal(String nomSucursal, String paisSucursal) {
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
    }

    public long getPk_SucursalID() {
        return pk_SucursalID;
    }

    public void setPk_SucursalID(long pk_SucursalID) {
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
