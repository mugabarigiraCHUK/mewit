/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.exception;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Azrul
 */
public class RuleNonCompliantException extends RuntimeException {
    private List<String> errors = new ArrayList<String>();
    public RuleNonCompliantException(Exception e){
        super(e);
    }
    public RuleNonCompliantException(List<String> errors){
        this.errors = errors;
    }

    /**
     * @return the errors
     */
    public List<String> getErrors() {
        return errors;
    }
}
