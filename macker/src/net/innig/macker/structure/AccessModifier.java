/*______________________________________________________________________________
 *
 * Macker   http://innig.net/macker/
 *
 * Copyright 2002 Paul Cantrell
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2, as published by the
 * Free Software Foundation. See the file LICENSE.html for more information.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY, including the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the license for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc. / 59 Temple
 * Place, Suite 330 / Boston, MA 02111-1307 / USA.
 *______________________________________________________________________________
 */
 
package net.innig.macker.structure;

import net.innig.util.GraphType;

public class AccessModifier
    extends GraphType
    {
    public static final AccessModifier
        PRIVATE   = new AccessModifier("private"),
        PACKAGE   = new AccessModifier("package", PRIVATE),
        PROTECTED = new AccessModifier("protected", PRIVATE),
        PUBLIC    = new AccessModifier("public", new AccessModifier[] { PROTECTED, PACKAGE} );
    
    public boolean isLooserEq(AccessModifier that)
        { return this.is(that); }
    
    public boolean isTighterEq(AccessModifier that)
        { return that.is(this); }
    
    private AccessModifier(String name) { super(name); }
    private AccessModifier(String name, AccessModifier parent) { super(name, parent); }
    private AccessModifier(String name, AccessModifier[] parents) { super(name, parents); }
    }