/*______________________________________________________________________________
 *
 * Current distribution and futher info:  http://innig.net/macker/
 *
 * Copyright 2002 Paul Cantrell
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License version 2.1, as published
 * by the Free Software Foundation. See the file LICENSE.html for more info.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY, including the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the license for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.
 * 59 Temple Place, Suite 330 / Boston, MA 02111-1307 / USA.
 *______________________________________________________________________________
 */
 
package net.innig.macker.rule;

import org.jdom.Element;

/**
    Indicates a structural exception in rules specification XML.
*/

public class RulesDocumentException
    extends RulesException
    {
    public RulesDocumentException(Element element, String message)
        {
        super("Error in rules document: " + message + " (Offending element: " + element + ')');
        this.element = element;
        }
    
    public final Element getElement()
        { return element; }
    
    private final Element element;
    }