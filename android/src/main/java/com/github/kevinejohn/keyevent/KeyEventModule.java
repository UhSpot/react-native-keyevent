package com.github.uhspot.touchevent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;

import android.view.TouchEvent;

/**
 * Created by Kevin Johnson on 8/15/16.
 */
public class TouchEventModule extends ReactContextBaseJavaModule {
    private ReactContext mReactContext;
    private DeviceEventManagerModule.RCTDeviceEventEmitter mJSModule = null;

    private static TouchEventModule instance = null;

    public static TouchEventModule initTouchEventModule(ReactApplicationContext reactContext) {
        instance = new TouchEventModule(reactContext);
        return instance;
    }

    public static TouchEventModule getInstance() {
        return instance;
    }

    public void onTouchDownEvent(int touchCode, TouchEvent touchEvent) {
        if (!mReactContext.hasActiveCatalystInstance()) {
            return;
        }

        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        mJSModule.emit("onTouchDown", getJsEventParams(touchCode, touchEvent, null));
    };

    public void onTouchUpEvent(int touchCode, TouchEvent touchEvent) {
        if (!mReactContext.hasActiveCatalystInstance()) {
            return;
        }

        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        mJSModule.emit("onTouchUp", getJsEventParams(touchCode, touchEvent, null));
    };

    public void onTouchMultipleEvent(int touchCode, int repeatCount, TouchEvent touchEvent) {
        if (!mReactContext.hasActiveCatalystInstance()) {
            return;
        }
        
        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }


        mJSModule.emit("onTouchMultiple", getJsEventParams(touchCode, touchEvent, repeatCount));
    };

    protected TouchEventModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    private WritableMap getJsEventParams(int touchCode, TouchEvent touchEvent, Integer repeatCount) {
        WritableMap params = new WritableNativeMap();
        int action = touchEvent.getAction();
        char pressedTouch = (char) touchEvent.getUnicodeChar();

        if (touchEvent.getAction() == TouchEvent.ACTION_MULTIPLE && touchCode == TouchEvent.KEYCODE_UNKNOWN) {
            String chars = touchEvent.getCharacters();
            if (chars != null) {
                params.putString("characters", chars);
            }
        }

        if (repeatCount != null) {
            params.putInt("repeatcount", repeatCount);
        }

        params.putInt("touchCode", touchCode);
        params.putInt("action", action);
        params.putString("pressedTouch", String.valueOf(pressedTouch));

        return params;
    }

    @Override
    public String getName() {
        return "TouchEventModule";
    }
}
