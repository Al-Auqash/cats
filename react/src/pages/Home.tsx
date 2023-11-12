import { useEffect, useState } from 'react';
import { SpeciesInterface } from '../interfaces/Species.interface';
import CatApi from '../api/Cat.api';
import { CatInterface } from '../interfaces/Cat.interface';

const Home = () => {
  const [cat, setCat] = useState<CatInterface[]>([]);

  const getCat = () => {
    CatApi.getAllCats()
      .then((response) => {
        setCat(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getCat();
  }, []);

  return (
    <div>
      <h1>Cats</h1>
      <table>
        <thead>
          <tr>
            <td>no</td>
            <td>name</td>
            <td>age</td>
            <td>color</td>
            <td>origin</td>
            <td>breed</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>kitty</td>
            <td>2</td>
            <td>white</td>
            <td>US</td>
            <td>Maine</td>
          </tr>
          {cat &&
            cat.map((data) => (
              <tr>
                <td>{data.cat_id}</td>
                <td>{data.name}</td>
                <td>{data.age}</td>
                <td>{data.color}</td>
                <td>{data.origin_name}</td>
                <td>{data.breed_name}</td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};

export default Home;
