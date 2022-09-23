/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import hr.algebra.model.Person.TypeOfPerson;

/**
 *
 * @author asim2
 */
public interface PersonEditable {
    boolean editPerson(Person person, TypeOfPerson personType, Person newDetails) throws Exception;
}
