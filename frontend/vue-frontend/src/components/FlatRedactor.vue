<template>
<div class="redactor-page" v-if="flat">

  <back-button
  />

  <div class="first-block">
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
import BackButton from "@/components/BackButton.vue";

@Options({
  components: {
    BackButton,
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
}
</script>

<style scoped>
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

</style>
