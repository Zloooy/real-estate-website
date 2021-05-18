<template>
  <div class="redactor-page" v-if="complex">
    <div class="block">
<!--      {{complex.address}}-->
      Город
      <div class="choose-city">
        <dropdown-selector
          v-if="cities_redactor.length > 0"
          :options="cities_redactor"
          :firstSelectedOptionIndex="1"
          @select="setCity"
        />
      </div>
    </div>

    <div class="block">
      Улица
      <div class="complex-street">
        <input class="street-input" type="text" placeholder="Введите адресс" v-model="complex.address.street">
      </div>
    </div>

    <div class="block">
      Название
      <div class="complex-name">
        <input class="name-input" type="text" placeholder="Введите название комплекса" v-model="complex.name">
      </div>
    </div>

    <div class="block">
      Изображение
      <textarea name="img-input" type="text" placeholder="Введите url изображения" v-model="complex.image"></textarea>
    </div>

    <div class="block">
      Метро
      <dropdown-selector
          :options="metros"
      />
    </div>

     <div class="block">
       Район
       <dropdown-selector
         :options="districts"
     />
     </div>

    <div class="block">
      Текст-описание
      <textarea name="about-input" type="text" placeholder="Ввдите описание" v-model="complex.comment"></textarea>
    </div>

    <div class="block">
      <div class="headers">КВАРТИРЫ КОМПЛЕКСА</div>
<!--      <div class="flats-list">-->
<!--        <mini-flat-card-->
<!--            @click="goToFlat(flat)"-->
<!--            v-for="flat in complex_flats"-->
<!--            :key="flat.id"-->
<!--            :rooms="flat.numberOfRooms"-->
<!--            :price="flat.price"-->
<!--            :square="flat.square"-->
<!--            :floor="flat.floor"-->
<!--        />-->
<!--      </div>-->
      <add-button/>
    </div>

  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import MiniFlatCard from "@/components/MiniFlatCard.vue";
import {Store, useStore} from "@/store/index";
import {Flat} from "@/generated-api/data-contracts";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {Complex, City} from '@/generated-api/data-contracts';

@Options({
  components: {
    DropdownSelector,
    MiniFlatCard,
  },
  computed:{
    cities_redactor(){
      return this.store.getters.cities_redactor;
    },
    metros(){
      return this.store.getters.metros;
    },
    districts(){
      return this.store.getters.districts;
    },
    // complex_flats(){
    //   return this.store.getters.complexFlats;
    // }
  }

})

export default class CatalogRedactor extends Vue {
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_CITIES_REDACTOR', null);
  }
  setCity(value:City){

    // if(this.complex.address?.city) {

    console.log(JSON.stringify(this.complex.address));
    //@ts-ignore
    this.complex.address.city = value;
    if(value.id){
      this.store.dispatch("GET_METROS_REDACTOR", value.id);
      this.store.dispatch("GET_DISTRICTS_REDACTOR", value.id);
      // this.store.dispatch('GET_COMPLEX_FLATS', Number(this.$route.params.id));
    }
  }
  goToFlat({id}:Flat){
    this.$router.push(`/flat/${id}`)
  }

  complex: Complex = {
    address:{city:{name:"Spb"}},
   // address:{street:"Наличная 25"},
    author: {username: "Шмяка"},
    category: "NEW",
    name: "Pampushki",
    image:"https://cn-med.ru/media/1107/kvartira_u_metro_zvezdnaya.jpg?anchor=center&mode=crop&width=1920&rnd=132176877150000000",
    comment:" огогого"
  }

}
</script>

<style scoped>

.redactor-page{
  text-align: left;
  max-width: 1060px;
  margin: 0 auto;
  margin-top: 30px;
}

.block{
  margin-top: 15px;
}

input[type=text]{
  margin-top: 15px;
  width: 100%;
  padding: 12px;
  border-radius: 4px;
  box-sizing: border-box;
  resize: vertical;
}

textarea{
  margin-top: 15px;
  width: 100%;
  padding: 12px;
  box-sizing: border-box;
  resize: vertical;
  height: 100px;
}

@media screen and (max-width: 600px) {

}
</style>
