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
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.flightlevel600.aviadroid.navigation.HeadingUtils;

import java.util.concurrent.Callable;


/** This activity handles reciprocal headings practice mode */
public class ReciprocalHeadingActivity extends Activity {
    // Number of digits in the heading values should always be 3
    // in this activity. Values less than 100 should be zero-padded.
    private static final int NUM_DIGITS      = 3;

    // probably not worth bringing Apache Commons just for this
    private static final String EMPTY_STRING = "";

    // Preload some string resource values for efficiency
    private String titleCorrect              = null;
    private String titleIncorrect            = null;
    private String messageCorrect            = null;
    private String messageIncorrect          = null;

    // The following variables represent the current state of the activity
    private String currentHeading            = null;
    private String reciprocalHeading         = null;

    /** Handles any changes to the reciprocal heading text field */
    private final TextWatcher reciprocalHeadingWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i2, final int i3) {}

        @Override
        public void onTextChanged(final CharSequence charSequence, final int i, final int i2, final int i3) {}

        @Override
        public void afterTextChanged(final Editable editable) {
            if (NUM_DIGITS == editable.length()) {
                showResult(editable);
                reset();
            }
        }
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.reciprocal);

        titleCorrect = getResources().getString(R.string.alert_title_correct);
        titleIncorrect = getResources().getString(R.string.alert_title_incorrect);
        messageCorrect = getResources().getString(R.string.reciprocal_alert_message_correct);
        messageIncorrect = getResources().getString(R.string.reciprocal_alert_message_incorrect);

        findReciprocalHeadingView().addTextChangedListener(reciprocalHeadingWatcher);

        reset();
    }

    /**
     * Check answer and show result as a dialog
     *
     * @param editable text field with the reciprocal heading
     */
    private void showResult(final Editable editable) {
        String title;
        String message;
        if (reciprocalHeading.equals(editable.toString())) {
            title = titleCorrect;
            message = String.format(messageCorrect, currentHeading, reciprocalHeading);
        } else {
            title = titleIncorrect;
            message = String.format(messageIncorrect, currentHeading, reciprocalHeading);
        }

        AlertDialogHelper.showDialog(title, message, this, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                reset();
                return null;
            }
        });
    }

    /** Reset random heading in the text view and clear the answer view */
    private void reset() {
        int currentHeadingInt = HeadingUtils.random();
        currentHeading    = HeadingUtils.format(currentHeadingInt);
        reciprocalHeading = HeadingUtils.format(HeadingUtils.reciprocal(currentHeadingInt));

        findCurrentHeadingView().setText(currentHeading);
        findReciprocalHeadingView().setText(EMPTY_STRING);
    }

    // The following methods are UI elements accessors
    private TextView findCurrentHeadingView() {
        return (TextView)findViewById(R.id.txt_current_hdg);
    }

    private EditText findReciprocalHeadingView() {
        return (EditText)findViewById(R.id.edit_reciprocal);
    }
}
