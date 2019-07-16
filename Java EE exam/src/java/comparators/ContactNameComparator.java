/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparators;

import java.util.Comparator;
import pojos.Contact;

/**
 *
 * @author Daniel Varga - vargadaniel81@gmail.com
 */
public class ContactNameComparator implements Comparator<Contact>{

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
    
}
