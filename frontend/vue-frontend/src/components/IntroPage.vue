<template>
  <horizontal-category-select
    :categories="categories"
    :firstSelectedIndex="0"
    @select="changeCategory"
  />
  <ul class="list-grid" v-if="categories.length > 0">
  <li v-for="(complex, i) in complexes" :key="i">
    <my-card
        :image="complex.image"
        :title="complex.name"
        :metro="complex.address.metro.name"
        :price="complex.price"
    />
  </li>
  </ul>
  <div v-else>
    По вашему запросу ничего не найдено
  </div>
  <intro-footer/>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import HorizontalCategorySelect from "@/components/HorizontalCategorySelect.vue";
import IntroFooter from "@/components/IntroFooter.vue";
import {useStore} from "@/store/index";
import MyCard from "@/components/MyCard.vue";

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
    }
  }
})
export default class IntroPage extends Vue {
 store = useStore();
 categories = [
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
 changeCategory(category: any){
  this.store.dispatch('GET_ADVERTIZED_COMPLEXES', category.value);
 }
}

</script>

<style scoped>
  body {

    font-family: 'Open Sans', sans-serif;

    font-size: 15px;
    line-height: 1.6;
    color: #333333;
  }
  *,
  *:before,
  *:after{
    box-sizing: border-box;
  }

  h1, h2, h3, h4, h5, h6{
    margin: 0;
  }
  /*Container*/

  .container{
    width: 100%;
    max-width: 1000px;
    margin: 0px auto;
  }

  /*Intro */

  .intro {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    height: 100vh;

    background-size: cover;
  }


  .intro__inner{
    width: 100%;
    margin: 0 auto;

    text-align: center;
  }

  /*Header*/

  .header{
    width: 100%;
    padding-top: 30px;

    position: absolute;
    top:0;
    left: 0;
    right: 0;
    z-index: 1000;
  }

  .header__inner{

    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .header__logo{
    font-size: 30px;
    font-weight: 700;
    color: #333333;

  }

  /*NAv*/
  .nav{
    font-size: 15px;
    text-transform: uppercase;
  }

  .nav__link{
    display: inline-block;
    margin: 0 15px;
    color: black;
    text-decoration: none;

    transition: color 0.2s linear;
  }

  .nav__link:hover{
    color: lightsalmon;
  }

  /*button*/
  .btn{
    display: inline-block;
    vertical-align: top;
    padding: 10px 15px;

    margin: 60px auto 0;
    border: 3px solid black;

    font-size: 15px;
    font-weight: bold;
    color: black;

    text-transform: uppercase;
    transition: color 0.2s linear;
  }

  .btn:hover{
    color: lightsalmon;
  }

  /* Footer */
  .footer{
    padding-top: 60px;

  }

  .footer__inner{
    padding-bottom: 60px;
    display: flex;
  }

  .footer__col--first {
    width: 50%;
  }

  .footer__col--second {
    width: 10%;
  }

  .footer__col--third {
    width: 40%;
  }

  .footer__logo{
    margin: 30px 0;
    font-size: 30px;
    font-weight: bold;
    color: #333333;

  }

  .footer__text{
    margin-bottom: 30px;
    font-size: 15px;
    color: black;
  }

  .footer__social-header{
    font-size: 15px;
    font-weight: bold;
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

  .footer__title{
    margin: 30px 0;

    font-size: 15px;
    font-weight: bold;
    color: black;
    text-transform: uppercase;
  }

  /* Subscribe */
  .subscribe {
    width: 100%;
    max-width: 380px;
    display: flex;
  }

  .subscribe__input{

    width: 50%;
    padding: 10px;
    height: 30px;

    border: 1px solid black;
    border-right: 0;

    font-family: 'Open Sans', sans-serif;
    font-size: 15px;
    line-height: 1.1;

    font-style: italic;
    font-weight: lighter;
  }

  .subscribe__input::placeholder{
    color: #cccccc;
  }

  .subscribe__input:focus {
    outline: 0;
    border-color: #95e1d3;
  }

  .subscribe__btn{
    width: 50%;
    height: 30px;

    background-color: #7488ab;
    border: 0;
    cursor: pointer;

    font-family: 'Open Sans', sans-serif;
    line-height: 1.1;
    font-size: 15px;
    font-weight: 700;
    text-transform: uppercase;

    transition: background .2s linear;
  }

  .subscribe__btn:hover{
    background-color: #61728d;
  }

  .subscribe__btn:focus {
    outline: 0;
  }

  .list-grid {
    display: grid;
    display: -ms-grid;
    grid-template-columns: auto auto;
  }
  .list-grid li {
    list-style-type: none;
    align-items: center;
    padding-left: 30px;
    list-style-position: inside;
    text-transform: none;
  }

</style>