import express from 'express';
import morgan from 'morgan';
import helmet from 'helmet';
import cors from 'cors';
import { graphqlHTTP } from 'express-graphql';

import * as dotenv from 'dotenv';
dotenv.config();

const app = express();

app.use(morgan('dev'));
app.use(
  helmet({
    contentSecurityPolicy: {
      directives: {
        ...helmet.contentSecurityPolicy.getDefaultDirectives(),
        "script-src": ["'self'", "'unsafe-inline'", "'unsafe-eval'"],
      },
    },
  })
);
app.use(cors());
app.use(express.json());

// database
import db from './db';
import { mergedSchema } from './resolvers/index.resolver';

const database = async () => {
  await db
    .sync()
    .then(() => {
      console.log('Synced db.');
    })
    .catch((err: { message: string }) => {
      console.log('Failed to sync db: ' + err.message);
    });
};

database();

// GraphQL Endpoint
app.use('/graphql', graphqlHTTP({
  schema: mergedSchema,
  graphiql: true,
}));

export default app;
