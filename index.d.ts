declare module 'react-native-touchevent';

export type TouchEventProps = { action: number; touchCode: number; touched: boolean; };

export function onTouchDownListener(keyEvent: any): void;
export function onTouchUpListener(keyEvent: any): void;
export function onTouchMultipleListener(keyEvent: any): void;

export function removeTouchDownListener(): void;
export function removeTouchUpListener(): void;
export function removeTouchMultipleListener(): void;
