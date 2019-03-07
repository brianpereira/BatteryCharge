package org.dyndns.brianpereira.batterycharge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.widget.TextView;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TextView batteryStatusLabel = ((MainActivity)context).findViewById(R.id.BatteryStatus);

        String action = intent.getAction();

        if(action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            String message = "";

            switch (status) {
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "Full";
                    ToneGenerator beep = new ToneGenerator(AudioManager.STREAM_MUSIC, 500);
                    beep.startTone(ToneGenerator.TONE_CDMA_HIGH_L, 1000);
                    break;

                case BatteryManager.BATTERY_STATUS_CHARGING:
                 message = "Charging";
                 break;

                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "Discharging";
                    break;

                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "Not charging";
                    break;
            }

            batteryStatusLabel.setText(message);
        }
    }
}
