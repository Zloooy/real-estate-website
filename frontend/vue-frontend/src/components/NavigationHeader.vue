<template>
  <header class="navigation-header">
    <div class="header__container"></div>
    <div class="header__inner">
      <div class="header__logo">
        <router-link to="/">Real-Estate</router-link>
      </div>
      <nav class="nav">
        <dropdown-selector
            nameFieldTitle="name"
            :options="cities"
            :firstSelectedOptionIndex="1"
            @select="setCity"
        />
        <router-link to="/tariffs">Тарифы</router-link>
        <router-link to="/blog">Блог</router-link>
        <router-link to="/phone">Телефон</router-link>
      </nav>
    </div>
  </header>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {City} from "@/generated-api/data-contracts";
import {Store, useStore} from "@/store";

@Options({
      components: {
        DropdownSelector
      },
      computed: {
        cities(): City[] {
          return this.store.getters.cities;
        }
      },
      name: "navigation-header",
    }
)
export default class NavigationHeader extends Vue {
  store: Store = useStore();
  setCity(city) {
    this.store.commit('SET_CITY', city);
  }
}
</script>

<style scoped>
.navigation-header{
  width: 100%;
  max-width: 1060px;
  margin: 0 auto;
}

.header__container {
  width: 100%;
  max-width: 1060px;
  margin: 0 auto;
}

.header__inner {
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-decoration: none;
}

.header__logo {

  font-size: 30px;
  font-weight: 700;
  color: #333333;
}
.header__logo>*{
  color: #333333;
  text-decoration: none;
  transition: color 0.2s linear;
}

.header__logo>*:hover{
  color: lightsalmon;
}

.nav>* {
  margin: 0 15px;
  color: #333333;
  text-decoration: none;
  transition: color 0.2s linear;
}

.nav>*:hover{
  color: lightsalmon;
}

@media (max-width: 600px) {
  .nav>* {
    display: flex;
    flex-direction: column;
  }
}
@media (min-width: 601px) {
  .nav>* {
    display: inline-block;
  }
}

.nav>* {
   margin: 0 15px;
   color: #333333;
   text-decoration: none;
   transition: color 0.2s linear;
 }

.nav>*:hover{
  color: lightsalmon;
}

</style>