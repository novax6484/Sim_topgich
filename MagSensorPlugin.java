package uz.compux.simtopgich;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "MagSensor")
public class MagSensorPlugin extends Plugin implements SensorEventListener {
    private SensorManager sm;

    @PluginMethod
    public void start(PluginCall call) {
        sm = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor mag = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mag == null) {
            call.reject("Telefonda magnit datchik yo'q");
            return;
        }
        sm.unregisterListener(this);
        sm.registerListener(this, mag, SensorManager.SENSOR_DELAY_FASTEST);
        call.resolve();
    }

    @PluginMethod
    public void stop(PluginCall call) {
        if (sm != null) sm.unregisterListener(this);
        call.resolve();
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        JSObject o = new JSObject();
        o.put("x", e.values[0]);
        o.put("y", e.values[1]);
        o.put("z", e.values[2]);
        notifyListeners("reading", o);
    }

    @Override
    public void onAccuracyChanged(Sensor s, int a) {}
}
