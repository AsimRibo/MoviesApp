/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 *
 * @author asim2
 */
public class NumberUtils {
    
    public static boolean isPositiveInteger(String data){
        if (data.isEmpty()) {
            return false;
        }
        CharacterIterator iterator = new StringCharacterIterator(data);
        while (iterator.current() != CharacterIterator.DONE) {
            if (!Character.isDigit(iterator.current())) {
                return false;
            }
            iterator.next();
            
        }
        return true;
    }
}
