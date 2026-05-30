import { useState, useLayoutEffect } from 'react';

export default function useWindowSize() {
  const [windowSize, setWindowSize] = useState({
    height: 0,
    width: 0,
  });
  useLayoutEffect(() => {
    const resize = () =>
      setWindowSize({
        height: window.innerHeight,
        width: window.innerWidth,
      });
    // Measure once on mount so consumers do not wait for the first resize event.
    resize();
    window.addEventListener('resize', resize);
    return () => {
      window.removeEventListener('resize', resize);
    };
  }, []);
  return windowSize;
}
