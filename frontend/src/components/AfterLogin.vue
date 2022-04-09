<template>
  <AfterLoginHeader />
  <h1 class="text-center mt-5">
    Hello {{ user }}! Welcome to the Admin Console
  </h1>
</template>

<script>
import AfterLoginHeader from "./AfterLoginHeader.vue";
export default {
  name: "AfterLogin",
  components: {
    AfterLoginHeader,
  },
  data() {
    return {
      user: "",
    };
  },
  mounted() {
    let user = localStorage.getItem("user-info");
    if (!user) this.$router.push({ name: "HomePage" });
    if(user!=null)
    this.user = JSON.parse(user).username;
    
  },
};
</script>