package com.booker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "stylist")
public class Stylist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "forename")
    private String forename;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;

    public Stylist() {
    }

    public Stylist(final String forename, final String surname, final String email) {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("[Stylist: %s, %s, %s, %s.]", id, forename, surname, email);
    }
}
