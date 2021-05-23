<template>
  <div class="intro-page">
<!--    <div class="intro-img">-->
<!--    </div>-->
  <horizontal-category-select
    :categories="categories"
    :firstSelectedIndex="0"
    @select="changeCategory"
  />
  <ul class="list-grid" v-if="complexes && complexes.length > 0">
  <li v-for="(complex, i) in complexes" :key="i">
    <my-card
        :image="complex.image"
        :title="complex.name"
        :metro="complex.address?.metro?.name || ''"
        :price="complex.price"
        @click="goToComplex(complex.id)"
    />
  </li>
  </ul>
  <div v-else>
    По вашему запросу ничего не найдено
  </div>
  <div class="sec-grid">
    <div class="link">
      <router-link to="/search">
        перейти ко {{getCategoryDatelny()}}
      </router-link>
    </div>
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
 goToComplex(id: number){
   console.debug("going to card");
   this.$router.push(`/complex/${id}`);
 }
}

</script>

<style scoped>

.intro-page {
  max-width: 1060px;
  margin: 0 auto;
  text-align: center;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 15px;
  line-height: 1.6;
  color: #333333;
}
  .footer__social-content a{
    display: inline-block;
    vertical-align: middle;
    margin-left: 10px;
    color: #7488ab;
    padding-top: 15px;
    font-size: 20px;
  }

  .list-grid {

    padding-top: 30px;
    font-size: 15px;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px,auto));
    grid-gap: 15px;
    grid-auto-rows: minmax(150px, auto);
  }

  .list-grid li {
    align-items: center;
    justify-content: center;
    list-style-type: none;
    list-style-position: inside;
    text-transform: none;
  }

  /*.intro-img{*/
  /*  height: auto;*/
  /*  text-overflow: ellipsis;*/
  /*  overflow: hidden;*/
  /*  border-radius: 5px;*/
  /*  max-height: 300px;*/
  /*}*/
  /*.intro-img>img{*/
  /*  top: 100px;*/
  /*  max-width: 100%;*/
  /*}*/
  .link{
    margin: 0 auto;
    margin-top: 30px;
  }
  .link>*{

    padding: 10px 15px;
    border: 2px solid #76ACDA;
    border-radius: 5px;
    font-size: 15px;
    font-weight: bold;
    color: #333333;
    transition: color 0.2s linear;
  }

  .link:hover>*{
    color: lightsalmon;
    background-color: #E6EDF2;

  }

  @media (max-width: 600px){

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
</style>