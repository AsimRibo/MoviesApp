/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asim2
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Comparable<Person> {
    @XmlTransient
    private int id;
    @XmlElement(name = "firstname")
    private String firstName;
    @XmlElement(name = "lastname")
    private String lastName;

    public Person() {
    }

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return id == ((Person) obj).id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        return hash;
    }
    
    
    @Override
    public int compareTo(Person person) {
        return firstName.compareTo(person.firstName);
    }
    
    public enum TypeOfPerson{
        ACTOR, DIRECTOR;
    }
    
    
}
