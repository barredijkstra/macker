/*______________________________________________________________________________
 *
 * Macker   http://innig.net/macker/
 *
 * Copyright 2002-2003 Paul Cantrell
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

import net.innig.collect.GraphWalker;
import net.innig.collect.Graphs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractClassInfo
    implements ClassInfo
    {
    public AbstractClassInfo(ClassManager classManager)
        { this.classManager = classManager; }
    
    public String getClassName()
        {
        String className = getFullName();
        return className.substring(className.lastIndexOf('.') + 1);
        }
        
    public String getPackageName()
        {
        String className = getFullName();
        int lastDotPos = className.lastIndexOf('.');
        return (lastDotPos > 0) ? className.substring(0, lastDotPos) : "";
        }
    
    public Set<ClassInfo> getDirectSupertypes()
        {
        if(cachedAllDirectSuper == null)
            {
            Set<ClassInfo> newAllDirectSuper = new HashSet<ClassInfo>(getImplements());
            newAllDirectSuper.add(getExtends());
            cachedAllDirectSuper = newAllDirectSuper; // failure atomicity
            }
        return cachedAllDirectSuper;
        }
    
    public Set<ClassInfo> getSupertypes()
        {
        if(cachedAllSuper == null)
            cachedAllSuper = Graphs.reachableNodes(
                this,
                new GraphWalker<ClassInfo>()
                    {
                    public Collection<ClassInfo> getEdgesFrom(ClassInfo node)
                        { return node.getDirectSupertypes(); }
                    } );
        return cachedAllSuper;
        }
    
    public final ClassManager getClassManager()
        { return classManager; }
    
    public final boolean equals(Object that)
        {
        if(this == that)
            return true;
        if(that == null)
            return false;
        if(!(that instanceof ClassInfo))
            return false;
        return getFullName().equals(((ClassInfo) that).getFullName());
        }
    
    public final int hashCode()
        { return getFullName().hashCode(); }
    
    public String toString()
        { return getFullName(); }
    
    private ClassManager classManager;
    private Set<ClassInfo> cachedAllSuper, cachedAllDirectSuper;
    }

