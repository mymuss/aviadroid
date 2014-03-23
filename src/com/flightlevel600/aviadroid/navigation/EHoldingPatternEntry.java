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

import com.flightlevel600.aviadroid.R;


/** Represents Holding Pattern Entry Procedure */
public enum EHoldingPatternEntry {
    DIRECT   (R.string.e_direct),
    TEARDROP (R.string.e_teardrop),
    PARALLEL (R.string.e_parallel);

    private int resourceId;

    private EHoldingPatternEntry(final int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return this.resourceId;
    }
}
