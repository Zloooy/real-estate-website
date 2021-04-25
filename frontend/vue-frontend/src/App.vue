<template>
  <navigation-header/>
  <horizontal-category-select
      :categories="categories"
      :firstSelectedIndex="1"
  />
  <intro-footer/>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-class-component';
import NavigationHeader from "@/components/NavigationHeader.vue";
import {Store, useStore} from "@/store";
import HorizontalCategorySelect from "@/components/HorizontalCategorySelect.vue";
import IntroFooter from "@/components/IntroFooter.vue";

@Options({
  components: {
    NavigationHeader,
    HorizontalCategorySelect,
    IntroFooter
  },
})
export default class App extends Vue {
  categories: any = [
      {name:"Новостройки"},
    {name: "Вторичка"},
    {name:"Аренда"}
  ];
  store: Store = useStore();
  mounted() {
    this.store.dispatch('GET_CITIES',null);
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
