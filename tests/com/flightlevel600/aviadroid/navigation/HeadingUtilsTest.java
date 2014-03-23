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
import static org.junit.Assert.assertTrue;


/** Unit tests for the HeadingUtils class */
public class HeadingUtilsTest {
    @Test
    public void normalizeInt() {
        assertEquals("normalized 0",      0, HeadingUtils.normalize(0));
        assertEquals("normalized -1",   359, HeadingUtils.normalize(-1));
        assertEquals("normalized -359",   1, HeadingUtils.normalize(-359));
        assertEquals("normalized -360",   0, HeadingUtils.normalize(-360));
        assertEquals("normalized -361", 359, HeadingUtils.normalize(-361));
        assertEquals("normalized -719",   1, HeadingUtils.normalize(-719));
        assertEquals("normalized -720",   0, HeadingUtils.normalize(-720));
        assertEquals("normalized -721", 359, HeadingUtils.normalize(-721));
        assertEquals("normalized 1",      1, HeadingUtils.normalize(1));
        assertEquals("normalized 359",  359, HeadingUtils.normalize(359));
        assertEquals("normalized 360",    0, HeadingUtils.normalize(360));
        assertEquals("normalized 361",    1, HeadingUtils.normalize(361));
        assertEquals("normalized 719",  359, HeadingUtils.normalize(719));
        assertEquals("normalized 720",    0, HeadingUtils.normalize(720));
        assertEquals("normalized 721",    1, HeadingUtils.normalize(721));
        assertEquals("normalized Integer.MAX_VALUE", 127, HeadingUtils.normalize(Integer.MAX_VALUE));
        assertEquals("normalized Integer.MAX_VALUE - 1", 126, HeadingUtils.normalize(Integer.MAX_VALUE - 1));
        assertEquals("normalized Integer.MIN_VALUE", 232, HeadingUtils.normalize(Integer.MIN_VALUE));
        assertEquals("normalized Integer.MIN_VALUE + 1", 233, HeadingUtils.normalize(Integer.MIN_VALUE + 1));
    }

