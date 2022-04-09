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
            <a class="nav-link active" aria-current="page" href="/">Home</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div v-if="status" class="alert alert-success" role="alert">Success</div>

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
      <p class="error-message" v-if="valid">
        The username and password must be at least 5 characters each
      </p>
      <button
        v-on:click="
          signUp();
          status = false;
        "
        type="button"
        class="btn btn-primary"
      >
        Sign Up
      </button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
var url = "http://localhost:3000/user";
var length = 5;
export default {
  name: "SignUp",
  data() {
    return {
      username: "",
      password: "",
      status: false,
      valid: false,
    };
  },
  methods: {
    async signUp() {
      console.warn(this.username);
      if (this.username.length < length && this.password.length < length) {
        this.valid = true;
        return;
      }
      let result = await axios.post(url, {
        username: this.username,
        password: this.password,
      });
      console.warn(result);
      if (result.status == 201) {
        this.username = "";
        this.password = "";
        this.status = true;
        this.valid = false;
      }
    },
  },
};
</script>