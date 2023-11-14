import React from 'react';
import ReactDOM from 'react-dom/client';
import { WrappedApp } from './App';
import './index.css';
import client from './apolloClient';
import { ApolloProvider } from '@apollo/client';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <ApolloProvider client={client}>
    <WrappedApp />
  </ApolloProvider>
);
