import api from './http-common';
import { CatInterface } from '../interfaces/Cat.interface';

class CatServices {
  getAllCats() {
    return api.get('api/cats/');
  }

  getCatById(id: string | undefined) {
    return api.get(`api/cats/${id}`);
  }

  createCat(data: CatInterface[]) {
    return api.post(`api/Cat/`, data);
  }

  updateCat(id: string | undefined, data: CatInterface[]) {
    return api.patch(`api/Cat/${id}`, data);
  }

  deleteCat(id: string | undefined) {
    return api.delete(`api/Cat/${id}`);
  }
}

export default new CatServices();
