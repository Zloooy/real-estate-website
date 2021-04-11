<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <login-form v-if="!this.token"
              @token="this.setToken($event)"/>
  <greeting v-else
            :token="this.token"
  />
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import LoginForm from "@/components/LoginForm.vue";
import Greeting from "@/components/Greeting.vue";


@Options({
  components: {
    LoginForm,
    Greeting
  },
  methods: {
    setToken(token: string){
      console.debug("Got token", token);
      this.token = token;
    }
  },
  data() {
    return {
      token: null
    }
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
