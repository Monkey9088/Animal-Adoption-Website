import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/"
});



export default {

    getListOfAllPets() {
        
        return http.get('pets');
    },

    getPetDetailsById(id) {
        return http.get(`pets/${id}`);
    },

    createPet(pet) {
        const url = "/pets/create";
        return http.post(url, pet);
    },

    updatePet(id, pet){
        return http.put(`/pets/edit/${id}`, pet)

    },
    
    addPet(pet){
        if(pet.id) {
            return this.updatePet(pet.id, pet);
        }else {
            return this.createPet(pet);
        }
       
    }

  
}