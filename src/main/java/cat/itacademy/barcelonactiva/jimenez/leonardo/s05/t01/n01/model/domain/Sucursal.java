package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nom")
    private String nomSucursal;

    @Column(name = "pais")
    private int paisSucursal;

    public Sucursal() {
    }

    public Sucursal(String nomSucursal, int paisSucursal) {
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public int getPaisSucursal() {
        return paisSucursal;
    }

    public void setPaisSucursal(int paisSucursal) {
        this.paisSucursal = paisSucursal;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nomSucursal='" + nomSucursal + '\'' +
                ", paisSucursal=" + paisSucursal +
                '}';
    }
}
