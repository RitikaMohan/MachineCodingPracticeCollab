import { useEffect, useState } from 'react';

export default function useQuery(fn) {
  const [state, setState] = useState({
    status: 'loading',
  });
  useEffect(() => {
    // Ignore late promise resolutions after deps change or the component unmounts.
    let ignore = false;
    setState({ status: 'loading' });
    fn()
      .then((data) => {
        if (ignore) {
          return;
        }
        setState({ status: 'success', data });
      })
      .catch((error) => {
        if (ignore) {
          return;
        }
        setState({ status: 'error', error });
      });
    return () => {
      ignore = true;
    };
  }, []);
  return state;
}
