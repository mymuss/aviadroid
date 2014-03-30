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

/** Represents an indication on a CDI */
public class CdiIndication {
    private int obs;
    private double deflection; // in number of dots on the CDI, negative for left deflection
    private ECdiDirection direction; // to/from arrow

    public CdiIndication() { }

    public CdiIndication(final int obs, final double deflection, final ECdiDirection direction) {
        this.obs        = obs;
        this.deflection = deflection;
        this.direction  = direction;
    }

    public double getDeflection() {
        return deflection;
    }

    public CdiIndication setDeflection(final double deflection) {
        this.deflection = deflection;
        return this;
    }

    public ECdiDirection getDirection() {
        return direction;
    }

    public CdiIndication setDirection(final ECdiDirection direction) {
        this.direction = direction;
        return this;
    }

    public int getObs() {
        return obs;
    }

    public CdiIndication setObs(final int obs) {
        this.obs = obs;
        return this;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof CdiIndication)) {
            return false;
        }

        CdiIndication ci = (CdiIndication)obj;

        return ci.getDeflection() == this.deflection &&
               ci.getObs() == this.obs &&
               (null == this.direction ? null == ci.getDirection() : this.direction.equals(ci.getDirection()));
    }

    @Override
    public int hashCode() {
        int result = 24;
        long dl = Double.doubleToLongBits(deflection);
        result = 31 * result + (int)(dl ^ (dl >>> 32));
        result = 31 * result + obs;
        if (null != direction) {
            result = 31 * result + direction.ordinal();
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("CdiIndication[OBS:%d, deflection:%f, %s]", obs, deflection, direction);
    }
}
