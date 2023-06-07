import { useEffect, useState } from "react";
import CatApi from "../api/Cat.api";
import { CatInterface } from "../interfaces/Cat.interface";

const Home = () => {

  const [cat, setCat] = useState<CatInterface[]>([]);

  const getCat = () => {
    CatApi.getAllCats()
      .then((response) => {
        setCat(response.data);
      })
      .catch((err) => {
        console.log(err)
      })
  }

  useEffect(() => {
    getCat();
  }, [])

  return (
    <div>
      <h1>Cats</h1>
      <table>
        <thead>
          <tr>
            <td>no</td>
            <td>name</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>kitty</td>
          </tr>
          {
            cat && cat.map((data) => (
              <tr>
                <td>{data.id}</td>
                <td>{data.name}</td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
}

export default Home;
