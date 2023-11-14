import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import NotFound from './pages/NotFound';
import CreateCat from './pages/CreateCat';
import EditCat from './pages/EditCat';
import Dashboard from './pages/Dashboard';

export function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/create-cat" element={<CreateCat />} />
      <Route path="/edit-cat/:cat_id" element={<EditCat />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export function WrappedApp() {
  return (
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}
