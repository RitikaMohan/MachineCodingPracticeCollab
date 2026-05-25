import { useCallback, useState } from 'react';

export default function useArray(defaultValue) {
  const [array, setArray] = useState(defaultValue);
  const push = useCallback((element) => setArray((a) => [...a, element]), []);
  const filter = useCallback(
    (callback) => setArray((a) => a.filter(callback)),
    [],
  );
  const update = useCallback(
    (index, newElement) =>
      setArray((a) => [
        ...a.slice(0, index),
        newElement,
        ...a.slice(index + 1, a.length),
      ]),
    [],
  );
  const remove = useCallback(
    (index) =>
      setArray((a) => [...a.slice(0, index), ...a.slice(index + 1, a.length)]),
    [],
  );
  const clear = useCallback(() => setArray([]), []);
  return { array, set: setArray, push, filter, update, remove, clear };
}
