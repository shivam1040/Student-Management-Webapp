<template>
  <AfterLoginHeader :status="status" />
  <div class="container mt-5">
    <form class="row g-3">
      <div class="col-md-4">
        <label for="validationDefault01" class="form-label">Name</label>
        <input
          v-model="Name"
          type="text"
          class="form-control"
          id="validationDefault01"
          required
        />
      </div>
      <div class="col-md-3">
        <label for="validationDefault04" class="form-label">Sex</label>
        <select
          class="form-select"
          id="validationDefault04"
          required
          v-model="sex"
        >
          <option selected disabled value="">Choose...</option>
          <option>M</option>
          <option>F</option>
          <option>O</option>
        </select>
      </div>
      <div class="col-md-3">
        <label for="validationDefault04" class="form-label">Class</label>
        <select
          v-model="Class"
          class="form-select"
          id="validationDefault04"
          required
        >
          <option selected disabled value="">Choose...</option>
          <option>I</option>
          <option>II</option>
          <option>III</option>
          <option>IV</option>
          <option>V</option>
        </select>
      </div>
      <div class="col-md-3">
        <label for="validationDefault05" class="form-label"
          >Marks in Maths</label
        >
        <input
          v-model="maths"
          type="number"
          class="form-control"
          id="validationDefault05"
          required
        />
      </div>
      <div class="col-md-3">
        <label for="validationDefault05" class="form-label"
          >Marks in Science</label
        >
        <input
          v-model="science"
          type="number"
          class="form-control"
          id="validationDefault05"
          required
        />
      </div>
      <div class="col-md-3">
        <label for="validationDefault05" class="form-label"
          >Marks in Language</label
        >
        <input
          v-model="language"
          type="number"
          class="form-control"
          id="validationDefault05"
          required
        />
      </div>
      <div class="col-12">
        <button
          v-on:click="
            addStudents();
            status = false;
          "
          class="btn btn-primary"
          type="button"
        >
          Submit form
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import AfterLoginHeader from "./AfterLoginHeader.vue";
import axios from "axios";
var url = "http://localhost:3000/students";
export default {
  name: "AddStudents",
  components: {
    AfterLoginHeader,
  },
  data() {
    return {
      Name: "",
      sex: "",
      Class: "",
      maths: "",
      science: "",
      language: "",
      status: false,
    };
  },
  methods: {
    async addStudents() {
      let percentage =((this.language + this.maths + this.science) / 300)*100;
      percentage = parseFloat(percentage.toFixed(2));
      let result = await axios.post(url, {
        name: this.Name,
        sex: this.sex,
        class: this.Class,
        maths: this.maths,
        science: this.science,
        language: this.language,
        percentage: percentage,
      });
      if (result.status == 201) {
        this.Name = "";
        this.sex = "";
        this.Class = "";
        this.maths = "";
        this.science = "";
        this.language = "";
        this.status = true;
      }
    },
  },
  mounted() {
    if (!localStorage.getItem("user-info"))
      this.$router.push({ name: "HomePage" });
  },
};
</script>