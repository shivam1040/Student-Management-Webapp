<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Student Management Webapp</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <!-- <a class="nav-link active" aria-current="page" href="/afterLogin"
              >Home</a
            > -->
            <router-link class="nav-link active" aria-current="page" to="/afterLogin">Home</router-link>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Actions
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li>
                <!-- <a class="dropdown-item" href="/addStudents">Add Students</a> -->
                <router-link class="dropdown-item" to="/addStudents">Add Students</router-link>
              </li>
              <li>
                <!-- <a class="dropdown-item" href="/manageStudents"
                  >Manage Students</a
                > -->
                <router-link class="dropdown-item" to="/manageStudents">Manage Students</router-link>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a
              v-on:click="logout()"
              class="nav-link active"
              aria-current="page"
              href="#"
              >Logout</a
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container">
    <div v-if="status" class="alert alert-success" role="alert">Success</div>
  </div>
</template>

<script>
var status = false;
export default {
  name: "AfterLoginHeader",
  props: {
    status
  },
  methods: {
    logout() {
      localStorage.clear();
      this.$router.push({ name: "HomePage" });
    },
  },
};
</script>