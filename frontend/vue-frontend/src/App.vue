<template>
  <span class="top-line">
    <dropdown-selector
        nameFieldTitle="name"
        :options="this.cities"
        @select="setCity"
    />
  <span id="nav">
    <router-link to="/tariffs">Тарифы</router-link>
    <router-link to="/blog">Блог</router-link>
    <router-link to="/phone">Телефон</router-link>
  </span>
  </span>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-class-component';
import LoginForm from "@/components/LoginForm.vue";
import Greeting from "@/components/Greeting.vue";
import {Store, useStore} from "@/store";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {City} from "@/generated-api/data-contracts";

@Options({
  components: {
    LoginForm,
    Greeting,
    DropdownSelector
  },
  computed: {
    cities():City[] {
      return this.store.state.cities;
    }
  }
})
export default class App extends Vue {
  store: Store = useStore();
  mounted() {
    this.store.dispatch('GET_CITIES',null);
  }
  setCity(city: City){
    this.store.commit('SET_CITY', city);
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
