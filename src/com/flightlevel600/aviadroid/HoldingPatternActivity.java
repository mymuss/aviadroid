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
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.flightlevel600.aviadroid.navigation.EHoldingPatternEntry;
import com.flightlevel600.aviadroid.navigation.EHoldingPatternTurns;
import com.flightlevel600.aviadroid.navigation.HeadingUtils;
import com.flightlevel600.aviadroid.navigation.HoldingClearanceGenerator;
import com.flightlevel600.aviadroid.navigation.HoldingPattern;

import java.util.EnumSet;
import java.util.concurrent.Callable;


/** This activity handles holding pattern entry calculation practice */
public class HoldingPatternActivity extends Activity {
    // currently only one chart with fixed aircraft position on it
    private final static int CURRENT_HEADING             = 327;

    private HoldingClearanceGenerator clearanceGenerator = null;

    // Preload some string resource values for efficiency
    private String titleCorrect                          = null;
    private String titleIncorrect                        = null;
    private String messageCorrect                        = null;
    private String messageIncorrectSingle                = null;
    private String messageIncorrectMultiple              = null;

    // contains valid entries for current clearance
    private EnumSet<EHoldingPatternEntry> currentEntries = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.holding);

        clearanceGenerator = new HoldingClearanceGenerator(this);

        titleCorrect = getResources().getString(R.string.alert_title_correct);
        titleIncorrect = getResources().getString(R.string.alert_title_incorrect);
        messageCorrect = getResources().getString(R.string.holding_alert_message_correct);
        messageIncorrectSingle = getResources().getString(R.string.holding_alert_message_incorrect_single);
        messageIncorrectMultiple = getResources().getString(R.string.holding_alert_message_incorrect_multiple);

        reset();
    }

    /**
     * Default handler for Direct Entry answer.
     *
     * @param view sender view
     */
    public void answerDirect(final View view) {
        showAlert(EHoldingPatternEntry.DIRECT);
    }

    /**
     * Default handler for Teardrop Entry answer.
     *
     * @param view sender view
     */
    public void answerTeardrop(final View view) {
        showAlert(EHoldingPatternEntry.TEARDROP);
    }

    /**
     * Default handler for Parallel Entry answer.
     *
     * @param view sender view
     */
    public void answerParallel(final View view) {
        showAlert(EHoldingPatternEntry.PARALLEL);
    }

    /** Displays alert with answer */
    private void showAlert(final EHoldingPatternEntry entry) {
        String title;
        String message;
        if (currentEntries.contains(entry)) {
            title = titleCorrect;
            message = String.format(messageCorrect, getEntryName(entry));
        } else {
            title = titleIncorrect;
            EHoldingPatternEntry[] entriesArr = currentEntries.toArray(new EHoldingPatternEntry[0]);
            if (1 == currentEntries.size()) {
                message = String.format(messageIncorrectSingle, getEntryName(entriesArr[0]));
            } else {
                message = String.format(messageIncorrectMultiple, getEntryName(entriesArr[0]), getEntryName(entriesArr[1]));
            }
        }

        AlertDialogHelper.showDialog(title, message, this, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                reset();
                return null;
            }
        });
    }

    /** Reset the activity with new clearance question */
    private void reset() {
        int holdingRadial = HeadingUtils.random();
        EHoldingPatternTurns turns = EHoldingPatternTurns.RIGHT;
        if (0.5 < Math.random()) {
            turns = EHoldingPatternTurns.LEFT;
        }

        currentEntries = HoldingPattern.calculateEntry(holdingRadial, CURRENT_HEADING, turns);

        TextView clearanceView = (TextView)findViewById(R.id.txt_clearance);
        clearanceView.setText(clearanceGenerator.generate(holdingRadial, turns));
    }

    /**
     * Helper method for getting holding pattern entry name from resources
     *
     * @param entry holding pattern entry
     * @return string resource name for given entry
     */
    private String getEntryName(final EHoldingPatternEntry entry) {
        return getResources().getString(entry.getResourceId());
    }

}
