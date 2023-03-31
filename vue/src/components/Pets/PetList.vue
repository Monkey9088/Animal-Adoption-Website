<template>
  <div>
    <head></head>
    <body>
      <div id="container">
        <div
          class="petCard"
          v-for="pet in this.$store.state.pets"
          v-bind:key="pet.id"
          v-on:click="goToPet(pet.id)"
        >
          <div v-if="pet.available">
            <img
              id="frame"
              v-bind:src="pet.image"
              alt="Adopt this Pet Today!"
            />
            <h2 class="petName">{{ pet.name }}</h2>
            <p class="tagline">{{ pet.tagline }}</p>
          </div>
        </div>
      </div>
    </body>
  </div>
</template>
<script>
export default {
  name: "pet-list",
  components: {},
  data() {
    return {};
  },
  methods: {
    goToPet(petId) {
      this.$router.push({ name: "attributes", params: { id: petId } });
    },
  },
};
</script>
<style scoped>
.petCard {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  border: 3px solid #434343;
  border-radius: 10px;
  width: 400px;
  height: 360px;
  margin: 20px;
  background-color: #B4E3B4;
}
.petName {
  font-size: 1.5rem;
}

#frame {
  height: 60%;
  width: 40%;
  border:5px solid #434343; padding:3px; margin:5px;
  border-radius: 10px;
}
.petImg {
  max-width: 400px;
  height: 200px;
  object-fit: contain;
  display: block;
  margin-left: auto;
  margin-right: auto;
  border-radius: 50%;
}
.petDetails {
  padding-top: 78px;
}
#container {
  display: flex;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  margin: 0 auto;
  width: 80vw;
}
</style>