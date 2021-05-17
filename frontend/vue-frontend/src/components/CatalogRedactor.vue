<template>
  <div class="redactor-page">

    <div class="block">
      Название
      <form class="complex-name" action="/" method="post">
        <input class="name-input" type="name" placeholder="Название комплекса">
      </form>
    </div>

    <div class="block">
      Изображение
      <form class="img" action="/" method="post">
        <input class="img-input" type="str" placeholder="url изображения">
      </form>
    </div>

    <div class="block">
      Адресс
      <form class="address" action="/" method="post">
        <input class="address-input" type="String" placeholder="Адресс">
      </form>
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

    <div class="text-about">
      Текст-описание
    </div>

    <div class="flats-list">
      <mini-flat-card
          @click="goToFlat(flat)"
          v-for="flat in complex_flats"
          :key="flat.id"
          :rooms="flat.numberOfRooms"
          :price="flat.price"
          :square="flat.square"
          :floor="flat.floor"
      />
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import HorizontalCategorySelect from "@/components/HorizontalCategorySelect.vue";
import TextAbout from "@/components/TextAbout.vue";
import MainDataComplex from "@/components/MainDataComplex.vue";
import MiniFlatCard from "@/components/MiniFlatCard.vue";
import AddButton from "@/components/AddButton.vue";
import EditButton from "@/components/EditButton.vue";

@Options({
  components: {
    HorizontalCategorySelect,
    MainDataComplex,
    TextAbout,
    MiniFlatCard,
    EditButton,
    AddButton
  }
})

export default class CatalogRedactor extends Vue {
  metros=[];
  districts=[];

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
</style>
