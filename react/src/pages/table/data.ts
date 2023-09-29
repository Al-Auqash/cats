import { useState, useEffect } from 'react';
import axios from 'axios';
import { Cat } from '../../interfaces/Cat.interface';

const defaultData: Cat[] = [
  {
    breed: 'test',
    country: 'test',
    origin: 'test',
    coat: 'test',
    pattern: 'test',
  },
];

const useCat = () => {
  const [cat, setCat] = useState<Cat[]>(() => [...defaultData]);

  const getCat = async () => {
    await axios
      .get('https://catfact.ninja/breeds')
      .then((response) => {
        setCat(response.data.data);
      })
      .catch((err) => {
        console.log('error : ', err);
      });
  };

  useEffect(() => {
    getCat();
  }, []);

  return cat;
};

export default useCat;
