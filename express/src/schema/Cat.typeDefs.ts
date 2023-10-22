import {
  GraphQLObjectType,
  GraphQLID,
  GraphQLString,
  GraphQLInt,
} from 'graphql';

// Cats Type
const CatType = new GraphQLObjectType({
  name: 'Cats',
  fields: () => ({
    cat_id: { type: GraphQLID },
    name: { type: GraphQLString },
    age: { type: GraphQLInt },
    color: { type: GraphQLString },
    origin_id: { type: GraphQLString },
    breed_id: { type: GraphQLString },
  }),
});

export default CatType;