    @Test
    public void normalizeDouble() {
        assertEquals("normalized 0.0",      0, HeadingUtils.normalize(0.0));
        assertEquals("normalized 0.1",      0, HeadingUtils.normalize(0.1));
        assertEquals("normalized -0.9",   359, HeadingUtils.normalize(-0.9));
        assertEquals("normalized -1.0",   359, HeadingUtils.normalize(-1.0));
        assertEquals("normalized -1.1",   359, HeadingUtils.normalize(-1.1));
        assertEquals("normalized -359.2",   1, HeadingUtils.normalize(-359.2));
        assertEquals("normalized -360.3",   0, HeadingUtils.normalize(-360.3));
        assertEquals("normalized -361.2", 359, HeadingUtils.normalize(-361.2));
        assertEquals("normalized -719.3",   1, HeadingUtils.normalize(-719.3));
        assertEquals("normalized -720.4",   0, HeadingUtils.normalize(-720.4));
        assertEquals("normalized -721.2", 359, HeadingUtils.normalize(-721.2));
        assertEquals("normalized 1.354",    1, HeadingUtils.normalize(1.354));
        assertEquals("normalized 359.0",  359, HeadingUtils.normalize(359.0));
        assertEquals("normalized 360.2",    0, HeadingUtils.normalize(360.2));
        assertEquals("normalized 361.1",    1, HeadingUtils.normalize(361.1));
        assertEquals("normalized 719.4",  359, HeadingUtils.normalize(719.4));
        assertEquals("normalized 719.5",    0, HeadingUtils.normalize(719.5));
        assertEquals("normalized 721.3",    1, HeadingUtils.normalize(721.3));
        assertEquals("normalized Double.MIN_VALUE", 0, HeadingUtils.normalize(Double.MIN_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void normalizeOutOfRangeMax() {
        HeadingUtils.normalize(Double.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void normalizeOutOfRangeMin() {
        HeadingUtils.normalize(2.0 * Integer.MIN_VALUE);
    }

    @Test
    public void random() {
        for (int i = 0; i < 25; i++) {
            int hdg = HeadingUtils.random();
            assertTrue("random heading is negative", hdg >= 0);
            assertTrue("random heading is over 360", hdg < 360);
        }
    }

    @Test
    public void reciprocal() {
        assertEquals("reciprocal to 0",     180, HeadingUtils.reciprocal(0));
        assertEquals("reciprocal to 90",    270, HeadingUtils.reciprocal(90));
        assertEquals("reciprocal to 180",     0, HeadingUtils.reciprocal(180));
        assertEquals("reciprocal to 181",     1, HeadingUtils.reciprocal(181));
        assertEquals("reciprocal to 270",    90, HeadingUtils.reciprocal(270));
        assertEquals("reciprocal to 360",   180, HeadingUtils.reciprocal(360));
        assertEquals("reciprocal to 361",   181, HeadingUtils.reciprocal(361));
        assertEquals("reciprocal to 721",   181, HeadingUtils.reciprocal(721));
        assertEquals("reciprocal to -1",    179, HeadingUtils.reciprocal(-1));
        assertEquals("reciprocal to -361",  179, HeadingUtils.reciprocal(-361));
        assertEquals("reciprocal to North", 180, HeadingUtils.reciprocal(HeadingUtils.CARDINAL_NORTH));
        assertEquals("reciprocal to East",  270, HeadingUtils.reciprocal(HeadingUtils.CARDINAL_EAST));
        assertEquals("reciprocal to West",   90, HeadingUtils.reciprocal(HeadingUtils.CARDINAL_WEST));
        assertEquals("reciprocal to South",   0, HeadingUtils.reciprocal(HeadingUtils.CARDINAL_SOUTH));
    }

    @Test
    public void format() {
        assertEquals("formatting 0",    "360", HeadingUtils.format(0));
        assertEquals("formatting 360",  "360", HeadingUtils.format(360));
        assertEquals("formatting 720",  "360", HeadingUtils.format(720));
        assertEquals("formatting -360", "360", HeadingUtils.format(-360));
        assertEquals("formatting -720", "360", HeadingUtils.format(-720));
        assertEquals("formatting 5",    "005", HeadingUtils.format(5));
        assertEquals("formatting 45",   "045", HeadingUtils.format(45));
        assertEquals("formatting 135",  "135", HeadingUtils.format(135));
        assertEquals("formatting 180",  "180", HeadingUtils.format(180));
        assertEquals("formatting -355", "005", HeadingUtils.format(-355));
        assertEquals("formatting -315", "045", HeadingUtils.format(-315));
        assertEquals("formatting -175", "185", HeadingUtils.format(-175));
        assertEquals("formatting -180", "180", HeadingUtils.format(-180));
    }

    @Test
    public void nearestCardinal() {
        assertEquals("0",    ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(0));
        assertEquals("360",  ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(360));
        assertEquals("-360", ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(-360));
        assertEquals("720",  ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(720));
        assertEquals("-720", ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(-720));
        assertEquals("22",   ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(22));
        assertEquals("-22",  ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(-22));
        assertEquals("338",  ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(338));
        assertEquals("-338", ECaridinalDirection.NORTH, HeadingUtils.nearestCaridinal(-338));

        assertEquals("45",   ECaridinalDirection.NORTHEAST, HeadingUtils.nearestCaridinal(45));
        assertEquals("-315", ECaridinalDirection.NORTHEAST, HeadingUtils.nearestCaridinal(-315));
        assertEquals("23",   ECaridinalDirection.NORTHEAST, HeadingUtils.nearestCaridinal(23));
        assertEquals("-337", ECaridinalDirection.NORTHEAST, HeadingUtils.nearestCaridinal(-337));
        assertEquals("67",   ECaridinalDirection.NORTHEAST, HeadingUtils.nearestCaridinal(67));
        assertEquals("-293", ECaridinalDirection.NORTHEAST, HeadingUtils.nearestCaridinal(-293));

        assertEquals("90",   ECaridinalDirection.EAST, HeadingUtils.nearestCaridinal(90));
        assertEquals("-270", ECaridinalDirection.EAST, HeadingUtils.nearestCaridinal(-270));
        assertEquals("68",   ECaridinalDirection.EAST, HeadingUtils.nearestCaridinal(68));
        assertEquals("-292", ECaridinalDirection.EAST, HeadingUtils.nearestCaridinal(-292));
        assertEquals("112",  ECaridinalDirection.EAST, HeadingUtils.nearestCaridinal(112));
        assertEquals("-248", ECaridinalDirection.EAST, HeadingUtils.nearestCaridinal(-248));

        assertEquals("135",  ECaridinalDirection.SOUTHEAST, HeadingUtils.nearestCaridinal(135));
        assertEquals("-225", ECaridinalDirection.SOUTHEAST, HeadingUtils.nearestCaridinal(-225));
        assertEquals("113",  ECaridinalDirection.SOUTHEAST, HeadingUtils.nearestCaridinal(113));
        assertEquals("-247", ECaridinalDirection.SOUTHEAST, HeadingUtils.nearestCaridinal(-247));
        assertEquals("157",  ECaridinalDirection.SOUTHEAST, HeadingUtils.nearestCaridinal(157));
        assertEquals("-203", ECaridinalDirection.SOUTHEAST, HeadingUtils.nearestCaridinal(-203));

        assertEquals("180",  ECaridinalDirection.SOUTH, HeadingUtils.nearestCaridinal(180));
        assertEquals("-180", ECaridinalDirection.SOUTH, HeadingUtils.nearestCaridinal(-180));
        assertEquals("158",  ECaridinalDirection.SOUTH, HeadingUtils.nearestCaridinal(158));
        assertEquals("-202", ECaridinalDirection.SOUTH, HeadingUtils.nearestCaridinal(-202));
        assertEquals("202",  ECaridinalDirection.SOUTH, HeadingUtils.nearestCaridinal(202));
        assertEquals("-158", ECaridinalDirection.SOUTH, HeadingUtils.nearestCaridinal(-158));

        assertEquals("225",  ECaridinalDirection.SOUTHWEST, HeadingUtils.nearestCaridinal(225));
        assertEquals("-135", ECaridinalDirection.SOUTHWEST, HeadingUtils.nearestCaridinal(-135));
        assertEquals("203",  ECaridinalDirection.SOUTHWEST, HeadingUtils.nearestCaridinal(203));
        assertEquals("-157", ECaridinalDirection.SOUTHWEST, HeadingUtils.nearestCaridinal(-157));
        assertEquals("247",  ECaridinalDirection.SOUTHWEST, HeadingUtils.nearestCaridinal(247));
        assertEquals("-113", ECaridinalDirection.SOUTHWEST, HeadingUtils.nearestCaridinal(-113));

        assertEquals("270",  ECaridinalDirection.WEST, HeadingUtils.nearestCaridinal(270));
        assertEquals("-90",  ECaridinalDirection.WEST, HeadingUtils.nearestCaridinal(-90));
        assertEquals("248",  ECaridinalDirection.WEST, HeadingUtils.nearestCaridinal(248));
        assertEquals("-112", ECaridinalDirection.WEST, HeadingUtils.nearestCaridinal(-112));
        assertEquals("292",  ECaridinalDirection.WEST, HeadingUtils.nearestCaridinal(292));
        assertEquals("-68",  ECaridinalDirection.WEST, HeadingUtils.nearestCaridinal(-68));

        assertEquals("315",  ECaridinalDirection.NORTHWEST, HeadingUtils.nearestCaridinal(315));
        assertEquals("-45",  ECaridinalDirection.NORTHWEST, HeadingUtils.nearestCaridinal(-45));
        assertEquals("293",  ECaridinalDirection.NORTHWEST, HeadingUtils.nearestCaridinal(293));
        assertEquals("-67",  ECaridinalDirection.NORTHWEST, HeadingUtils.nearestCaridinal(-67));
        assertEquals("337",  ECaridinalDirection.NORTHWEST, HeadingUtils.nearestCaridinal(337));
        assertEquals("-23",  ECaridinalDirection.NORTHWEST, HeadingUtils.nearestCaridinal(-23));
    }
}
