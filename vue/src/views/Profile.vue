<template>
  <div>
    <p>Name: {{$store.state.user.username}}</p>
    <p >Application Status: {{$store.state.volunteers.applicationStatus}}</p>
  </div>
</template>

<script>
export default {
    name: 'profile',
    data(){
        return {
            user: {
                name: '',
                applicationStatus: ''
            }
        }
    },
    computed : {
        applicationStatus() {
            let status =' '
            if(this.$store.state.volunteers.applicationStatus=== 2) {
                status='your application is on pending'
                } 
            return status;
        }
    },
    created() {
        console.log(this.$store.state)
    }

}
</script>

<style>

</style>