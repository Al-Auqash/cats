import { gql, useMutation, useQuery } from '@apollo/client';

export const getAllCats = () => {
  const GET_ALL_CATS = gql`
    query {
      cats {
        cat_id
        name
        age
        color
        breed_id
        origin_id
      }
    }
  `;

  const { loading, error, data } = useQuery(GET_ALL_CATS);

  return { loading, error, data };
};

export const getCatById = (cat_id: number) => {
  const GET_CAT_BY_ID = gql`
    query CatById($cat_id: ID!) {
      cat(cat_id: $cat_id) {
        cat_id
        name
        age
        color
        breed_id
        origin_id
      }
    }
  `;

  const { loading, error, data } = useQuery(GET_CAT_BY_ID, {
    variables: { cat_id },
  });

  return { loading, error, data };
};

export const createCat = () => {
  const CREATE_CAT = gql`
    mutation CreateCat(
      $name: String!
      $age: Int!
      $color: String!
      $breed_id: Int!
      $origin_id: Int!
    ) {
      createCat(
        name: $name
        age: $age
        color: $color
        breed_id: $breed_id
        origin_id: $origin_id
      ) {
        name
        age
        color
        breed_id
        origin_id
      }
    }
  `;

  const [createCatMutation, { loading, error, data }] = useMutation(CREATE_CAT);

  return { createCatMutation, loading, error, data };
};

export const editCat = () => {
  const UPDATE_CAT = gql`
    mutation updateCat(
      $cat_id: ID!
      $name: String
      $age: Int
      $color: String
      $breed_id: Int
      $origin_id: Int
    ) {
      updateCat(
        cat_id: $cat_id
        name: $name
        age: $age
        color: $color
        breed_id: $breed_id
        origin_id: $origin_id
      ) {
        status
        success
      }
    }
  `;

  const [updateCatMutation, { loading, error, data }] = useMutation(UPDATE_CAT);

  return { updateCatMutation, loading, error, data };
};

export const deleteCat = () => {
  const DELETE_CAT = gql`
    mutation deleteCat($cat_id: ID!) {
      deleteCat(cat_id: $cat_id) {
        status
        success
      }
    }
  `;

  const [deleteCatMutation, { loading, error, data }] = useMutation(DELETE_CAT);

  return { deleteCatMutation, loading, error, data };
};
