import {
  GraphQLID,
  GraphQLInt,
  GraphQLList,
  GraphQLNonNull,
  GraphQLString,
} from 'graphql';
import CatType from '../schema/Cat.typeDefs';
import CatModel from '../models/Cat.model';
import ResponseType from '../schema/Response.typeDefs';

// Queries
export const CatQueries = {
  cats: {
    type: new GraphQLList(CatType),
    resolve() {
      return CatModel.findAll();
    },
  },
  cat: {
    type: CatType,
    args: { cat_id: { type: GraphQLID } },
    resolve(parent: any, args: any) {
      return CatModel.findByPk(args.cat_id);
    },
  },
};

// Mutations
export const CatMutations = {
  // Add a cat
  createCat: {
    type: CatType,
    args: {
      name: { type: GraphQLNonNull(GraphQLString) },
      age: { type: GraphQLNonNull(GraphQLInt) },
      color: { type: GraphQLNonNull(GraphQLString) },
      origin_id: { type: GraphQLNonNull(GraphQLInt) },
      breed_id: { type: GraphQLNonNull(GraphQLInt) },
    },
    resolve(parent: any, args: any) {
      return CatModel.create(args);
    },
  },

  // Delete a cat
  deleteCat: {
    type: ResponseType,
    args: {
      cat_id: { type: GraphQLNonNull(GraphQLID) },
    },
    resolve(parent: any, args: any): Promise<any> {
      return CatModel.destroy({ where: { cat_id: args.cat_id } })
        .then(() => {
          return {
            status: 201,
            success: true,
          };
        })
        .catch((error: Error) => {
          return {
            status: 404,
            success: false,
            message: error.message,
          };
        });
    },
  },

  // Update a cat
  updateCat: {
    type: ResponseType,
    args: {
      cat_id: { type: GraphQLNonNull(GraphQLID) },
      name: { type: GraphQLString },
      age: { type: GraphQLInt },
      color: { type: GraphQLString },
      origin_id: { type: GraphQLInt },
      breed_id: { type: GraphQLInt },
    },
    resolve(parent: any, args: any): Promise<any> {
      return CatModel.update(
        {
          name: args.name,
          age: args.age,
          color: args.color,
          origin_id: args.origin_id,
          breed_id: args.breed_id,
        },
        {
          where: {
            cat_id: args.cat_id,
          },
        }
      )
        .then(() => {
          return {
            status: 201,
            success: true,
          };
        })
        .catch((error: Error) => {
          return {
            status: 404,
            success: false,
            message: error.message,
          };
        });
    },
  },
};
