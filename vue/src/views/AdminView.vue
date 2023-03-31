<template>
<div>
 
    <approve-volunteers/>
  </div>
</template>

<script>
import volunteerService from '@/services/VolunteerService.js';
import petService from '@/services/PetService.js';
import ApproveVolunteers from '../components/ApproveVolunteers.vue';

export default {
  components: { ApproveVolunteers },
  data() {
    return {
    }
  },
  created() {
    volunteerService.findAllVolunteers().then((response) => {
      let list = response.data;
      this.$store.commit("ADD_VOLUNTEERS", list);
    });
    petService.getListOfAllPets().then((response) => {
      let list = response.data;
      this.$store.commit("ADD_PETS", list);
    });
  },

}


  

</script>

<style>

</style>