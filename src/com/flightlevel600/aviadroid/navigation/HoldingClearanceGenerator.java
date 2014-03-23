/*
 * AviaDroid
 * Copyright (C) 2014  Andrew Novikov
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.flightlevel600.aviadroid.navigation;

import android.content.Context;
import com.flightlevel600.aviadroid.R;


/** Generates a holding clearance */
public class HoldingClearanceGenerator {
    private Context context;
    private String[] callsigns;

    /**
     * Constructor. Context is required to access resources
     *
     * @param context current context
     */
    public HoldingClearanceGenerator(final Context context) {
        this.context = context;
    }

    /**
     * Generates a holding clearance.
     *
     * @param radial radial to hold on
     * @param turns  direction of turns in the hold
     * @return full clearance
     */
    public String generate(final int radial, final EHoldingPatternTurns turns) {
        ECaridinalDirection cardinalDirection = HeadingUtils.nearestCaridinal(radial);

        return String.format(getResourceString(R.string.holding_clearance_pattern),
                             getRandomCallsign(),
                             getResourceString(R.string.fix_name),
                             getResourceString(cardinalDirection.getResourceId()),
                             HeadingUtils.format(radial),
                             getResourceString(turns.getResourceId()));
    }

    /**
     * Get a random callsign from the array of strings in the resources.
     *
     * @return callsign
     */
    private String getRandomCallsign() {
        if ((null == callsigns) || (0 == callsigns.length)) {
            callsigns = context.getResources().getStringArray(R.array.callsigns_arr);
        }
        if ((null == callsigns) || (0 == callsigns.length)) {
            throw new IllegalStateException("Callsigns not found in the resources");
        }
        int idx = (int)Math.round(Math.random() * (callsigns.length - 1));
        return callsigns[idx];
    }

    /**
     * Helper method for getting string resources
     *
     * @param resourceId resource id
     * @return string resource value for given id
     */
    private String getResourceString(final int resourceId) {
        return context.getResources().getString(resourceId);
    }
}
