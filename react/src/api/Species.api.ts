import api from './http-common';
import { SpeciesInterface } from '../interfaces/Species.interface';

class SpeciesServices {
  getAllSpecies() {
    return api.get('api/species/');
  }

  getSpeciesById(id: string | undefined) {
    return api.get(`api/species/${id}`);
  }

  createSpecies(data: SpeciesInterface[]) {
    return api.post(`api/Species/`, data);
  }

  updateSpecies(id: string | undefined, data: SpeciesInterface[]) {
    return api.patch(`api/Species/${id}`, data);
  }

  deleteSpecies(id: string | undefined) {
    return api.delete(`api/Species/${id}`);
  }
}

export default new SpeciesServices();
