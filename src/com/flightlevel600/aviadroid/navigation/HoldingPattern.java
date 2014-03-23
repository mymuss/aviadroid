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

import java.util.EnumSet;


/** Calculate holding patter entry */
public class HoldingPattern {
    // Magic numbers definitions for entry calculation
    private static final int HDG_MIN_DIRECT_1 = 0;
    private static final int HDG_MAX_DIRECT_1 = 115;
    private static final int HDG_MIN_DIRECT_2 = 285;
    private static final int HDG_MAX_DIRECT_2 = 359;
    private static final int HDG_MIN_TEARDROP = 105;
    private static final int HDG_MAX_TEARDROP = 185;
    private static final int HDG_MIN_PARALLEL = 175;
    private static final int HDG_MAX_PARALLEL = 295;

    /**
     * Calculate recommended holding pattern entry. This algorithm allows
     * for choosing entry if within +/- 5 degrees per AIM 5-3-14 (6)(d)
     *
     * @param radial         radial where the aircraft currently is
     * @param inboundHeading heading on the inbound leg
     * @param turns          direction of turns
     * @return set of recommended entries
     */
    public static EnumSet<EHoldingPatternEntry> calculateEntry(final int radial,
                                                               final int inboundHeading,
                                                               final EHoldingPatternTurns turns) {
        int inboundRadial = HeadingUtils.reciprocal(inboundHeading);
        int angularDiff = radial + HeadingUtils.FULL_CIRCLE - inboundRadial; // clockwise on the compass card

        if (EHoldingPatternTurns.LEFT.equals(turns)) {
            // left turns is mirrored
            angularDiff = HeadingUtils.FULL_CIRCLE - angularDiff;
        }
        angularDiff = HeadingUtils.normalize(angularDiff);

        EnumSet<EHoldingPatternEntry> entries = EnumSet.noneOf(EHoldingPatternEntry.class);
        if ((angularDiff >= HDG_MIN_DIRECT_1 && angularDiff <= HDG_MAX_DIRECT_1) ||
            (angularDiff >= HDG_MIN_DIRECT_2 && angularDiff <= HDG_MAX_DIRECT_2)) {
            entries.add(EHoldingPatternEntry.DIRECT);
        }
        if (angularDiff >= HDG_MIN_TEARDROP && angularDiff <= HDG_MAX_TEARDROP) {
            entries.add(EHoldingPatternEntry.TEARDROP);
        }
        if (angularDiff >= HDG_MIN_PARALLEL && angularDiff <= HDG_MAX_PARALLEL) {
            entries.add(EHoldingPatternEntry.PARALLEL);
        }

        // there can only be one or two valid patter entries for any heading
        assert entries.size() >= 1;
        assert entries.size() <= 2;

        return entries;
    }

    /**
     * Calculate recommended holding pattern entry. This algorithm allows
     * for choosing entry if within +/- 5 degrees per AIM 5-3-14 (6)(d)
     *
     * @param fix            holding fix coordinates
     * @param position       current position of the aircraft
     * @param inboundHeading heading on the inbound leg
     * @param turns          direction of turns
     * @return set of recommended entries
     */
    public static EnumSet<EHoldingPatternEntry> calculateEntry(final PointLatLong fix,
                                                               final PointLatLong position,
                                                               final int inboundHeading,
                                                               final EHoldingPatternTurns turns) {
        PointPolar pointPolar = CoordinateUtils.calculatePolarPoint(fix, position);
        return calculateEntry(pointPolar.getRadial(), inboundHeading, turns);
    }
}
