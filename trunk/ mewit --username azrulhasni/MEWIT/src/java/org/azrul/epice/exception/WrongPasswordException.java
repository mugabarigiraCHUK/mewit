/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.exception;

/**
 *
 * @author azrulm
 */
public class WrongPasswordException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public WrongPasswordException(){

    }

    public WrongPasswordException(Exception e){
        super(e);
    }
}
