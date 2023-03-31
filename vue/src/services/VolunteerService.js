import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {

    approveVolunteer(volunteer) {
        return http.post(`/admin`, volunteer, {
            headers: {
                "Content-Type": "application/json"
            }
        }
        );

    },

    denyVolunteer(volunteer) {
        return http.put('/admin', volunteer, {
            headers: {
                "Content-Type": "application/json"
            } 
        }
        );
    },

    findAllVolunteers() {
        return http.get(`/volunteers`);
    },

    findVolunteerById(id) {
        return http.get(`/volunteers/${id}`);
    },

    createVolunteer(volunteer) {
        const url = "/volunteer/apply";
        return http.post(url, volunteer);
    },

    updateVolunteer(volunteer) {
        const url = '/volunteers';
        return http.put(url, volunteer);
    },

    deleteVolunteer(id) {
        return http.delete(`/volunteers/${id}`);
    },

    saveVolunteer(volunteer) {
        if (volunteer.id) {
            return this.updateVolunteer(volunteer);
        } else {
            return this.createVolunteer(volunteer);
        }
    }
}