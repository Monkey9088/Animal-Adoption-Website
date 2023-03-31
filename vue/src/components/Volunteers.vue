<template>
  <div>
    <head>
      <title>View Volunteers</title>
      <link rel="stylesheet" type="text/css" href="src/assets/global.css" />
    </head>

    <router-link v-if="hasAuthority" v-bind:to="{ name: 'admin' }"
      >Admin</router-link
    >

    <body>
      <h1>Meet Our Volunteers!</h1>
      <div 
        v-for="volunteer in this.$store.state.volunteers"
        v-bind:key="volunteer.id"
      >
        <div class="volunteer" v-if="volunteer.applicationStatusId === 2">
          <h3 class="name">{{ volunteer.name }}</h3>
          <p class="email">Email: {{ volunteer.email }}</p>
          <p class="phone">Phone Number: {{ volunteer.phone }}</p>
        </div>
      </div>
    </body>
  </div>
</template>

<script>
export default {
  name: "volunteers",
  components: {},
  data() {
    return {
      hasAuthority:
        this.$store.state.user &&
        this.$store.state.user.authorities[0].name === "ROLE_ADMIN",
    };
  },
};
</script>
