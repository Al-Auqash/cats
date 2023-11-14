import React from 'react';
import ReactDOM from 'react-dom/client';
import { WrappedApp } from './App';
import './index.css';
import client from './apolloClient';
import { ApolloProvider } from '@apollo/client';

import { ThemeProvider } from "@material-tailwind/react";

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <ApolloProvider client={client}>
      <ThemeProvider>
        <WrappedApp />
      </ThemeProvider>
    </ApolloProvider>
  </React.StrictMode>
);
