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

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;


/** Unit tests for the HoldingPattern class */
public class HoldingPatternTest {
    @Test
    public void calculateEntryForRadial() {
        assertEquals("right turns, non cardinal, on the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(225, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(226, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(224, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(240, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(210, 45, EHoldingPatternTurns.RIGHT));

        assertEquals("right turns, non cardinal, 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(335, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left within 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(336, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right within 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(334, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left more than 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(350, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right more than 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(320, 45, EHoldingPatternTurns.RIGHT));

        assertEquals("right turns, non cardinal, 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(45, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(46, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(44, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(60, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(30, 45, EHoldingPatternTurns.RIGHT));

        assertEquals("right turns, non cardinal, 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(155, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left within 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(156, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right within 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(154, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, left more than 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(170, 45, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, non cardinal, right more than 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(140, 45, EHoldingPatternTurns.RIGHT));

        assertEquals("left turns, non cardinal, on the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(225, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(226, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(224, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(240, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(210, 45, EHoldingPatternTurns.LEFT));

        assertEquals("left turns, non cardinal, 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(295, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left within 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(296, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right within 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(294, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left more than 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(310, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right more than 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(280, 45, EHoldingPatternTurns.LEFT));

        assertEquals("left turns, non cardinal, 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(45, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(46, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(44, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(60, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(30, 45, EHoldingPatternTurns.LEFT));

        assertEquals("left turns, non cardinal, 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(115, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left within 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(116, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right within 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(114, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, left more than 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(130, 45, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, non cardinal, right more than 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(100, 45, EHoldingPatternTurns.LEFT));

        assertEquals("right turns, cardinal, on the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(180, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(181, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(179, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(195, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(165, 0, EHoldingPatternTurns.RIGHT));

        assertEquals("right turns, cardinal, 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(290, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left within 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(291, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right within 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(289, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left more than 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(305, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right more than 5 deg of the 110 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(275, 0, EHoldingPatternTurns.RIGHT));

        assertEquals("right turns, cardinal, 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(0, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(1, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(359, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(15, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(345, 0, EHoldingPatternTurns.RIGHT));

        assertEquals("right turns, cardinal, 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(110, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left within 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(111, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right within 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(109, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, left more than 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(125, 0, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, cardinal, right more than 5 deg of the 290 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(95, 0, EHoldingPatternTurns.RIGHT));

        assertEquals("left turns, cardinal, on the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(180, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(181, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right within 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(179, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(195, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right more than 5 deg of the inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(165, 0, EHoldingPatternTurns.LEFT));

        assertEquals("left turns, cardinal, 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(250, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left within 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(251, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right within 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(249, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left more than 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(265, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right more than 5 deg of the 70 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(235, 0, EHoldingPatternTurns.LEFT));

        assertEquals("left turns, cardinal, 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(0, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(1, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right within 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(359, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(15, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right more than 5 deg of the 180 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(345, 0, EHoldingPatternTurns.LEFT));

        assertEquals("left turns, cardinal, 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(70, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left within 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(71, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right within 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP, EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(69, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, left more than 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(85, 0, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, cardinal, right more than 5 deg of the 250 deg off inbound",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(55, 0, EHoldingPatternTurns.LEFT));
    }

    @Test
    public void calculateEntryForFixes() {
        PointLatLong bos = new PointLatLong(42.3574500, -70.9895472);
        PointLatLong lwm = new PointLatLong(42.7404156, -71.0948419);

        assertEquals("right turns, on the inbound for direct",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(bos, lwm, 168, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, 145 deg off the inbound for teardrop",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(bos, lwm, 23, EHoldingPatternTurns.RIGHT));
        assertEquals("right turns, 235 deg off the inbound for parallel",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(bos, lwm, 293, EHoldingPatternTurns.RIGHT));

        assertEquals("left turns, on the inbound for direct",
                     EnumSet.of(EHoldingPatternEntry.DIRECT),
                     HoldingPattern.calculateEntry(bos, lwm, 168, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, 125 deg off the inbound for parallel",
                     EnumSet.of(EHoldingPatternEntry.PARALLEL),
                     HoldingPattern.calculateEntry(bos, lwm, 43, EHoldingPatternTurns.LEFT));
        assertEquals("left turns, 215 deg off the inbound for teardrop",
                     EnumSet.of(EHoldingPatternEntry.TEARDROP),
                     HoldingPattern.calculateEntry(bos, lwm, 313, EHoldingPatternTurns.LEFT));
    }
}
