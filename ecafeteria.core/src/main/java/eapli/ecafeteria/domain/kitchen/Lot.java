/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * @author 1161569
 */
@Entity
public class Lot implements Serializable, AggregateRoot<String> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String code;

    @ManyToOne()
    private Material material;

    public Lot(String code, Material material) {
         if (Strings.isNullOrEmpty(code)) {
            throw new IllegalArgumentException();
        }
        this.material = material;
        this.code = code + "//" + this.material.id();
    }

    public Material material(){
        return material;
    }
    
    protected Lot() {
        //for ORM
    }
    
     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lot lot = (Lot) o;

        if (!material.equals(lot.material())) {
            return false;
        }
    
        return code.equals(lot.id());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.code);
        hash = 89 * hash + Objects.hashCode(this.material);
        return hash;
    }

    public String getCode(){
        return this.code;
    }
    
    @Override
    public boolean sameAs(Object other) {
         if (!(other instanceof Lot)) {
            return false;
        }

        final Lot that = (Lot) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && material.equals(that.material);
    }

    @Override
    public String id() {
        return code;
    }
    
    @Override
    public String toString(){
        return String.format("Code: %s\nMaterial: %s\n", code,material.id());
    }
}
