<template>
  import MyCard from "@/components/MyCard.vue";

  <div class="catalog">
    <div class="catalog-layout__sidebar_content"><!--поиск в каталоге-->
      <!--сортировка по: возрастанию цены и т.п-->
      <div class="sort">Сортировка
        <nav class="sort-ds">
          <dropdown-selector
              nameFieldTitle="name"
              defaultText="По умолчанию"
              :options="sort"
              @select="setTypeS"
          />
        </nav>
        <!--выпадающий список метро-->
      </div> <div class="metro">Метро
        <nav class="metro-ds">
          <dropdown-selector
              nameFieldTitle="name"
              defaultText="Не выбрано"
              :options="metro"
              @select="setMetroS"
          />
        </nav>
        <!--выпадающий список region-->
      </div> <div class="region">Район
        <nav class="region-ds">
          <dropdown-selector
              nameFieldTitle="name"
              defaultText="Не выбран"
              :options="region"
              @select="setRegionS"
          />
        </nav>
      </div>
      <!--выпадающий список количества комнат-->
      <div class="number-of-rooms">Количество комнат
        <nav class="number-of-rooms-ds">
          <dropdown-selector
              nameFieldTitle="name"
              defaultText="Не выбрано"
              :options="numberrooms"
              @select="numberOfRoomsS"
          />
        </nav>
      </div>
      <!-- заголовок
      стоимость от и до-->
      <div class="coast-range">Диапазон стоимости</div>

      <!-- выпажадающй список сроков сдачи-->
      <div class="term-of-delivery">Сортировка
        <nav class="term-of-delivery-ds">
          <dropdown-selector
              nameFieldTitle="name"
              defaultText="Не выбрано"
              :options="termdelivery"
              @select="termOfDeliveryS"
          />
        </nav>
      </div>
      <!-- заголовок
  площадь от и до-->
      <div class="square"></div>
      <!-- кнопка поиск-->
      <div class="btn-search"></div>
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
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {useStore} from "@/store/index";
import MyCard from "@/components/MyCard.vue";

@Options({
  name:"catalog",
  components: {
    MyCard
  },
  computed:{
    complexes(){
      return this.store.getters.complexes;
    }
  }
})
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}

.catalog {
  padding-top: 15px;
  font-size: 15px;
  display: grid;
  grid-template-columns: 1fr 3fr;
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
  .main-catalog {
    grid-template-columns: 1fr;
  }
}

@media (min-width: 601px) and (max-width: 900px) {
  .catalog{
    grid-template-columns: 1fr 2fr;
  }
  .main-catalog{
    grid-template-columns: repeat(1, minmax(250px, 3fr));
  }
}

@media (min-width: 901px) {
  .catalog{
    grid-template-columns: 1fr 3fr;
  }
  .main-catalog{
    grid-template-columns: repeat(2, minmax(250px, 3fr));
  }
}

</style>
