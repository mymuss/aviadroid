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

/** Utilities to work with coordinates and geographical points */
public class CoordinateUtils {
    private final static int EARTH_RADIUS_NM = 3440;

    /**
     * Given two lat/long points where the first one is reference
     * calculate an equivalent polar point for the second one in relation
     * to the first one.
     *
     * Haversine formulae from:
     *   http://en.wikipedia.org/wiki/Haversine_formula
     *   http://www.movable-type.co.uk/scripts/latlong.html
     *   http://rosettacode.org/wiki/Haversine_formula
     *
     * @param reference a reference point (essentially the origin)
     * @param position  current position that will be returned as polar
     * @return polar point
     */
    public static PointPolar calculatePolarPoint(final PointLatLong reference, final PointLatLong position) {
        double dLat = Math.toRadians(position.getLatitude() - reference.getLatitude());
        double dLon = Math.toRadians(position.getLongitude() - reference.getLongitude());
        double refLat = Math.toRadians(reference.getLatitude());
        double ptLat = Math.toRadians(position.getLatitude());

        double va = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(refLat) * Math.cos(ptLat);
        double vc = 2 * Math.atan2(Math.sqrt(va), Math.sqrt(1 - va));

        PointPolar result = new PointPolar();
        result.setDistance(EARTH_RADIUS_NM * vc);

        // This is INITIAL bearing for the great circles path
        // Not going to work if distance between points is large enough!
        double vy = Math.sin(dLon) * Math.cos(ptLat);
        double vx = Math.cos(refLat) * Math.sin(ptLat) - Math.sin(refLat) * Math.cos(ptLat) * Math.cos(dLon);
        double bearing = Math.toDegrees(Math.atan2(vy, vx));
        result.setRadial(HeadingUtils.normalize(bearing));

        return result;
    }
}
