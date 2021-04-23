<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <login-form v-if="!this.store.getters.authorization_set"/>
  <greeting v-else/>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-class-component';
import LoginForm from "@/components/LoginForm.vue";
import Greeting from "@/components/Greeting.vue";
import {useStore} from "@/store";

@Options({
  components: {
    LoginForm,
    Greeting
  },
  data() {
    return {
      store: useStore()
    }
  },
  methods: {
  }
})
export default class App extends Vue {}
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
