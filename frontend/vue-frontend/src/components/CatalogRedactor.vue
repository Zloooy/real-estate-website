<template>
  <div class="redactor-page" v-if="complex">
    <back-button
    />
    <div class="first-block">
      Название
      <div class="complex-name">
        <input class="name-input" type="text" placeholder="Введите название комплекса" v-model="complex.name">
      </div>
    </div>

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

<!--    <div class="block">
      Метро
      <dropdown-selector
          :options="metros"
      />
    </div>-->

    <div class="block">
      Район
      <dropdown-selector
          :options="districts"
      />
    </div>

    <div class="block">
      Категория
      <dropdown-selector
          :options="complex_category"
      />
    </div>

    <div class="block">
      Реклама
      <input type="checkbox"  v-model="complex.advertized">
    </div>

    <div class="block">
      Улица
      <div class="complex-street">
        <input class="street-input" type="text" placeholder="Введите адресс" v-model="complex.address.street">
      </div>
    </div>

    <div class="block">
      Цена
      <input class="price-input" type="number" placeholder="Введите стоимость" v-model="complex.price">
    </div>

    <div class="block">
      Дата сдачи
      <div class="complex-street">
        <input class="date-input" type="text" placeholder="Введите дату сдачи" v-model="complex.deliveryDate">
      </div>
    </div>

    <div class="block">
      Изображение
      <textarea name="img-input" type="text" placeholder="Введите url изображения" v-model="complex.image"></textarea>
    </div>

    <div class="contacts">
      Контакты
      <dropdown-selector v-if="contacts" :options="contacts" :firstSelectedIndex="0" @select="setContacts"/>
      <div v-if="complex.contacts">
      <div class="block2"> Имя <input class="nameС-input" type="text" placeholder="Имя" v-model="complex.contacts.name"></div>
      <div class="block2"> Телефон<input class="phone-input" type="text" placeholder="Телефон" v-model="complex.contacts.phone"></div>
      <div class="block2"> Почта<input class="emailC-input" type="text" placeholder="почта" v-model="complex.contacts.email"></div>
      <div class="block2"> Фото <textarea name="img-input" type="text" placeholder="Введите url фотографии" v-model="complex.contacts.photo"></textarea></div>
      </div>
    </div>

    <div class="block">
      Текст-описание
      <textarea name="about-input" type="text" placeholder="Ввeдите описание" v-model="complex.comment"></textarea>
    </div>

    <div class="block">
      <div class="headers">КВАРТИРЫ КОМПЛЕКСА</div>
      <div class="flats-list">
        <mini-flat-card
            @click="goToFlat(flat)"
            @delete-flat="deleteFlat(flat)"
            v-for="flat in complex_flats"
            :key="flat.id"
            :rooms="flat.numberOfRooms"
            :price="flat.price"
            :square="flat.square"
            :floor="flat.floor"
        />
      </div>
      <add-button v-if="store.getters.CAN_EDIT_FLATS"
      @click="createFlat"/>
    </div>
      <button @click="saveComplex">
        Записать
      </button>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import MiniFlatCard from "@/components/MiniFlatCard.vue";
import {Store, useStore} from "@/store/index";
import {Contacts, Flat} from "@/generated-api/data-contracts";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {City} from '@/generated-api/data-contracts';
import AddButton from "@/components/AddButton.vue";
import BackButton from "@/components/BackButton.vue";

@Options({
  name:"complex",
  components: {
    BackButton,
    DropdownSelector,
    MiniFlatCard,
    AddButton
  },
  computed:{
    complex(){
      return this.store.getters.complex;
    },
    cities_redactor(){
      return this.store.getters.cities_redactor;
    },
    metros(){
      return this.store.getters.metros;
    },
    districts(){
      return this.store.getters.districts;
    },
    complex_category(){
      return this.store.getters.complex_category;
    },

    complex_flats(){
      return this.store.getters.complexFlats;
    },

    creation_response(){
      return this.store.getters.creation_response;
    },
    contacts() {
      return this.store.getters.contacts;
    }
    },
    watch: {
      creation_response({id, created}) {
        if (this.waitingForFlat) {
          if (created)
            this.$router.push(`/flat/${id}/edit`);
          this.waitingForFlat = false;
        }
      }
    }
})

export default class CatalogRedactor extends Vue {
  store: Store = useStore();
  waitingForFlat: boolean = false;
  created(){
    this.store.dispatch('GET_CITIES_REDACTOR', null);
    this.store.dispatch('GET_COMPLEX', Number(this.$route.params.id));
    this.store.dispatch('GET_CONTACTS', undefined);
  }
  setCity(value:City){

    // if(this.complex.address?.city) {
    //@ts-ignore
    console.log(JSON.stringify(this.complex.address));
    //@ts-ignore
    this.complex.address.city = value;
    if(value.id){
      this.store.dispatch("GET_METROS_REDACTOR", value.id);
      this.store.dispatch("GET_DISTRICTS_REDACTOR", value.id);
      this.store.dispatch('GET_COMPLEX_FLATS', Number(this.$route.params.id));
    }
  }
  setContacts(value: Contacts){
    //@ts-ignore
    this.complex.contacts = value;
  }
  goToFlat({id}:Flat){
    this.$router.push(`/flat/${id}`)
  }
  createFlat(){
    this.waitingForFlat = true;
    this.store.dispatch('CREATE_FLAT',
        {
          complex: this.store.getters.complex,
          estateType: "FLAT"
        } as Flat);
  }
  deleteFlat({id}: Flat){
    console.debug("DELITING FLAT");
    this.store.dispatch('DELETE_FLAT', id || 47);
  }

  saveComplex(){
    if (this.store.getters.complex)
    this.store.dispatch('EDIT_COMPLEX', this.store.getters.complex);
  }


  complex_category=[
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

}
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}

.redactor-page{
  text-align: left;
  max-width: 1060px;
  margin: 0 auto;
}

.back-button{
  text-align: center;
  margin: 10px 0 10px ;
}
.first-block{
  margin-top: 35px;
  color: steelblue;
}
.block{
  margin-top: 15px;
  color: steelblue;
}

.block2{
  margin-top: 15px;
 color: gray;
}

.contacts{
  margin-top: 15px;
  color: steelblue;
  padding: 10px;
  border: 1px solid steelblue;
}
.headers{
  margin-bottom: 15px;
}
input[type=text],[type=number]{
  margin-top: 15px;
  width: 100%;
  padding: 12px;
  border-radius: 4px;
  box-sizing: border-box;
  resize: vertical;
}

input[type=number]{
  border: 2px solid darkslategray;
}

textarea{
  margin-top: 15px;
  width: 100%;
  padding: 12px;
  box-sizing: border-box;
  resize: vertical;
  height: 100px;
}

.flats-list{
  padding-top: 30px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px,auto));
  grid-gap: 15px;
}

button{

  background: white;
  padding: 10px 15px;
  border: 2px solid #76ACDA;
  border-radius: 5px;
  font-size: 15px;
  font-weight: bold;
  color: #333333;
  transition: color 0.2s linear;
}

button:hover{
  color: lightsalmon;
  background-color: #E6EDF2;

}

@media screen and (max-width: 600px) {

}
</style>
