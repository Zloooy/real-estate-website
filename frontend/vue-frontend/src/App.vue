<template>
  <navigation-header/>
  <router-view/>
<!--  <intro-page/>-->
</template>

<script lang="ts">
import {Options, Vue} from 'vue-class-component';
import NavigationHeader from "@/components/NavigationHeader.vue";
import {Store, useStore} from "@/store";
import IntroPage from "@/components/IntroPage.vue";
import RangeSlider from "@/components/RangeSlider.vue";

@Options({
  components: {
    NavigationHeader,
    IntroPage,
    RangeSlider
  },
})
export default class App extends Vue {
  store: Store = useStore();
  created() {
    this.store.dispatch('GET_CITIES',null);
  }
}
</script>

<style>
/*#657D87
#859497
#708188
#90B8DA
#76ACDA
#174A75
#496B87
#4682B4

#4B7B9D*/

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
  padding-left:5%;
  padding-right:5%;
}
ul {
  padding-inline-start: 0;
}
</style>
