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


/** Unit tests for the VorUtils class */
public class VorUtilsTest {
    @Test
    public void getIndication() {
        assertEquals("on cardinal radial (0), on course, obs 360",
                     new CdiIndication(0, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(360, 0));

        assertEquals("on cardinal radial (360), on course, obs 360",
                     new CdiIndication(0, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(360, 360));

        assertEquals("on cardinal radial (720), on course, obs 360",
                     new CdiIndication(0, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(360, 720));

        assertEquals("on cardinal radial (360), on course, obs 000",
                     new CdiIndication(0, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(0, 360));

        assertEquals("on cardinal radial (360), course, obs 720",
                     new CdiIndication(0, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(720, 360));

        assertEquals("on cardinal radial (360), on opposite course, obs 180",
                     new CdiIndication(180, 0.0, ECdiDirection.TO),
                     VorUtils.getIndication(180, 360));

        assertEquals("on cardinal radial (360), on opposite course, obs 540",
                     new CdiIndication(180, 0.0, ECdiDirection.TO),
                     VorUtils.getIndication(540, 360));

        assertEquals("on cardinal radial (360), right of course, obs 358",
                     new CdiIndication(358, -1.0, ECdiDirection.FROM),
                     VorUtils.getIndication(358, 360));

        assertEquals("on cardinal radial (360), left of course, obs 002",
                     new CdiIndication(2, 1.0, ECdiDirection.FROM),
                     VorUtils.getIndication(2, 360));

        assertEquals("on cardinal radial (360), abeam, right of course, obs 270",
                     new CdiIndication(270, -45, ECdiDirection.FLAG),
                     VorUtils.getIndication(270, 360));

        assertEquals("on cardinal radial (360), abeam, left of course, obs 090",
                     new CdiIndication(90, 45, ECdiDirection.FLAG),
                     VorUtils.getIndication(90, 360));

        assertEquals("on cardinal radial (360), right of opposite course, obs 182",
                     new CdiIndication(182, 1.0, ECdiDirection.TO),
                     VorUtils.getIndication(182, 360));

        assertEquals("on cardinal radial (360), left of opposite course, obs 178",
                     new CdiIndication(178, -1.0, ECdiDirection.TO),
                     VorUtils.getIndication(178, 360));

        // non cardinal, right of 360
        assertEquals("non cardinal radial (005), on course, obs 005",
                     new CdiIndication(5, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(5, 5));

        assertEquals("non cardinal radial (365), on course, obs 365",
                     new CdiIndication(5, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(365, 365));

        assertEquals("non cardinal radial (005), on opposite course, obs 185",
                     new CdiIndication(185, 0.0, ECdiDirection.TO),
                     VorUtils.getIndication(185, 5));

        assertEquals("non cardinal radial (005), right of course, obs 003",
                     new CdiIndication(3, -1.0, ECdiDirection.FROM),
                     VorUtils.getIndication(3, 5));

        assertEquals("non cardinal radial (005), right of course beyond 360, obs 355",
                     new CdiIndication(355, -5.0, ECdiDirection.FROM),
                     VorUtils.getIndication(355, 5));

        assertEquals("non cardinal radial (005), left of course, obs 007",
                     new CdiIndication(7, 1.0, ECdiDirection.FROM),
                     VorUtils.getIndication(7, 5));

        assertEquals("non cardinal radial (005), abeam, right of course, obs 275",
                     new CdiIndication(275, -45, ECdiDirection.FLAG),
                     VorUtils.getIndication(275, 5));

        assertEquals("non cardinal radial (005), abeam, left of course, obs 095",
                     new CdiIndication(95, 45, ECdiDirection.FLAG),
                     VorUtils.getIndication(95, 5));

        assertEquals("non cardinal radial (005), right of opposite course, obs 187",
                     new CdiIndication(187, 1.0, ECdiDirection.TO),
                     VorUtils.getIndication(187, 5));

        assertEquals("non cardinal radial (005), left of opposite course, obs 183",
                     new CdiIndication(183, -1.0, ECdiDirection.TO),
                     VorUtils.getIndication(183, 5));

        // non cardinal, left of 360
        assertEquals("non cardinal radial (355), on course, obs 005",
                     new CdiIndication(355, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(355, 355));

        assertEquals("non cardinal radial (715), on course, obs 715",
                     new CdiIndication(355, 0.0, ECdiDirection.FROM),
                     VorUtils.getIndication(715, 715));

        assertEquals("non cardinal radial (355), on opposite course, obs 175",
                     new CdiIndication(175, 0.0, ECdiDirection.TO),
                     VorUtils.getIndication(175, 355));

        assertEquals("non cardinal radial (355), right of course, obs 353",
                     new CdiIndication(353, -1.0, ECdiDirection.FROM),
                     VorUtils.getIndication(353, 355));

        assertEquals("non cardinal radial (355), left of course, obs 357",
                     new CdiIndication(357, 1.0, ECdiDirection.FROM),
                     VorUtils.getIndication(357, 355));

        assertEquals("non cardinal radial (355), left of course beyond 360, obs 005",
                     new CdiIndication(5, 5.0, ECdiDirection.FROM),
                     VorUtils.getIndication(5, 355));

        assertEquals("on cardinal radial (355), abeam, right of course, obs 265",
                     new CdiIndication(265, -45, ECdiDirection.FLAG),
                     VorUtils.getIndication(265, 355));

        assertEquals("non cardinal radial (355), abeam, left of course, obs 085",
                     new CdiIndication(85, 45, ECdiDirection.FLAG),
                     VorUtils.getIndication(85, 355));

        assertEquals("non cardinal radial (355), right of opposite course, obs 177",
                     new CdiIndication(177, 1.0, ECdiDirection.TO),
                     VorUtils.getIndication(177, 355));

        assertEquals("non cardinal radial (355), left of opposite course, obs 173",
                     new CdiIndication(173, -1.0, ECdiDirection.TO),
                     VorUtils.getIndication(173, 355));
    }
}
