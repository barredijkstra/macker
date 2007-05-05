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
 
package net.innig.macker.rule.filter;

import net.innig.macker.rule.EvaluationContext;
import net.innig.macker.rule.Pattern;
import net.innig.macker.rule.RuleSet;
import net.innig.macker.rule.RulesException;
import net.innig.macker.structure.AccessModifier;
import net.innig.macker.structure.ClassInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AccessFilter
    implements Filter
    {
    public Pattern createPattern(
            RuleSet ruleSet,
            List<Pattern> params,
            Map<String,String> options)
        throws RulesException
        {
        if(params.size() != 0)
            throw new FilterSyntaxException(
                this,
                "Filter \"" + options.get("filter") + "\" expects no parameters, but has " + params.size());

        String
            maxS = options.get("max"),
            minS = options.get("min");
        final AccessModifier
            max = (maxS != null) ? AccessModifier.valueOf(maxS.toUpperCase()) : AccessModifier.PUBLIC,
            min = (minS != null) ? AccessModifier.valueOf(minS.toUpperCase()) : AccessModifier.PRIVATE;
            
        if(maxS == null && minS == null)
            throw new FilterSyntaxException(
                this, options.get("filter") + " requires a \"max\" or \"min\" option (or both)");
        if(max == null && maxS != null)
            throw new FilterSyntaxException(
                this, 
                '"' + maxS + "\" is not a valid access level; expected one of: "
                + Arrays.asList(AccessModifier.values()));
        if(min == null && minS != null)
            throw new FilterSyntaxException(
                this, 
                '"' + minS + "\" is not a valid access level; expected one of: "
                + Arrays.asList(AccessModifier.values()));
        
        return new Pattern()
            {
            public boolean matches(EvaluationContext context, ClassInfo classInfo)
                throws RulesException
                {
                return classInfo.getAccessModifier().compareTo(min) >= 0
                    && classInfo.getAccessModifier().compareTo(max) <= 0;
                }
            };
        }
    }