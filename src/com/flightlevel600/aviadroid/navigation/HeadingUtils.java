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

/** Various utilities to deal with headings */
public class HeadingUtils {
    // cardinal headings
    public static final int CARDINAL_NORTH          = 0;
    public static final int CARDINAL_EAST           = 90;
    public static final int CARDINAL_SOUTH          = 180;
    public static final int CARDINAL_WEST           = 270;

    // full circle on a compass card
    public static final int FULL_CIRCLE             = 360;

    // I wonder who would use Integer.MIN_VALUE as a real heading
    // but this is what a normalized heading for Integer.MIN_VALUE
    // should be
    private static final int NORMALIZED_HDG_MIN_INT = 232;

    // numerical difference between direct and reciprocal headings
    private static final int RECIPROCAL_DELTA       = 180;

    // this is how headings should be formatted in strings
    private static final String HEADING_FORMAT      = "%03d";

    // string representation of cardinal North heading
    private static final String ZERO_HEADING        = "360";

    // to simplify cardinal direction calculations we'll
    // determine the relevant octant, the following constants
    // help identify current octant on the standard compass card
    private static final int DEG_PER_OCTANT         = 45;
    private static final int FIRST_OCTANT_OFFSET    = 23;

    /**
     * Normalize int heading value so that it falls within 0-359 range
     *
     * @param heading heading value
     * @return heading value in 0-359 range
     */
    public static int normalize(final int heading) {
        if (heading < 0) {
            // just to handle MIN_VALUE
            // doubt it's ever going to be used
            if (Integer.MIN_VALUE == heading) {
                return NORMALIZED_HDG_MIN_INT;
            }
            return normalize(FULL_CIRCLE - normalize(-1 * heading));
        }
        if (heading > FULL_CIRCLE - 1) {
            return heading - FULL_CIRCLE * (int)Math.floor(heading / FULL_CIRCLE);
        }

        return heading;
    }

    /**
     * Normalize double heading value so that it falls within 0-359 range
     *
     * @param heading heading value, must be in the range (-2^31, 2^31-1)
     * @return heading value in 0-359 range
     */
    public static int normalize(final double heading) {
        long longHeading = Math.round(heading);
        if (longHeading > Integer.MAX_VALUE || (longHeading < Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("Input heading is outside of the supported range: " + heading);
        }
        return normalize((int)longHeading);
    }

    /**
     * Get a random valid heading value
     *
     * @return heading 0-359
     */
    public static int random() {
        return (int)Math.round((FULL_CIRCLE - 1) * Math.random());
    }

    /**
     * Calculate reciprocal heading
     *
     * @param heading original heading
     * @return reciprocal heading
     */
    public static int reciprocal(final int heading) {
        return normalize(heading + RECIPROCAL_DELTA);
    }

    /**
     * Format integer heading as string for display. Pads with leading zeroes for 3 digits.
     * Cardinal Northbound heading returned as 360, not 000 to be consistent with ATC.
     *
     * @param heading integer heading
     * @return heading as string
     */
    public static String format(final int heading) {
        int normalizedHeading = normalize(heading);
        if (0 == normalizedHeading) {
            return ZERO_HEADING;
        }
        return String.format(HEADING_FORMAT, normalizedHeading);
    }

    /**
     * Returns the nearest cardinal direction for given radial
     *
     * @param radial integer radial
     * @return cardinal direction
     */
    public static ECaridinalDirection nearestCaridinal(final int radial) {
        int octant = normalize(radial - FIRST_OCTANT_OFFSET) / DEG_PER_OCTANT;
        switch (octant) {
            case 0: return ECaridinalDirection.NORTHEAST;
            case 1: return ECaridinalDirection.EAST;
            case 2: return ECaridinalDirection.SOUTHEAST;
            case 3: return ECaridinalDirection.SOUTH;
            case 4: return ECaridinalDirection.SOUTHWEST;
            case 5: return ECaridinalDirection.WEST;
            case 6: return ECaridinalDirection.NORTHWEST;
            default: return ECaridinalDirection.NORTH;
        }
    }
}
