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

/** Lat/Long coordinate point */
public class PointLatLong {
    public double latitude;
    public double longitude;

    public PointLatLong() {}

    public PointLatLong(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public PointLatLong setLatitude(final double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public PointLatLong setLongitude(final double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof PointPolar)) {
            return false;
        }

        PointLatLong pp = (PointLatLong)obj;
        return (pp.getLatitude() == this.latitude) && (pp.getLongitude() == this.longitude);
    }

    @Override
    public int hashCode() {
        int result = 41;
        long dlat = Double.doubleToLongBits(latitude);
        result = 31 * result + (int)(dlat ^ (dlat >>> 32));
        long dlon = Double.doubleToLongBits(longitude);
        result = 31 * result + (int)(dlon ^ (dlon >>> 32));;

        return result;
    }
}
