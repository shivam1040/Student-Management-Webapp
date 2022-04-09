<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Student Management Webapp</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <!-- <a class="nav-link active" href="/signUp">Sign-Up</a> -->
            <router-link class="nav-link active" to="/signUp">Sign-Up</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container w-50 mt-5">
    <form>
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Username</label>
        <input
          type="text"
          v-model="username"
          class="form-control"
          id="exampleInputEmail1"
          aria-describedby="emailHelp"
        />
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input
          type="password"
          v-model="password"
          class="form-control"
          id="exampleInputPassword1"
        />
      </div>
      <button v-on:click="login()" type="button" class="btn btn-primary">
        Login
      </button>
    </form>
  </div>
</template>

<script>
var baseUrl='http://localhost:3000/'
import axios from "axios";
export default {
  name: "HomePage",
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    async login() {
      let result = await axios.get(
        baseUrl+`user?_search=username:${this.username},password:${this.password}`
      );
      if (result.status == 200 && result.data.length > 0) {
        localStorage.setItem("user-info", JSON.stringify(result.data[0]));
        this.$router.push({ name: "AfterLogin" });
      }
    },
  },
  mounted() {
    if (localStorage.getItem("user-info"))
      this.$router.push({ name: "AfterLogin" });
  },
};
</script>