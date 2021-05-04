<template>
  <div class="intro-page">
  <horizontal-category-select
    :categories="categories"
    :firstSelectedIndex="0"
    @select="changeCategory"
  />
    <div class="centered">
  <ul class="list-grid" v-if="complexes && complexes.length > 0">
  <li v-for="(complex, i) in complexes" :key="i">
    <my-card
        :image="complex.image"
        :title="complex.name"
        :metro="complex.address.metro.name || ''"
        :price="complex.price"
    />
  </li>
    <li v-for="(complex, i) in complexes.slice(1)" :key="i">
      <my-card
          :image="complex.image"
          :title="complex.name"
          :metro="complex.address.metro.name || ''"
          :price="complex.price"
      />
    </li>
  </ul>
  <div v-else>
    По вашему запросу ничего не найдено
  </div>
      <router-link to="/search">
        перейти ко {{getCategoryDatelny()}}
      </router-link>
    </div>
  <intro-footer/>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import HorizontalCategorySelect from "@/components/HorizontalCategorySelect.vue";
import IntroFooter from "@/components/IntroFooter.vue";
import {Store, useStore} from "@/store/index";
import MyCard from "@/components/MyCard.vue";
import {ComplexQuery} from "@/generated-api/data-contracts";
import {MutationPayload} from "vuex";

@Options({
  name:"intro-page",
  components: {
    HorizontalCategorySelect,
    IntroFooter,
    MyCard
  },
  computed:{
    complexes(){
      return this.store.getters.complexes;
    },
    category(){
      return this.store.getters.complex_category;
    }
  }
})
export default class IntroPage extends Vue {
 store: Store = useStore();
 categories: {
   name: String,
   value:ComplexQuery['estateCategory']
   }[] = [
   {
     name:"Новостройки",
     value: "NEW"
   },
   {
     name: "Вторичка",
     value: "SECONDARY"
   },
   {
     name:"Аренда",
     value: "RENT"
   }
 ];
 created(){
   this.store.subscribe(this.subscribeCityChange);
 }
 changeCategory(category: any){
   this.store.commit('SET_COMPLEX_CATEGORY', category.value);
 }
 subscribeCityChange(mutation: MutationPayload){
   if (mutation.type === 'SET_CITY' || mutation.type === 'SET_COMPLEX_CATEGORY'){
     this.store.dispatch('GET_ADVERTIZED_COMPLEXES', undefined);
   }
 }
 getCategoryDatelny(){
   switch (this.store.getters.complex_category){
     case "NEW":
       return "всем новостройкам";
     case "RENT":
       return "всей аренде"
     case "SECONDARY":
       return "всей вторичке"
       default:
         return null
   }
 }
}

</script>

<style scoped>

  .footer__social-content a{
    display: inline-block;
    vertical-align: middle;
    margin-left: 10px;
    color: #7488ab;
    padding-top: 15px;
    font-size: 20px;
  }

  @media (max-width: 600px){
    .list-grid {
      grid-template-columns: 1fr;
    }
  }
  @media (min-width: 601px) and (max-width: 900px) {
    .list-grid {
      grid-template-columns: repeat(2, minmax(250px, 3fr));
    }
  }
  @media (min-width: 901px) {
    .list-grid {
      grid-template-columns: repeat(3, minmax(250px, 3fr));
    }
  }
  .list-grid {
    display: inline-grid;
    display: -ms-grid;
    margin-top: 60px;
    margin-bottom: 30px;
  }
  .list-grid li {
    align-items: center;
    justify-content: center;
    list-style-type: none;
    max-width: 350px;
    list-style-position: inside;
    text-transform: none;
  }
  .intro-page {
    text-align: center;
    font-family: 'Open Sans', sans-serif;
    font-size: 15px;
    line-height: 1.6;
    color: #333333;
  }
  .centered {
    text-align: center;
  }

</style>