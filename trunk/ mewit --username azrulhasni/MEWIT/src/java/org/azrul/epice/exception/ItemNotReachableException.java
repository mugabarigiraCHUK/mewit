/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.exception;

/**
 *
 * @author azrulhasni
 */
public class ItemNotReachableException extends RuntimeException{
    public ItemNotReachableException(){
        
    }

    public ItemNotReachableException(Exception e){
        super(e);
    }
}
