package com.example.nserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MapStocSrv")

public class MapStocOpt {
    @Id
    private int id;

    @Column(name="id_intern")
    private String idIntern;

    @Column(name = "articol")
    private String articol;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "grupa")
    private String grupa;

    @Column(name = "idFurn")
    private int id_furn;

    @Column(name = "furniz")
    private String furniz;

    @Column(name = "nrZile")
    private int nr_zile;

    public String toString(){
        return "id:"+id+";articol:"+articol.trim()+";id_furniz:"+id_furn+";furnizor:"+furniz.trim()+";nr_zile:"+nr_zile;
    }
}
