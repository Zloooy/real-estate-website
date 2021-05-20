<template>
  <div class="redactor-page">

    <div class="block">
      ID
      <input class="id-input" type="number" placeholder="id" v-model="article.id">
    </div>

    <div class="block">
      Заголовок
      <div class="complex-name">
        <input class="title-input" type="text" placeholder="Введите звголовок" v-model="article.title">
      </div>
    </div>

    <div class="block">
      Изображение
      <textarea name="img-input" type="text" placeholder="Введите url изображения" v-model="article.image"></textarea>
    </div>

    <div class="block">
      Дата публикации
      <div class="complex-street">
        <input class="date-input" type="text" placeholder="Введите дату публикации" v-model="article.publishDate">
      </div>
    </div>

    <div class="block">
      Текст статьи
      <textarea name="input" type="text" placeholder="Ввeдите текст" v-model="article.text"></textarea>
    </div>

  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {Store, useStore} from "@/store/index";
import {Article} from '@/generated-api/data-contracts';

@Options({})

export default class ArticleRedactor extends Vue {
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_ARTICLE', Number(this.$route.params.id));
  }
  article: Article={
    id:1,
    title:"А кому сейчас легко?",
    publishDate: "20.05.2021",
    text:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laboru",
    image:"https://cn-med.ru/media/1107/kvartira_u_metro_zvezdnaya.jpg?anchor=center&mode=crop&width=1920&rnd=132176877150000000",

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
