import { GraphQLObjectType, GraphQLSchema } from 'graphql';
import { CatQueries, CatMutations } from './Cat.resolver';

// Queries
const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    ...CatQueries,
  },
});

// Mutations
const RootMutation = new GraphQLObjectType({
  name: 'RootMutationType',
  fields: {
    ...CatMutations,
  },
});

// Merge all queries and mutations
export const mergedSchema = new GraphQLSchema({
  query: RootQuery,
  mutation: RootMutation,
});
