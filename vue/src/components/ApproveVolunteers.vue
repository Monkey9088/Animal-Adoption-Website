<template>
  <div>
    <h2>Pending Applications: </h2>
          <table id="pendingApplications">
          <thead>
              <tr id="column_names">
                  <th>Volunteer Id:</th>
                  <th>Name:</th>
                  <th>Username:</th>
                  <th>Email:</th>
                  <th>Phone:</th>
                  <th id="actions">Approve:</th>
                  <th>Deny:</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="volunteer in this.$store.state.volunteers" v-bind:key="volunteer.id">
                
                  <td v-if="volunteer.applicationStatusId == 1">{{volunteer.id}}</td>
                  <td v-if="volunteer.applicationStatusId == 1">{{volunteer.name}}</td>
                  <td v-if="volunteer.applicationStatusId == 1">{{volunteer.username}}</td>
                  <td v-if="volunteer.applicationStatusId == 1">{{volunteer.email}}</td>
                  <td v-if="volunteer.applicationStatusId == 1">{{volunteer.phone}}</td>
                  <!--This is not working right.-->
                  <td v-if="volunteer.applicationStatusId == 1"><button type="button" id="approve" v-on:click="approve(volunteer)"> Approve </button></td>
                  <td v-if="volunteer.applicationStatusId == 1"><button type="button" id="deny" v-on:click="deny(volunteer)">Deny</button></td>
                  
                  
                
              </tr>
          </tbody>
          </table>
  </div>
</template>

<script>
import volunteerService from '../services/VolunteerService.js';
export default {
    name: 'approve-volunteers',
    data() {
        return {
          errorMsg: "",
         
         

      volunteer: {
        id: "",
        name: "",
        email: "",
        phone: "",
        applicationStatusId: "",
        username:""
          
        }
    }
    },
    
  methods: {
    approve(volunteer){
      volunteerService.approveVolunteer(volunteer).then(
        (response) => {
          if (response.status === 201) {
            this.$store.commit("APPROVE_APPLICATION", volunteer);
          }
    }); 
    },
    deny(volunteer){
      volunteerService.denyVolunteer(volunteer).then(
        (response) => {
          if (response.status === 200) {
            this.$store.commit("DENY_APPLICATION", volunteer);
          }
        }
      );
    } 
  }
   

}
</script>

<style>

</style>