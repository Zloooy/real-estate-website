<template>


  <div class="catalog">
    <div class="sidebar_content"><!--поиск в каталоге-->
      <!--сортировка по: возрастанию цены и т.п-->
      <div class="sort">Сортировка
        <nav class="sort-ds">
          <dropdown-selector
              nameFieldTitle="name"
              :options="sort"
              @select="setSort"
          />
        </nav>
        <!--выпадающий список метро-->
      </div> <div class="metro">Метро
        <nav class="metro-ds">
          <dropdown-selector
              nameFieldTitle="name"
              :options="metros"
              @select="setMetro"
          />
        </nav>
        <!--выпадающий список district-->
      </div>
      <div class="district">Район
        <nav class="district-ds">
          <dropdown-selector
              nameFieldTitle="name"
              :options="districts"
              @select="setDistrict"
          />
        </nav>
      </div>
      <!--выпадающий список количества комнат-->
      <div class="number-of-rooms">Количество комнат
        <nav class="number-of-rooms-ds">
          <dropdown-selector
              nameFieldTitle="name"
              :options="amountOfRooms"
              @select="setAmountOfRooms"
          />
        </nav>
      </div>
      <!-- заголовок
      стоимость от и до-->
      <div class="price-range">
        Цена
        <br>
      от {{minTempPrice}} Р
      <range-slider
          :min="100"
          :max="300000"
          @max-value="setMaxPrice"
          @min-value="setMinPrice"
          @max-temp-value="setMaxTempPrice"
          @min-temp-value="setMinTempPrice"
      />
      до {{maxTempPrice}} Р
      </div>

      <div class="delivery_date_selector"></div>
      <dropdown-selector
          nameFieldTitle="name"
          :options="deliveryDate"
          @select="setDeliveryDate"
      />
      <button class="btn-search" @click="search">
      Поиск
      </button>
    </div>

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
      </ul>
      <div v-else>
        По вашему запросу ничего не найдено
      </div>
    </div>
    <add-button v-if="store.getters.CAN_MANAGE_COMPLEXES"/>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {Store, useStore} from "@/store/index";
import MyCard from "@/components/MyCard.vue";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {Complex, District, Metro} from "@/generated-api/data-contracts";
import RangeSlider from "@/components/RangeSlider.vue";
import AddButton from "@/components/AddButton.vue";

@Options({
  name:"catalog",
  components: {
    MyCard,
    DropdownSelector,
    RangeSlider,
    AddButton
  },
  computed:{
    complexes(): Complex[]{
      return this.store.getters.complexes;
    },
    metros(): Metro[]{
      return [{name: "Любая", id: null} as unknown as Metro].concat(this.store.getters.metros);
    },
    districts(){
      return [{name: "Любой", id: null} as unknown as District].concat(this.store.getters.districts);
    },
    complexSearchParamsChanged(){
      return this.store.getters.complexSearchParamsChanged;
    }
  }
})
export default class Catalog extends Vue {
  store : Store = useStore();
  sort: {name:string, value: string | null}[] = [
    {
      name: "По умолчанию",
      value: "id"
    },
    {
      name: "По цене",
      value: "price"
    }
  ]
  amountOfRooms: {name:string, value: number | null}[] = [
    {
      name: "Любое",
      value: null
    },
    {
      name:"одна",
      value: 1
    },
    {
      name: "две",
      value: 2
    },
    {
      name: "три",
      value: 3
    }
  ]
  deliveryDate: {name:string, value: string | undefined}[]=[
    {
      name:"Любой",
      value: undefined
    },
  {
    name: "2022",
    value: "2022"
  },
  {
    name: "2023",
    value: "2023"
  },
  {
    name: "2024",
    value: "2024"
  },
  {name: "2025",
  value: "2025"
  },
  {
    name: "2026",
  value: "2026"
  }
  ]
  minTempPrice: number = 0;
  maxTempPrice: number = 300000;
  created(){
    this.store.dispatch('GET_METROS', undefined);
    this.store.dispatch('GET_DISTRICTS', undefined);
    this.store.dispatch("GET_SEARCHED_COMPLEXES", undefined);
  }
  setSort({value}){
    this.store.commit('SET_SORT', value);
  }
  setAmountOfRooms({value}){
    this.store.commit('SET_AMOUNT_OF_ROOMS', value);
  }
  setMetro(m: Metro){
    this.store.commit('SET_METRO', m);
  }
  setDistrict(d: District){
    this.store.commit('SET_DISTRICT', d);
  }
  setDeliveryDate({value}) {
    this.store.commit('SET_COMPLEX_DELIVERY_DATE', value);
  }
  search(){
    console.debug("SEARCHING");
    this.store.dispatch('GET_SEARCHED_COMPLEXES', undefined);
  }
  setMinPrice(minPrice: number){
    this.store.commit('SET_MIN_COMPLEX_PRICE', minPrice);
  }
  setMaxPrice(maxPrice: number){
    this.store.commit('SET_MAX_COMPLEX_PRICE', maxPrice);
  }
  setMinTempPrice(minTempPrice: number){
    this.minTempPrice=minTempPrice;
  }
  setMaxTempPrice(maxTempPrice: number){
    this.maxTempPrice=maxTempPrice;
  }
}
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}

.catalog {
  max-width: 1060px;
  margin: 0 auto;
  padding-top: 60px;
  font-size: 15px;
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-gap: 30px;
}

.list-grid {
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
  max-width: 350px;
  list-style-position: inside;
  text-transform: none;
}

.centered {
  text-align: center;
}

@media (max-width: 349px){
  .catalog{
    grid-template-columns: 1fr;
  }
}

@media (min-width: 350px) and (max-width: 600px) {
  .catalog{
    grid-template-columns: 1fr 1fr;
  }
}

@media (min-width: 601px) and (max-width: 900px) {
  .catalog{
    grid-template-columns: 1fr 1.5fr;
  }
}

@media (min-width: 901px) {
  .catalog{
    grid-template-columns: 1fr 3fr;
  }
}
.price-range {
  display: block;
}
.sidebar_content {
  text-align: left;
}
.sidebar_content > * {
  margin-top: 10px;
}

</style>
