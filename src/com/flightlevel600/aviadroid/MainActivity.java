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
package com.flightlevel600.aviadroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/** Startup activity for the application */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Default handler to display reciprocal headings practice mode.
     *
     * @param view sender view
     */
    public void showReciprocal(final View view) {
        Intent reciprocalIntent = new Intent(this, ReciprocalHeadingActivity.class);
        startActivity(reciprocalIntent);
    }

    /**
     * Default handler to display holding pattern entry practice mode.
     *
     * @param view sender view
     */
    public void showHoldingPatternEntry(final View view) {
        Intent holdingIntent = new Intent(this, HoldingPatternActivity.class);
        startActivity(holdingIntent);
    }

    /**
     * Default handler to display VOR navigation practice mode.
     *
     * @param view sender view
     */
    public void showVorNavigation(final View view) {
        Intent vorIntent = new Intent(this, VorActivity.class);
        startActivity(vorIntent);
    }
}
