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

/** Utility methods to work with VOR navigation */
public class VorUtils {
    // 5 dots is max deflection on a standard CDI
    // anything above that produces full scale deflection
    private final static double FULL_DEFLECTION = 5.1;

    // number of degrees per one dot deflection
    private final static double DEGREES_PER_DOT = 2.0;

    // cardinal positions on the compass card
    private final static int ABEAM_POINT        = 90;

    /**
     * Get current CDI indication
     *
     * @param obs    current setting of the OBS, in degrees heading
     * @param radial radial that aircraft is currently on
     * @return CdiIndication object
     */
    public static CdiIndication getIndication(final int obs, final int radial) {
        int normObs    = HeadingUtils.normalize(obs);
        int normRadial = HeadingUtils.normalize(radial);

        ECdiDirection direction = ECdiDirection.FROM;
        int signedAngle = normObs - normRadial;
        int angle = Math.abs(signedAngle);
        if (HeadingUtils.HALF_CIRCLE < angle) {
            angle = HeadingUtils.FULL_CIRCLE - angle;
            signedAngle = angle * (int)Math.signum(signedAngle) * -1;
        }

        double deflection = signedAngle / DEGREES_PER_DOT;
        if (ABEAM_POINT > angle) {
            // same general direction
            direction  = ECdiDirection.FROM;
        } else if (ABEAM_POINT == angle) {
            // VOR direction arrow doesn't work when abeam the station
            direction  = ECdiDirection.FLAG;
        } else if (HeadingUtils.HALF_CIRCLE >= angle) {
            // opposite general direction
            direction  = ECdiDirection.TO;
            angle = HeadingUtils.HALF_CIRCLE - angle;
            signedAngle = angle * (int)Math.signum(signedAngle) * -1;
            deflection = signedAngle / DEGREES_PER_DOT;
        } else {
            assert false : "Angle cannot be more than 180 degrees";
        }

        return new CdiIndication(normObs, deflection, direction);
    }
}
