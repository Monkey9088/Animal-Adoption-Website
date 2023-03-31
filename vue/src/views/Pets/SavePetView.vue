<template>
  <div v-if="isReady">
     <add-pet v-bind:existingPet="petToEdit"/>
  </div>
</template>

<script>
import AddPet from '../../components/Pets/AddPet.vue'
import PetService from '../../services/PetService.js'

export default {
  components: { 
    AddPet 
    },
  data() {
    return {
      petToEdit: {},
      isReady: false
    }
  },
  created() {
    if(this.$route.params.id) {
      PetService.getPetDetailsById(this.$route.params.id).then(
        response => {
          this.petToEdit = response.data;
          this.isReady = true;
        }
      );
    } else {
      this.isReady = true;
    }
  }
}
</script>
