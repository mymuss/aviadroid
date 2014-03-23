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

/**
 * Radial and distance coordinate point
 * This is essentially equivalent to a point in polar coordinates
 */
public class PointPolar {
    private int radial;
    private double distance; // in nautical miles

    public PointPolar() { }

    public PointPolar(final int radial, final double distance) {
        this.radial = radial;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public PointPolar setDistance(final double distance) {
        this.distance = distance;
        return this;
    }

    public int getRadial() {
        return radial;
    }

    public PointPolar setRadial(final int radial) {
        this.radial = radial;
        return this;
    }
}
