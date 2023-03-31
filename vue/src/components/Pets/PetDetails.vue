<template>
  <div id="main-grid">
    <head></head>
    <body>
      <div class="container">
        <div id="name">{{ petFacts.name }}</div>
        <div id="edit">
          <router-link
            v-bind:to="{ name: 'edit', params: { id: petFacts.id } }"
            v-if="$store.state.token"
            >Edit Pet</router-link
          >
        </div>
        <img
          id="pic"
          v-bind:src="petFacts.image"
          alt="Are looks all that matter to you?"
        />
        <div class="petDetails">
          <h3 id="caption">"{{ petFacts.tagline }}"</h3>
          <div id="details">
            <p>Animal Member Id: &emsp;{{ petFacts.id }}</p>
            <p>Gender: {{ petFacts.gender }}</p>
            <p>Age: {{ petFacts.age }}</p>
            <div
              v-if="
                petFacts.gender.toLowerCase() === 'female' &&
                petFacts.type != 'bunny'
              "
            >
              <p>Fixed: {{ petFacts.fixed ? "Spayed" : " Not Spayed" }}</p>
            </div>
            <div v-if="petFacts.gender.toLowerCase() === 'male'">
              <p>Fixed: {{ petFacts.fixed ? "Neutered" : "Not Neutered" }}</p>
            </div>
          </div>
          <router-link v-bind:to="{ name: 'addVolunteer' }" id="volunteer"
            >Volunteer To Play With Me</router-link
          >
        </div>
      </div>
    </body>
  </div>
</template>
<script>
import petService from "@/services/PetService.js";
export default {
  name: "pet-details",
  data() {
    return {
      petFacts: {},
    };
  },
  //props: ["petFacts"],
  //this causes 'unexpected mutation of property petFacts error' and page won't load
  created() {
    //load up the object into the above property through
    //the find method pulling the param off the url
    if (this.$store.state.pets.length < 1) {
      petService.getListOfAllPets().then((response) => {
        let list = response.data;
        this.$store.commit("ADD_PETS", list);
      });
    }
    if (this.$route.params.id) {
      this.petFacts = this.$store.state.pets.find((pet) => {
        return pet.id === this.$route.params.id;
      });
    }
  },
};
</script>
<style scoped>
#main-grid {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "name"
    "edit"
    "pic"
    "details"
    "volunteer";
}
@media screen and (max-height: 800px) {
  #main-grid {
    grid-template-columns: 1fr 1fr 2fr 1fr 3fr 1fr;
    grid-template-areas: "name pic ";
  }
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  row-gap: 24px;
  column-gap: 50px;
  align-items: center;
  height: 90vh;
}
#name {
  width: 100%;
  padding-top: 8px;
  font-size: 3rem;
  font-family: "Lora", Arial, Helvetica, sans-serif;
  margin: auto;
  color: #b4e3b4;
  text-decoration-line: 1px;
  text-decoration-color: #d9f1d9;
  margin: 18px;
  background-color: #434343ff;
  display: block;
}
#pic {
  width: 320px;
  height: 520px;
  border-radius: 12px;
  border: 5px solid #434343;
  padding: 3px;
  margin: 5px;
}
#loader {
  width: auto;
  height: 80vh;
}
#caption {
  font-family: "Lora", Verdana, Geneva, Tahoma, sans-serif;
  font: italic;
  margin: -15px;
}
#details {
  font-size: 20px;
  font-family: "Lora", Verdana, Geneva, Tahoma, sans-serif;
  display: flex;
  justify-content: space-between;
  gap: 40px;
  margin-top: -3px;
}
#volunteer {
  font-family: "Lora", Verdana, Geneva, Tahoma, sans-serif;
  font-size: 18px;
  padding-bottom: -40px;
}
#edit {
  font-size: 20px;
  font-family: "Lora", Verdana, Geneva, Tahoma, sans-serif;
}

.petDetails {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  text-align: center;
  height: 600px;
  width: 45%;
  margin: 10px;
  padding: 15px;
  background-color: #b4e3b4;
  border: 3px solid #434343;
  border-radius: 10px;
}
</style>