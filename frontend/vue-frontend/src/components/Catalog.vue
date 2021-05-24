<template>

  <div class="catalog">

    <back-button
    />

    <div class="main">
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

<!--      &lt;!&ndash; выпажадающй список сроков сдачи&ndash;&gt;-->
<!--      <div class="term-of-delivery">Сортировка-->
<!--        <nav class="term-of-delivery-ds">-->
<!--          <dropdown-selector-->
<!--              nameFieldTitle="name"-->
<!--              defaultText="Не выбрано"-->
<!--              :options="termdelivery"-->
<!--              @select="termOfDeliveryS"-->
<!--          />-->
<!--        </nav>-->
<!--      </div>-->
<!--      &lt;!&ndash; заголовок-->
<!--  площадь от и до&ndash;&gt;-->
<!--      <div class="square"></div>-->
<!--      &lt;!&ndash; кнопка поиск&ndash;&gt;-->
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
              :metro="complex.address?.metro?.name || ''"
              :price="complex.price"
            @delete-complex="deleteComplex(complex)"
            @click="goToComplex(complex.id)"
        />
      </li>
    </ul>
    <div v-else>
      По вашему запросу ничего не найдено
    </div>
        <add-button v-if="store.getters.CAN_MANAGE_COMPLEXES"
        @click="createComplex"
        />
    </div>
    </div>

  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {Store, useStore} from "@/store/index";
import MyCard from "@/components/MyCard.vue";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {Complex, CreationResponse, District, Metro} from "@/generated-api/data-contracts";
import RangeSlider from "@/components/RangeSlider.vue";
import EditButton from "@/components/EditButton.vue";
import AddButton from "@/components/AddButton.vue";
import BackButton from "@/components/BackButton.vue";

@Options({
  name:"catalog",
  components: {
    BackButton,
    MyCard,
    DropdownSelector,
    RangeSlider,
    EditButton,
    AddButton
  },
  computed:{
    complexes(): Complex[]{
      return this.store.getters.complexes;
    },
    metros(): Metro[]{
      return [{name: "Любая", id: null} as unknown as Metro].concat(this.store.getters.metros);
    },//return [{name: "Любая", id: null}];
    districts(){
      return [{name: "Любой", id: null} as unknown as District].concat(this.store.getters.districts);
    },
    complexSearchParamsChanged(){
      return this.store.getters.complexSearchParamsChanged;
    },
    creation_response(){
      return this.store.getters.creation_response;
    },

  },
  watch:{
    creation_response({id, created}: CreationResponse){
      if (this.waitingForComplex){
        if (created)
          this.$router.push(`/complex/${id}/edit`);
        this.waitingForComplex = false;
      }
    }
  }
})
export default class Catalog extends Vue {
  store : Store = useStore();
  sort: {name:string, value: string | null, ascending: boolean}[] = [
    {
      name: "По умолчанию",
      value: "id",
      ascending: false
    },
    {
      name: "По цене (по убыванию)",
      value: "price",
      ascending: false
    },
    {
      name: "По цене (по возрастанию)",
      value: "price",
      ascending: true
    },
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
  {
    name: "2025",
  value: "2025"
  },
  {
    name: "2026",
    value: "2026"
  }
  ]
  minTempPrice: number = 0;
  maxTempPrice: number = 300000;
  waitingForComplex: boolean = false;
  created(){
    this.store.dispatch('GET_METROS', undefined);
    this.store.dispatch('GET_DISTRICTS', undefined);
    this.setMinPrice(this.minTempPrice);
    this.store.dispatch("GET_SEARCHED_COMPLEXES", undefined);
  }
  setSort({value, ascending}){
    this.store.commit('SET_SORT', `${value},${ascending ? 'asc' : 'desc'}`);
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
  goToComplex(id: number){
    console.debug(id);
    console.debug("going to card");
    this.$router.push(`/complex/${id}`);
  }
  createComplex(){
      this.waitingForComplex = true;
      this.store.dispatch('CREATE_COMPLEX', {
        address: {city: this.store.getters.city},
        name: "Новый комплекс",
        category: this.store.getters.complex_category || "NEW",
        status: "ACCEPTED",
        price: 1000,
    } as Complex);
    }
  deleteComplex({id}: Complex){
    if (id)
    this.store.dispatch('DELETE_COMPLEX', id);
  }
}
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}


.catalog{
  text-align: center;
  max-width: 1060px;
  margin: 0 auto;
  font-size: 15px;
}


.main{
  width: 100%;
  padding-top: 35px;
  display: grid;
  grid-template-columns: 1fr 3fr;
}

.list-grid {
  font-size: 15px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px,auto));
  grid-gap: 30px;
  grid-auto-rows: minmax(150px, auto);
}

.list-grid li {
  align-items: center;
  justify-content: center;
  list-style-type: none;
  list-style-position: inside;
  text-transform: none;
}
.back-button{
  margin: 10px 0 10px ;
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
@media (max-width: 349px){
  .catalog{
    grid-template-columns: 1fr;
  }
}

@media (min-width: 350px) and (max-width: 600px) {
  .catalog{
    grid-template-columns: 1fr 1fr;
  }
  .btn{
    float: none;
  }

}

@media (min-width: 601px) and (max-width: 900px) {
  .catalog{
    grid-template-columns: 1fr 2fr;
  }
}

@media (min-width: 901px) {
  .catalog{
    grid-template-columns: 1fr 3fr;
  }
  .list-grid {
    grid-template-columns: repeat(2, minmax(250px, 3fr));
  }

}

</style>