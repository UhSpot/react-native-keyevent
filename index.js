import { DeviceEventEmitter, NativeEventEmitter, NativeModules, Platform } from 'react-native';

class TouchEvent {
  onTouchDownListener(cb) {
    this.removeTouchDownListener();
    if (Platform.OS === "ios") {
      let touchEvent = new NativeEventEmitter(NativeModules.RNTouchEvent);
      this.listenerTouchDown = touchEvent.addListener('onTouchDown', cb);
    } else {
      this.listenerTouchDown = DeviceEventEmitter.addListener('onTouchDown', cb);
    }
  }

  removeTouchDownListener() {
    if (this.listenerTouchDown) {
      this.listenerTouchDown.remove();
      this.listenerTouchDown = null;
    }
  }

  onTouchUpListener(cb) {
    this.removeTouchUpListener();
    if (Platform.OS === "ios") {
      let touchEvent = new NativeEventEmitter(NativeModules.RNTouchEvent)
      this.listenerTouchUp = touchEvent.addListener('onTouchUp', cb);
    } else {
      this.listenerTouchUp = DeviceEventEmitter.addListener('onTouchUp', cb);
    }
  }

  removeTouchUpListener() {
    if (this.listenerTouchUp) {
      this.listenerTouchUp.remove();
      this.listenerTouchUp = null;
    }
  }

  onTouchMultipleListener(cb) {
    this.removeTouchMultipleListener();
    if (Platform.OS === "ios") {
      let touchEvent = new NativeEventEmitter(NativeModules.RNTouchEvent);
      this.listenerTouchMultiple = touchEvent.addListener('onTouchMultiple', cb);
    } else {
      this.listenerTouchMultiple = DeviceEventEmitter.addListener('onTouchMultiple', cb);
    }
  }

  removeTouchMultipleListener() {
    if (this.listenerTouchMultiple) {
      this.listenerTouchMultiple.remove();
      this.listenerTouchMultiple = null;
    }
  }
}

export default new TouchEvent();
