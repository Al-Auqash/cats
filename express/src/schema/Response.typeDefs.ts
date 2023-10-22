import {
  GraphQLBoolean,
  GraphQLInt,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLString,
} from 'graphql';

const ResponseType = new GraphQLObjectType({
  name: 'ResponseType',
  fields: {
    status: { type: GraphQLNonNull(GraphQLInt) },
    success: { type: GraphQLNonNull(GraphQLBoolean) },
    message: { type: GraphQLString }, // Optional, for any messages you might want to return.
  },
});

export default ResponseType;