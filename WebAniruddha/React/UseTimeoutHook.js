import { useRef, useEffect } from 'react';

export default function useTimeout(callback, delay) {
  // Keep the timeout pointed at the latest callback without restarting it on every render.
  const latestCallback = useRef(callback);
  latestCallback.current = callback;
  useEffect(() => {
    if (delay === null) {
      return;
    }
    const timeoutId = setTimeout(() => {
      latestCallback.current();
    }, delay);
    return () => {
      clearTimeout(timeoutId);
    };
  }, [delay]);
}
