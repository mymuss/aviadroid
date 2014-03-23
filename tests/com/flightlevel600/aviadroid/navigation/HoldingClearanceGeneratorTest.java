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

import android.content.Context;
import android.content.res.Resources;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/** Unit tests for the HoldingClearanceGenerator class */
public class HoldingClearanceGeneratorTest {
    private static final String STRING_VALUE = "FooBar";
    private static final String STRING_ARR_ITEM = "Bah";

    private HoldingClearanceGenerator generator;

    @Mock
    private Context context;

    @Mock
    private Resources resources;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        generator = new HoldingClearanceGenerator(context);
        when(context.getResources()).thenReturn(resources);
        when(resources.getStringArray(anyInt())).thenReturn(new String[] {STRING_ARR_ITEM});
        when(resources.getString(anyInt())).thenReturn(STRING_VALUE + " %s %s %s %s %s");
    }

    @Test
    public void generate() {
        String clearance;

        clearance = generator.generate(0, EHoldingPatternTurns.RIGHT);
        assertTrue("0 radial, formatted radial", clearance.contains(" 360 "));
        assertTrue("0 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("0 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(360, EHoldingPatternTurns.RIGHT);
        assertTrue("360 radial, formatted radial", clearance.contains(" 360 "));
        assertTrue("360 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("360 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(720, EHoldingPatternTurns.RIGHT);
        assertTrue("720 radial, formatted radial", clearance.contains(" 360 "));
        assertTrue("720 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("720 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(-360, EHoldingPatternTurns.RIGHT);
        assertTrue("-360 radial, formatted radial", clearance.contains(" 360 "));
        assertTrue("-360 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("-360 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(-720, EHoldingPatternTurns.RIGHT);
        assertTrue("-720 radial, formatted radial", clearance.contains(" 360 "));
        assertTrue("-720 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("-720 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(5, EHoldingPatternTurns.RIGHT);
        assertTrue("5 radial, formatted radial", clearance.contains(" 005 "));
        assertTrue("5 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("5 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(45, EHoldingPatternTurns.RIGHT);
        assertTrue("45 radial, formatted radial", clearance.contains(" 045 "));
        assertTrue("45 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("45 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(180, EHoldingPatternTurns.RIGHT);
        assertTrue("180 radial, formatted radial", clearance.contains(" 180 "));
        assertTrue("180 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("180 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(-355, EHoldingPatternTurns.RIGHT);
        assertTrue("-355 radial, formatted radial", clearance.contains(" 005 "));
        assertTrue("-355 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("-355 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(-315, EHoldingPatternTurns.RIGHT);
        assertTrue("-315 radial, formatted radial", clearance.contains(" 045 "));
        assertTrue("-315 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("-315 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        clearance = generator.generate(-180, EHoldingPatternTurns.RIGHT);
        assertTrue("-180 radial, formatted radial", clearance.contains(" 180 "));
        assertTrue("-180 radial, string value", clearance.contains(STRING_VALUE));
        assertTrue("-180 radial, callsign item", clearance.contains(STRING_ARR_ITEM));

        verify(context, atLeastOnce()).getResources();
        verify(resources, atLeastOnce()).getString(anyInt());
        verify(resources, atLeastOnce()).getStringArray(anyInt());
    }
}
