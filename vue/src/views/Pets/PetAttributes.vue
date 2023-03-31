<template>
  <div>
    
    <div>
      <!--bind the imported component props to return property from the view-->
      <pet-details v-bind:petFacts="petCard" id="featured"/>
    </div>
  </div>
</template>

<script>
import PetDetails from "../../components/Pets/PetDetails.vue";
import petService from '@/services/PetService.js';

export default {
  components: {
     PetDetails 
     },
  data() {
    return {
      //declare the property to be passed to the imported component through vbind above
      petCard: {},
      isLoading: true,
    }
  },
  created() {
    if(this.$store.state.pets.length <= 1) {
    petService.getListOfAllPets().then((response) => {
      let list = response.data;
      this.$store.commit("ADD_PETS", list);
    });
  }
  },
 

}
</script>
