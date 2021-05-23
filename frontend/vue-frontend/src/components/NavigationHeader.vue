<template>
  <header class="navigation-header">
    <div class="box">
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
      </nav>
    </div>
    <div class="box">
        <div class ="phone">+7-911-772-17-22</div>

        <request-call-button @click="$router.push('/request_call/')"/>

        <logout-button v-if="store.getters.authorization_set"/>
    </div>
  </header>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {City} from "@/generated-api/data-contracts";
import {Store, useStore} from "@/store";
import RequestCallButton from "@/components/RequestCallButton.vue";
import LogoutButton from "@/components/LogoutButton.vue";

@Options({
      components: {
        LogoutButton,
        RequestCallButton,
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
  display: flex;
  justify-content: space-between;
}

.box{
  display: flex;
  align-items: center;
  text-decoration: none;
}

.header__logo {
  float: left;
  font-size: 33px;
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
  float: left;
  color: #333333;
  text-decoration: none;
  transition: color 0.2s linear;
}

.nav>*:hover{
  color: lightsalmon;
}

request-call-button:hover{
  color: #E6EDF2;
}

.phone{
  margin: 0 15px;
  font-size: 14px;
  color: steelblue;
  text-decoration: none;
  transition: none;
}

img{
  margin: 0 15px;
}



@media (max-width: 1000px){
  .phone{
    display: none;
  }
}


@media (min-width: 350px) and (max-width: 600px) {


}

@media (min-width: 601px) and (max-width: 1000px) {
  .phone{
    display: none;
  }
}


</style>