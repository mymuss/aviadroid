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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.concurrent.Callable;


/** Contains code to show alert dialogs with answer */
public class AlertDialogHelper {
    /**
     * Display dialog with single button
     *
     * @param title   dialog title
     * @param message dialog message
     * @param context sender context
     * @param reset   callable instance that resets the question
     */
    public static void showDialog(final String title,
                                  final String message,
                                  final Context context,
                                  final Callable<Object> reset) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        AlertDialog alert = alertDialogBuilder.setTitle(title)
                                              .setMessage(message)
                                              .setCancelable(false)
                                              .setPositiveButton(R.string.alert_button_ok,
                                                                 new DialogInterface.OnClickListener() {
                                                                     @Override
                                                                     public void onClick(DialogInterface dialogInterface,
                                                                                         int arg) {
                                                                         dialogInterface.cancel();
                                                                         try {
                                                                             reset.call();
                                                                         } catch (Exception ex) {
                                                                             // do nothing
                                                                         }
                                                                     }
                                                                 }).create();
        alert.show();
    }
}
