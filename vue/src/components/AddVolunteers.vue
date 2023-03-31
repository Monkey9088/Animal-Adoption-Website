<template>
  <div>
    <head>
      <title>Volunteer Application</title>
      <link rel="stylesheet" type="text/css" href="src/assets/global.css" />
    </head>
    <body>
      <div>
        <form class="form">
          <h2>Volunteer Now!</h2>
          <div id="name">
            <label>Name</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-picture-outline"
              placeholder="First Last"
              type="text"
              v-model="volunteer.name"
              required
            />
          </div>
          <div id="username">
            <label>Username</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-user"
              placeholder="Username123"
              type="text"
              v-model="volunteer.username"
              required
            />
          </div>
          <div id="email">
            <label>Email</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-message"
              placeholder="Email@email.com"
              type="text"
              v-model="volunteer.email"
              required
            />
          </div>
          <div id="phone">
            <label>Phone Number</label>
            <el-input
              size="medium"
              prefix-icon="el-icon-phone-outline"
              placeholder="1231231234"
              type="long"
              v-model="volunteer.phone"
              required
            />
          </div>
          <button id="button" type="button" v-on:click="cancel()">
            Cancel
          </button>
          &nbsp;
          <button id="button" type="submit" v-on:click.prevent="submitForm()">
            Submit Application
          </button>
        </form>
      </div>
    </body>
  </div>
</template>

<script>
import volunteerService from "@/services/VolunteerService.js";
export default {
  name: "add-volunteer",
  data() {
    return {
      volunteer: {
        name: "",
        username: "",
        email: "",
        phone: "",
      },
    };
  },
  methods: {
    submitForm() {
      volunteerService
        .createVolunteer(this.volunteer)
        .then((response) => {
          if (response.status === 201 || response.status === 200) {
            volunteerService.findAllVolunteers().then((response) => {
              let list = response.data;
              this.$store.commit("ADD_VOLUNTEERS", list);
            });

            this.$router.push({ name: "volunteers" });
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    cancel() {
      this.$router.push("/");
    },
  },
};
</script>
