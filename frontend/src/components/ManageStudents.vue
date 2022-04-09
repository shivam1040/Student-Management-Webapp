<template>
  <AfterLoginHeader :status="status" />

  <div class="container mt-5 text-center">
    <div class="table-content">
      <table
        class="table table-borded table-responsive table-striped"
        id="table-list"
      >
        <thead class="table-light">
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>
              <div class="btn-group">
                <button
                  class="btn btn-secondary btn-sm dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <b>Sex</b>
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('sex', $event)"
                      href="#"
                      >M</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('sex', $event)"
                      href="#"
                      >F</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('sex', $event)"
                      href="#"
                      >O</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('sex', $event)"
                      href="#"
                      >All</a
                    >
                  </li>
                </ul>
              </div>
            </th>
            <th>
              <div class="btn-group">
                <button
                  class="btn btn-secondary btn-sm dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <b>Class</b>
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('class', $event)"
                      href="#"
                      >I</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('class', $event)"
                      href="#"
                      >II</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('class', $event)"
                      href="#"
                      >III</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('class', $event)"
                      href="#"
                      >IV</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('class', $event)"
                      href="#"
                      >V</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      v-on:click="filter('class', $event)"
                      href="#"
                      >All</a
                    >
                  </li>
                </ul>
              </div>
            </th>
            <th>Maths' Mark</th>
            <th>Science's Mark</th>
            <th>Language's Mark</th>
            <th>
              Percentage
              <button
                v-if="sort == 'asc' || firstSort == false"
                v-on:click="sorting('dsc')"
                type="button"
                class="btn btn-outline-info btn-sm"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-arrow-up-short"
                  viewBox="0 0 16 16"
                >
                  <path
                    fill-rule="evenodd"
                    d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"
                  />
                </svg>
              </button>
              <button
                v-if="sort == 'dsc'"
                v-on:click="sorting('asc')"
                type="button"
                class="btn btn-outline-info btn-sm"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-arrow-down-short"
                  viewBox="0 0 16 16"
                >
                  <path
                    fill-rule="evenodd"
                    d="M8 4a.5.5 0 0 1 .5.5v5.793l2.146-2.147a.5.5 0 0 1 .708.708l-3 3a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L7.5 10.293V4.5A.5.5 0 0 1 8 4z"
                  />
                </svg>
              </button>
            </th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in students" :key="item">
            <td>{{ item.id }}</td>
            <td contenteditable="true" @input="onInput(item, $event, 'name')">
              {{ item.name }}
            </td>
            <td contenteditable="true" @input="onInput(item, $event, 'sex')">
              {{ item.sex }}
            </td>
            <td contenteditable="true" @input="onInput(item, $event, 'class')">
              {{ item.class }}
            </td>
            <td contenteditable="true" @input="onInput(item, $event, 'maths')">
              {{ item.maths }}
            </td>
            <td
              contenteditable="true"
              @input="onInput(item, $event, 'science')"
            >
              {{ item.science }}
            </td>
            <td
              contenteditable="true"
              @input="onInput(item, $event, 'language')"
            >
              {{ item.language }}
            </td>
            <td>{{ item.percentage }}</td>
            <td>
              <a href="#" v-on:click="update(item)" class="btn btn-primary"
                >Update</a
              >
              <a href="#" v-on:click="del(item)" class="btn btn-danger">Delete</a>
            </td>
          </tr>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li
            v-bind:class="index <= 1 ? 'disabled' : ''"
            v-on:click="index > 1 && page(true, false, false)"
            class="page-item"
          >
            <a class="page-link" href="#">Previous</a>
          </li>
          <li
            v-bind:class="index == item ? 'active' : ''"
            v-for="item in pageNumberedButtons"
            :key="item"
            class="page-item"
          >
            <a
              v-on:click="page(false, true, false, $event)"
              class="page-link"
              href="#"
              >{{ item }}</a
            >
          </li>
          <li
            v-bind:class="index >= current ? 'disabled' : ''"
            v-on:click="index < current && page(false, false, true)"
            class="page-item"
          >
            <a class="page-link" href="#">Next</a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import AfterLoginHeader from "./AfterLoginHeader.vue";
