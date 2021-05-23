<template>
  <div class="article-page" v-if="article">
    <back-button
    />
    <edit-button
        v-if="store.getters.CAN_EDIT_ARTICLES"
        @click="goToRedactor(article)"
    />
    <div class="header-article">
      {{article.title}}
      <div class="article-date">
        {{new Date(article.publishDate)}}
      </div>
    </div>

    <div class="img-article">
      <img :src="article.image">
    </div>
    <div class="text">
      <text-about
          :about="article.text"
      />
    </div>
  </div>
</template>


<script lang="ts">
  import {Options, Vue} from "vue-class-component";
  import TextAbout from "@/components/TextAbout.vue";
  import EditButton from "@/components/EditButton.vue";
  import {Store, useStore} from "@/store";
  import {Article} from "@/generated-api/data-contracts";
  import BackButton from "@/components/BackButton.vue";

  @Options({
    name: "article-page",
    props:{
      img:{
        type:String
      }
    },
    components:{
      BackButton,
      TextAbout,
      EditButton
    },
    computed: {
      article(){
        return this.store.getters.article;
      }
    }
  })

  export default class ArticlePage extends Vue{
    store: Store = useStore();
    created(){
      this.store.dispatch('GET_ARTICLE', Number(this.$route.params.id));
    }
    goToRedactor({id}:Article){
      this.$router.push(`/article/${id}/edit`)
    }
}

</script>

<style scoped>

.article-page{
  text-align: center;
  font-family: 'HelveticaNeueCyr', sans-serif;
  font-size: 15px;
  line-height: 1.6;
  color: #333333;
  max-width: 1060px;
  margin: 0 auto;
}

.back-button{
  margin: 10px 0 10px ;
}

.header-article{
  text-align: center;
  margin-top: 60px;
  width: 100%;
  font-size: 30px;
  font-weight: 700;
}

.article-date{
  text-align: center;
  margin-top: 15px;
  color: steelblue;
  width: 100%;
  font-size: 15px;
}

.img-article{
  padding-top: 30px;
  height: auto;
  text-align: center;
  width: 100%;
}

.img-article>img{
  max-width: 100%;
  max-height: 390px;
}

.text{
  padding-top: 30px;
  max-width: 100%;
  text-align: left;
}

</style>