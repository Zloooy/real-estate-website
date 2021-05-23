<template>
  <div class="redactor-page" v-if="article">

    <back-button
    />
<!--    <div class="block">
      ID
      <input class="id-input" type="number" placeholder="id" v-model="article.id">
    </div>-->

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

<!--    <div class="block">
      Дата публикации
      <div class="complex-street">
        <input class="date-input" type="text" placeholder="Введите дату публикации" v-model="article.publishDate">
      </div>
    </div>-->

    <div class="block">
      Текст статьи
      <textarea name="input" type="text" placeholder="Ввeдите текст" v-model="article.text"></textarea>
    </div>

  </div>
  <button @click="saveArticle">
    Записать
  </button>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {Store, useStore} from "@/store/index";
//import {Article} from '@/generated-api/data-contracts';
import BackButton from "@/components/BackButton.vue";

@Options({
  name: "article-redactor",
  components: {
    BackButton,
  },
  computed: {
    article(){
      return this.store.getters.article;
    }
  }
})

export default class ArticleRedactor extends Vue {
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_ARTICLE', Number(this.$route.params.id));
  }
  saveArticle(){
    console.debug("SAVING ARTICLE");
    if (this.store.getters.article)
    this.store.dispatch('EDIT_ARTICLE', this.store.getters.article);
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

.back-button{
  margin: 10px 0 10px ;
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
  margin: 0 auto;
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
