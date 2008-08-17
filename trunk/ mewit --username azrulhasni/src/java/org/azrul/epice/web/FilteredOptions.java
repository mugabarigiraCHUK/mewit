/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the license at
 * https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 */
package org.azrul.epice.web;

import com.sun.webui.jsf.model.Option;
import com.sun.webui.jsf.model.OptionsList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.azrul.epice.domain.Person;

/** A default list of options, pre-populated with three default items.
 *
 * @author dkushner
 */
public class FilteredOptions extends OptionsList {

    Option[] options = new Option[0]; //returned filtered options, built from props

    public FilteredOptions(Set<Person> persons) {
        filter(persons, null);//initial data creation
    }

    public boolean filter(Set<Person> persons, String filter) {
        List<Option> filteredValues = new ArrayList<Option>();
        if (filter != null) {
            filter = filter.toLowerCase();
            for (Person p : persons) {
                String email = p.getEmail();
                if (filter == null || filter.length() == 0 || email.toLowerCase().indexOf(filter) >= 0) {
                    filteredValues.add(new Option(email, email));
                }
            }
        }else{
            for (Person p : persons) {
                if (p!= null){
                    String email = p.getEmail();
                    filteredValues.add(new Option(email, email));
                }
            }
        }


        //create new option list
        options = new Option[filteredValues.size()];
        int counter  = 0;
        for (Option o : filteredValues) {
            options[counter++] = o;            
        }
        return true;
    }

    @Override
    public Option[] getOptions() {
        return options;
    }

    private void next() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
