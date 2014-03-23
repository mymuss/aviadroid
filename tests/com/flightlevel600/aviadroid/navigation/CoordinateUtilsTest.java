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

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/** Unit tests for the CoordinateUtils class */
public class CoordinateUtilsTest {
    @Test
    public void calculatePolarPoint() {
        // Northern/Western hemispheres
        PointLatLong kbos = new PointLatLong(42.3629722, -71.0064167);
        PointLatLong kjfk = new PointLatLong(40.6397511, -73.7789256);
        PointPolar jfkToBos = CoordinateUtils.calculatePolarPoint(kbos, kjfk);
        assertEquals("JFK to BOS distance", 162, Math.round((float) jfkToBos.getDistance()));
        assertEquals("JFK to BOS radial", 231, jfkToBos.getRadial());

        // Northern/Eastern hemispheres
        PointLatLong rjtt = new PointLatLong(35.552258, 139.779694); // Tokyo, Japan
        PointLatLong rjoo = new PointLatLong(34.785528, 135.438222); // Osaka, Japan
        PointPolar rjttToRjoo = CoordinateUtils.calculatePolarPoint(rjoo, rjtt);
        assertEquals("Tokyo (RJTT) to Osaka (RJOO) distance", 218, Math.round((float)rjttToRjoo.getDistance()));
        assertEquals("Tokyo (RJTT) to Osaka (RJOO) radial", 77, rjttToRjoo.getRadial());

        // Southern/Western hemispheres
        PointLatLong sbgl = new PointLatLong(-22.808903, -43.243647); // Rio De Janeiro, Brazil
        PointLatLong sbsp = new PointLatLong(-23.626692, -46.655375); // San Paulo, Brazil
        PointPolar sbglToSbsp = CoordinateUtils.calculatePolarPoint(sbsp, sbgl);
        assertEquals("Rio (SBGL) to San Paulo (SBSP) distance", 195, Math.round((float)sbglToSbsp.getDistance()));
        assertEquals("Rio (SBGL) to San Paulo (SBSP) radial", 76, sbglToSbsp.getRadial());

        // Southern/Eastern hemispheres
        PointLatLong ymml = new PointLatLong(-37.673333, 144.843333); // Melbourne, Australia
        PointLatLong yssy = new PointLatLong(-33.946111, 151.177222); // Sydney, Australia
        PointPolar ymmlToyssy = CoordinateUtils.calculatePolarPoint(yssy, ymml);
        assertEquals("Melbourne (YMML) to Sydney (YSSY) distance", 381, Math.round((float)ymmlToyssy.getDistance()));
        assertEquals("Melbourne (YMML) to Sydney (YSSY) radial", 232, ymmlToyssy.getRadial());

        // Across hemispheres
        PointLatLong gmmx = new PointLatLong(31.606886, -8.036300); // Marrakech, Morocco
        PointLatLong htkj = new PointLatLong(-3.429406, 37.074461); // Kilimanjaro, Tanzania
        PointPolar gmmxToHtkj = CoordinateUtils.calculatePolarPoint(htkj, gmmx);
        assertEquals("Marrakech (GMMX) to Kilimanjaro (HTKJ) distance", 3323, Math.round((float)gmmxToHtkj.getDistance()));
        assertEquals("Melbourne (YMML) to Sydney (YSSY) radial", 313, gmmxToHtkj.getRadial());

        // Same point
        PointPolar bosToBos = CoordinateUtils.calculatePolarPoint(kbos, kbos);
        assertEquals("BOS to BOS distance", 0, Math.round((float)bosToBos.getDistance()));
        assertEquals("BOS to BOS radial", 0, bosToBos.getRadial());

        // All zeros
        PointPolar allZeros = CoordinateUtils.calculatePolarPoint(new PointLatLong(0.0, 0.0), new PointLatLong(0.0, 0.0));
        assertEquals("all zeros distance", 0, Math.round((float)allZeros.getDistance()));
        assertEquals("all zeros radial", 0, allZeros.getRadial());
    }
}
