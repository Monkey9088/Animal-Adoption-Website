<template>
  <div class="home">
    <head>
      <title>Home</title>
      <link rel="stylesheet" type="text/css" href="src/assets/global.css" />
    </head>

    <body>
      <div class="mainContainer">
        <h1>Welcome to WildBreath Animal Shelter!</h1>
        <p>
          In the market for a new best friend? Check out our adoptable animals!
        </p>
        <div>
          <video width=1000px height=600px autoplay muted loop="loop">
           <source src="/video/test2.mp4" type='video/mp4'>Your browser does not support the video tag
           </video>
         
          <div class="contact">
            <div id="contact">
              <h2>Contact Us:</h2>
              &nbsp;&nbsp;
              <p>Email: wildbreathas@gmail.com</p>
              &nbsp;&nbsp;
              <p>Phone: 555-5555</p>
              &nbsp;&nbsp;
              <p>Address: 123 Pets Ave. Columbus, OH 43230</p>
              &nbsp;&nbsp;
              <p>Venmo: wildbreath123</p>
            </div>
          </div>
        </div>
      </div>
    </body>
  </div>
</template>

<script>
import volunteerService from "@/services/VolunteerService.js";
import petService from "@/services/PetService.js";

export default {
  name: "home",
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
};
</script>
