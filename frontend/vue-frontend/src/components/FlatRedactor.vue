<template>
<div class="redactor-page" v-if="flat">

  <div class="block">
    Цена
    <input class="price-input" type="number" placeholder="Введите стоимость" v-model="flat.price">
  </div>

  <div class="block">
    Площадь
    <input class="square-input" type="number" placeholder="Введите площадь" v-model="flat.square">
  </div>

<!--  <div class="block">
    ID КОМПЛЕКСА
    <input class="idC-input" type="number" placeholder="id" v-model="flat.complex.id">
  </div>-->

  <div class="block">
    Изображение
    <textarea name="img-input" type="text" placeholder="Введите url изображения" v-model="flat.image"></textarea>
  </div>

  <div class="block">
    Текст-описание
    <textarea name="about-input" type="text" placeholder="Ввeдите описание" v-model="flat.about"></textarea>
  </div>

  <div class="block">
    Этаж
    <input class="floor-input" type="number" placeholder="Введите номер этажа" v-model="flat.floor">
  </div>

  <div class="block">
    Количество комнат
    <input class="floor-input" type="number" placeholder="Введите колличество комнат" v-model="flat.numberOfRooms">
  </div>
  <button @click="saveFlat">
    Записать
  </button>
</div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import MiniFlatCard from "@/components/MiniFlatCard.vue";
import {Store, useStore} from "@/store/index";
import DropdownSelector from "@/components/DropdownSelector.vue";

@Options({
  components: {
    DropdownSelector,
    MiniFlatCard,
  },
  computed: {
    flat() {
      return this.store.getters.flat;//возвращает геттер, который возр квартиру
    }
  }
})

export default class FlatRedactor extends Vue {
  store: Store = useStore();

  created(){
    this.store.dispatch('GET_FLAT', Number(this.$route.params.id));//1
  }
  saveFlat(){
    if (this.store.getters.flat)
    this.store.dispatch('EDIT_FLAT', this.store.getters.flat);
  }

  // flat: Flat ={
  //   square: 72,
  //   price: 9000000,
  //   complex:{id:1},
  //   id: 1,
  //   floor: 7,
  //   image:"https://cn-med.ru/media/1107/kvartira_u_metro_zvezdnaya.jpg?anchor=center&mode=crop&width=1920&rnd=132176877150000000",
  //   about:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laboru",
  //   numberOfRooms: 2,
  // }
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
  color: steelblue;
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

</style>
