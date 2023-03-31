<template>
  <div>
    <head> </head>
    <body>
      <div>
        <form class="form">
          <div id="gender">
            <label>Gender</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-female"
              placeholder="Female / Male"
              type="text"
              v-model="pet.gender"
            />
          </div>
          <div id="name">
            <label>Name</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-picture-outline"
              placeholder="Fluffy / Socks / etc."
              type="text"
              v-model="pet.name"
            />
          </div>
          <div id="type">
            <label>Animal Type</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-football"
              placeholder="Cat / Dog / etc."
              type="text"
              v-model="pet.type"
            />
          </div>
          <div id="color">
            <label>Color</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-sunny"
              placeholder="Brown / Calico / etc."
              type="long"
              v-model="pet.color"
            />
          </div>
          <div id="age">
            <label>Age in years</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-time"
              placeholder="3 / 24 / etc."
              type="decimal"
              v-model="pet.age"
            />
          </div>
          <div id="image">
            <label>Image URL</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-camera"
              placeholder="/img/pickme.jpg"
              type="text"
              v-model="pet.image"
            />
          </div>
          <div id="tagline">
            <label>About Me</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-medal-1"
              placeholder="I like to cuddle / play / etc."
              type="text"
              v-model="pet.tagline"
            />
          </div>
          <div id="available">
            <label>Available</label>
            <input type="checkbox" v-model="pet.available" />
          </div>
          <div id="fixed">
            <label>Is Fixed</label>
            <input type="checkbox" v-model="pet.fixed" />
          </div>
          <button id="button" type="button" v-on:click="cancel()">
            Cancel
          </button>
          &nbsp;
          <button id="button" type="submit" v-on:click.prevent="savePet()">
            Save Pet
          </button>
        </form>
      </div>
    </body>
  </div>
</template>

<script>
import petService from "@/services/PetService.js";
export default {
  name: "add-pet",
  data() {
    return {
      errorMsg: "",

      pet: {
        id: "",
        gender: "",
        name: "",
        type: "",
        color: "",
        age: "",
        image: "",
        tagline: "",
        available: "",
        fixed: "",
      },
    };
  },
  props: ["existingPet"],

  created() {
    if (this.existingPet) {
      this.pet = this.existingPet;
    }
  },

  methods: {
    savePet() {
      petService
        .addPet(this.pet)
        .then((response) => {
          if (response.status === 201 || response.status === 202) {
            this.$router.push({ name: "pets-view" });
            petService.getListOfAllPets().then((response) => {
              let list = response.data;
              this.$store.commit("ADD_PETS", list);
            });
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = error.response.statusText;
          } else if (error.request) {
            this.errorMsg = "We couldn't find the server";
          } else {
            this.errorMsg = "Error - we couldn't create the request";
          }
        });
    },
    cancel() {
      this.$router.push({ name: "pets-view" });
    },
  },
};
</script>
