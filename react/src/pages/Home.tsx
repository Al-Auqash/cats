import { useEffect, useState } from "react";
import { SpeciesInterface } from "../interfaces/Species.interface";
import CatApi from "../api/Cat.api";
import { CatInterface } from "../interfaces/Cat.interface";
import SpeciesApi from "../api/Species.api";
import { gql, useQuery } from "@apollo/client";
import { deleteCat, getAllCats } from "../api/Cat.graphql";

const Home = () => {
  const [cat, setCat] = useState<CatInterface[]>([]);
  const [species, setSpecies] = useState<SpeciesInterface[]>([]);

  
  const { loading, error, data } = getAllCats();
  // const { loading, error, data } = useGetCatById(2);
  console.log(data)

  const { deleteCatMutation } = deleteCat();
  // const getCat = () => {
  //   CatApi.getAllCats()
  //     .then((response) => {
  //       setCat(response.data);
  //     })
  //     .catch((err) => {
  //       console.log(err)
  //     })
  // }

  // const getSpecies = () => {
  //   SpeciesApi.getAllSpecies()
  //     .then((response) => {
  //       setSpecies(response.data);
  //     })
  //     .catch((err) => {
  //       console.log(err)
  //     })
  // }

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  // useEffect(() => {
  // getCat();
  // getSpecies();
  // }, [])

  const handleDelete = (cat_id: number) => {

  }

  return (
    <>
      <div>
        <h1>Cats</h1>
        <a href='/create-cat'>Create Cat</a>
        <table>
          <thead>
            <tr>
              <td>no</td>
              <td>name</td>
              <td>age</td>
              <td>color</td>
              <td>origin_id</td>
              <td>breed_id</td>
              <td>action</td>
            </tr>
          </thead>
          <tbody>
            {
              data.cats && data.cats.map((data: any) => (
                <tr key={data.cat_id}>
                  <td>{data.cat_id}</td>
                  <td>{data.name}</td>
                  <td>{data.age}</td>
                  <td>{data.color}</td>
                  <td>{data.origin_id}</td>
                  <td>{data.breed_id}</td>
                  <td>
                    <a href={'/edit-cat/' + data.cat_id}>Edit</a>
                    <a onClick={() => deleteCatMutation({ variables: {cat_id : data.cat_id}})}>Hapus</a>
                  </td>
                </tr>
              ))
            }
          </tbody>
        </table>
      </div>
    </>
  );
};

export default Home;
