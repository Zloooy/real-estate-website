<template>
  <navigation-header/>
  <intro-page/>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-class-component';
import NavigationHeader from "@/components/NavigationHeader.vue";
import {Store, useStore} from "@/store";
import IntroPage from "@/components/IntroPage.vue";

@Options({
  components: {
    NavigationHeader,
    IntroPage
  },
})
export default class App extends Vue {
  categories = [
      {
        name:"Новостройки",
      },
    {name: "Вторичка"},
    {name:"Аренда"}
  ];
  store: Store = useStore();
  mounted() {
    this.store.dispatch('GET_CITIES',null);
    console.debug('getting adv')
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
