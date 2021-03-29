<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <login-form v-if="!this.token"
    @token="setToken($event)"/>
    <greeting v-else
    :token="this.token"
    />
  </div>
</template>

<script>
import LoginForm from "@/components/LoginForm";
import Greeting from "@/components/Greeting";

export default {
  name: 'App',
  components: {
    LoginForm,
    Greeting,
  },
  methods: {
   setToken(token){
      console.debug("Got token", token);
      this.token = token;
   }
  },
  data() {
    return {
      token: null
    }
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