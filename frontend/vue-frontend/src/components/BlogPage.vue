<template>
  <div class="blog-page">
    <div class="header">
      БЛОГ
    </div>
    <edit-button/>
    <div class="blog-articles">
      <div class="articles-list">
        <mini-Article-card
            v-for="article in articles"
            :key="article.id"
           :header="article.name"
           :data="article.date"
           :description="article.text"
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

@Options({
  name:"blog-page",
  components:{
    MiniArticleCard,
    EditButton
  },
  computed: {
    articles(){
      return this.store.getters.articles;
    }
  }
})
export default class BLogPage extends Vue{
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_ARTICLES', undefined);
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