var pagination = 5;
var pageNumbered = 3;
var isLastPrev = false;
var filterSex = "";
var filterClass = "";
var url = "";
var baseUrl='http://localhost:3000/'
export default {
  name: "ManageStudents",
  components: {
    AfterLoginHeader,
  },
  data() {
    return {
      students: [],
      current: 0,
      index: 0,
      pagination: pagination,
      pageNumberedButtons: [],
      arrayIndexTrack: 1,
      sort: "",
      status: false,
      firstSort: false,
    };
  },
  methods: {

    async loadData(){
        if (!localStorage.getItem("user-info"))
      this.$router.push({ name: "HomePage" });
    let result = await axios.get(baseUrl+"students/");
    this.students = result.data;
    this.current = Math.ceil(result.data.length / pagination);
    if (this.current > 0) {
      this.index = 1;
      this.pageNumberedButtons.length=0;
      for (let i = 1; i <= this.current; i++) {
        if (i > pageNumbered) break;
        this.pageNumberedButtons.push(i);
      }
    }
    result = await axios.get(
      baseUrl+`students?_page=1&_limit=${pagination}`
    );
    this.students = result.data;
    },

   async del(item){
      let result=await axios.delete(baseUrl+"students/"+item.id)
      if(result.status==200){
        this.status=true
        this.loadData();
      }
    },

    onInput() {
      arguments[0][arguments[2]] = arguments[1].target.innerText;
      let percentage =
        ((parseFloat(arguments[0]["maths"]) +
          parseFloat(arguments[0]["science"]) +
          parseFloat(arguments[0]["language"])) /
        300)*100;
      percentage = parseFloat(percentage.toFixed(2));
      arguments[0]["percentage"] = percentage;
    },

    async update() {
      let percentage =
       ((parseFloat(arguments[0]["maths"]) +
          parseFloat(arguments[0]["science"]) +
          parseFloat(arguments[0]["language"])) /
        300)*100;
        
      percentage = parseFloat(percentage.toFixed(2));
      
      let result = await axios.put(
        baseUrl+"students/" + arguments[0]["id"],
        {
          name: arguments[0]["name"],
          sex: arguments[0]["sex"],
          class: arguments[0]["class"],
          maths: arguments[0]["maths"],
          science: arguments[0]["science"],
          language: arguments[0]["language"],
          percentage: percentage
        }
      );
      if (result.status == 200) {
        this.status = true;
      }
    },

    async sorting() {
      this.firstSort = true;
      if (arguments[0] == "dsc") {
        if (url == "") {
          let result = await axios.get(
            baseUrl+`students?_search=percentage-&_page=${this.index}&_limit=${pagination}`
          );
          this.students = result.data;
        } else {
          let result = await axios.get(
            url +
              `&_search=percentage-&_page=${this.index}&_limit=${pagination}`
          );
          this.students = result.data;

          console.log(this.students);
        }
        this.sort = "dsc";
      } else {
        if (url == "") {
          let result = await axios.get(
            baseUrl+`students?_search=percentage+&_page=${this.index}&_limit=${pagination}`
          );
          this.students = result.data;
        } else {
          let result = await axios.get(
            url +
              `&_search=percentage+&_page=${this.index}&_limit=${pagination}`
          );
          this.students = result.data;
          console.log(this.students);
        }
        this.sort = "asc";
      }
    },
    async page() {
      if (arguments[2]) {
        this.index = this.index + 1;
        if (this.index <= this.current) {
          if (
            this.index >
            this.pageNumberedButtons[this.pageNumberedButtons.length - 1]
          ) {
            this.pageNumberedButtons.length = 0;
            this.arrayIndexTrack = 0;
            let count = 0;
            for (let i = this.index; i <= this.current; i++) {
              if (count > pageNumbered) break;
              this.pageNumberedButtons.push(i);
              count = count + 1;
            }
          }
          if (isLastPrev) {
            this.arrayIndexTrack = this.arrayIndexTrack + 1;
            isLastPrev = false;
          }
          let urlPage = "";
          console.log(this.sort);
          if (this.sort == "asc") {
            if (url == "")
              urlPage =
                baseUrl+"students?_search=percentage+&";
            else urlPage = url + "&_search=percentage+&";
          } else if (this.sort == "dsc") {
            if (url == "")
              urlPage =
                baseUrl+"students?_search=percentage-&";
            else urlPage = url + "&_search=percentage-&";
          } else {
            if (url == "") urlPage = baseUrl+"students?";
            else urlPage = url + "&";
          }
          let result = await axios.get(
            urlPage +
              `_page=${
                this.pageNumberedButtons[this.arrayIndexTrack]
              }&_limit=${pagination}`
          );
          this.arrayIndexTrack = this.arrayIndexTrack + 1;
          this.students = result.data;
        }
      } else if (arguments[0]) {
        this.index = this.index - 1;
        if (this.index <= this.current) {
          if (this.index < this.pageNumberedButtons[0]) {
            this.pageNumberedButtons.length = 0;
            let count = 0;
            for (let i = this.index; i >= 1; i--) {
              if (count > pageNumbered) break;
              this.pageNumberedButtons.unshift(i);
              count = count + 1;
            }
            this.arrayIndexTrack = this.pageNumberedButtons.length + 1;
          }

          if (this.arrayIndexTrack >= this.pageNumberedButtons.length)
            this.arrayIndexTrack = this.arrayIndexTrack - 2;
          else this.arrayIndexTrack = this.arrayIndexTrack - 1;
          let urlPage = "";
          if (this.sort == "asc") {
            if (url == "")
              urlPage =
                baseUrl+"students?_search=percentage+&";
            else urlPage = url + "&_search=percentage+&";
          } else if (this.sort == "dsc") {
            if (url == "")
              urlPage =
                baseUrl+"students?_search=percentage-&";
            else urlPage = url + "&_search=percentage-&";
          } else {
            if (url == "") urlPage = baseUrl+"students?";
            else urlPage = url + "&";
          }
          let result = await axios.get(
            urlPage +
              `_page=${
                this.pageNumberedButtons[this.arrayIndexTrack]
              }&_limit=${pagination}`
          );
          this.students = result.data;
          //console.log(this.arrayIndexTrack)
          isLastPrev = true;
        }
      } else if (arguments[1]) {
        let page = arguments[3].target.innerText;
        this.index = parseInt(page);
        this.arrayIndexTrack = this.pageNumberedButtons.indexOf(parseInt(page));
        console.log(this.arrayIndexTrack);
        let urlPage = "";
        if (this.sort == "asc") {
          if (url == "")
            urlPage =
              baseUrl+"students?_search=percentage+&";
          else urlPage = url + "&_search=percentage+&";
        } else if (this.sort == "dsc") {
          if (url == "")
            urlPage =
              baseUrl+"students?_search=percentage-&";
          else urlPage = url + "&_search=percentage-&";
        } else {
          if (url == "") urlPage = baseUrl+"students?";
          else urlPage = url + "&";
        }
        let result = await axios.get(
          urlPage + `_page=${page}&_limit=${pagination}`
        );
        this.students = result.data;
        isLastPrev = true;
      }
    },

    async filter() {
      if (arguments[0] == "class" || arguments[0] == "sex") {
        let cl = arguments[1].target.innerText;
        let urlPage = "";
        if (this.sort == "asc") {
          urlPage = "&_search=percentage+";
        } else if (this.sort == "dsc") {
          urlPage = "&_search=percentage-";
        }
        if (arguments[0] == "class" && cl != "All")
          filterClass = arguments[1].target.innerText;
        else if (arguments[0] == "sex" && cl != "All")
          filterSex = arguments[1].target.innerText;
        if (cl != "All") {
          if (filterSex == "")
            url = baseUrl+`students?_search=clasS:${filterClass}`;
          if (filterClass == "")
            url = baseUrl+`students?_search=sex:${filterSex}`;
          if (filterClass != "" && filterSex != "")
            url = baseUrl+`students?_search=sex:${filterSex},clasS:${filterClass}`;
          let result = await axios.get(url);
          this.students = result.data;
          this.current = Math.ceil(result.data.length / pagination);
          isLastPrev = false;
           console.log(this.current);
          if (this.current > 0) {
            this.index = 1;
            this.pageNumberedButtons.length = 0;
            this.arrayIndexTrack = 1;
            for (let i = 1; i <= this.current; i++) {
              if (i > pageNumbered) break;
              this.pageNumberedButtons.push(i);
            }
          } else {
            this.index = 0;
            this.pageNumberedButtons.length = 0;
            (this.current = 0), (this.arrayIndexTrack = 1);
            isLastPrev = false;
          }

          result = await axios.get(
            url + `&_page=${this.index}&_limit=${pagination}` + urlPage
          );
          this.students = result.data;
        } else {
          if (arguments[0] == "class") {
            filterClass = "";
          } else filterSex = "";
          if (filterSex == "")
            url = baseUrl+`students?_search=clasS:${filterClass}&`;
          if (filterClass == "")
            url = baseUrl+`students?_search=sex:${filterSex}&`;
          if (filterClass == "" && filterSex == "")
            url = baseUrl+`students?`;
          let result = await axios.get(url);
          this.students = result.data;
          this.current = Math.ceil(result.data.length / pagination);
         
          isLastPrev = false;
          console.log(this.current, 'a')
          if (this.current > 0) {
            this.index = 1;
            this.pageNumberedButtons.length = 0;
            this.arrayIndexTrack = 1;
            for (let i = 1; i <= this.current; i++) {
              if (i > pageNumbered) break;
              this.pageNumberedButtons.push(i);
            }
          } else {
            this.index = 0;
            this.pageNumberedButtons.length = 0;
            (this.current = 0), (this.arrayIndexTrack = 1);
            isLastPrev = false;
          }
          result = await axios.get(
            url + `_page=${this.index}&_limit=${pagination}` + urlPage
          );
          this.students = result.data;
        }
      }
    },
  },

  async mounted() {
   this.loadData();
  },
};
</script>