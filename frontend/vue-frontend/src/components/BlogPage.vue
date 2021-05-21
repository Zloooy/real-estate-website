<template>
  <div class="blog-page">
    <div class="header">
      БЛОГ
    </div>
    <edit-button v-if="store.getters.CAN_EDIT_ARTICLES"/>
    <div class="blog-articles">
      <div class="articles-list">
        <mini-Article-card
            @click="$router.push(`/article/${article.id}`)"
            v-for="article in articles"
            :key="article.id"
            :header="article.title"
            :data="article.publishDate"
            :img="article.image"
            :description="article.text"
            @delete-article="deleteArticle(article)"
        />
        <add-button
            v-if="store.getters.CAN_EDIT_ARTICLES"
            @click="createArticle"
        />
<!--        <mini-Article-card
            header="Новостройки у метро Звездная"
            data="14.05.2021"
        />
        <mini-Article-card
            header="Новостройки у метро Звездная"
            data="14.05.2021"
        />
        <mini-Article-card
            header="Новостройки у метро Звездная"
            data="14.05.2021"
        />-->
      </div>
    </div>

  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import MiniArticleCard from "@/components/MiniArticleCard.vue";
import {Store, useStore} from "@/store/index";
import EditButton from "@/components/EditButton.vue";
import {Article} from "@/generated-api/data-contracts";
import AddButton from "@/components/AddButton.vue";

@Options({
  name:"blog-page",
  components:{
    AddButton,
    MiniArticleCard,
    EditButton
  },
  computed: {
    articles(){
      return this.store.getters.articles;
    },
    creation_response(){
      return this.store.getters.creation_response;
    }
  },
  watch:{
    creation_response({id, created}){
      if(this.waitingForArticle){
        if (created)
        this.$router.push(`/article/${id}/edit`)
        this.waitingForArticle = false;
      }
    }
  }
})
export default class BLogPage extends Vue{
  store: Store = useStore();
  waitingForArticle: boolean = false;
  created(){
    this.store.dispatch('GET_ARTICLES', undefined);
  }
  deleteArticle({id}: Article){
    this.store.dispatch('DELETE_ARTICLE', id || 100);
  }
  createArticle(){
    this.waitingForArticle = true;
    this.store.dispatch('CREATE_ARTICLE', {name: "Введите название статьи"} as Article);
  }
}

</script>

<style>
.blog-page{
  text-align: center;
  font-family: 'HelveticaNeueCyr', sans-serif;
  font-size: 15px;
  line-height: 1.6;
  color: #333333;
  max-width: 1060px;
  margin: 0 auto;
}

.header{
  margin-top: 60px;
  width: 100%;
  text-align: center;
  font-size: 30px;
  font-weight: 700;
}

.articles-list{
  padding-top: 30px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px,auto));
  grid-gap: 40px;
}
</style>