<template>
  <div class="complex-page">

    <horizontal-category-select
        :categories="categories"
        :firstSelectedIndex="0"
        @select="changeCategory"
    />
    <div class="complex-name">JK PAMPUSHKA</div>
    <div class="intro-complex">
      <div class="img-complex">
        img
      </div>
      <main-data-complex
          address="address"
          metro = "metro"
          district = "district"
      />
    </div>
    <div class="about-complex">
      <div class="headers">О ПРОЕКТЕ</div>
      <text-about/>
    </div>

    <div class="complex-flats">
      <div class="headers">КВАРТИРЫ КОМПЛЕКСА</div>
      <div class="flats-list">
        <mini-flat-card
        rooms="2"
        price="6500000"
        square="50"
        floor="6"/>
        <mini-flat-card/>
        <mini-flat-card/>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import HorizontalCategorySelect from "@/components/HorizontalCategorySelect.vue";
import TextAbout from "@/components/TextAbout.vue";
import MainDataComplex from "@/components/MainDataComplex.vue";
import MiniFlatCard from "@/components/MiniFlatCard.vue";
import {Store, useStore} from "@/store/index";
import {ComplexQuery} from "@/generated-api/data-contracts";
import {MutationPayload} from "vuex";

@Options({
  name:"complex-page",
  components: {
    HorizontalCategorySelect,
    MainDataComplex,
    TextAbout,
    MiniFlatCard
  }

})
export default class СomplexPage extends Vue{
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
}

</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}

.complex-page{

  text-align: center;
  font-family: 'HelveticaNeueCyr', sans-serif;
  font-size: 15px;
  line-height: 1.6;
  color: #333333;
  max-width: 1060px;
  margin: 0 auto;
}

.complex-name{
  margin: 0 auto;
  margin-top: 60px;
  width: 100%;
  text-align: left;
  max-width: 1060px;
  font-size: 30px;
  font-weight: 700;
}

.intro-complex {
  width: 100%;
  padding-top: 30px;
  font-size: 15px;
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-auto-rows: minmax(150px, auto);
  grid-column-gap: 15px;

}

.img-complex{
  background-color: steelblue;
}

.headers{
  margin-top: 60px;
  width: 100%;
  text-align: left;
  font-size: 20px;
  font-weight: 700;
}

.about-complex{
  padding-top: 30px;
}

.flats-list{
  padding-top: 30px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px,auto));
  grid-gap: 15px;
}
@media (max-width: 349px){
  .intro-complex {
    grid-template-columns: 1fr;
  }
  .complex-name{
    margin-top: 30px;
    text-align: center;
  }
  .headers{
    margin-top: 30px;
    text-align: center;
  }
  .flats-list{
    grid-template-columns: repeat(1, 1fr);
  }
}

@media (min-width: 350px) and (max-width: 600px) {
  .intro-complex {
    grid-template-columns: 3fr 2fr;
  }


}

@media (min-width: 601px) and (max-width: 900px) {

}

@media (min-width: 901px) {

}

</style>
