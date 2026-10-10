package uz.compux.simtopgich;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        registerPlugin(MagSensorPlugin.class);
        super.onCreate(savedInstanceState);
    }
}
