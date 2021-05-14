<template>
  <div class="mini-article-card">
    <div class="header">
      {{header}}
    </div>
    <div class="img">
      <img src="https://cn-med.ru/media/1107/kvartira_u_metro_zvezdnaya.jpg?anchor=center&mode=crop&width=1920&rnd=132176877150000000">
      {{img}}
    </div>
    <div class="data">
      {{data}}
    </div>
    <div class="description">
      {{description}}
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  name: "mini-article-card",
  props: {
    header:{
      type: String
    },
    img:{
      type: String
    },
    data:{
      type: String
    },
    description:{
      type: String
    }

  }
})


export default class MiniArticleCard extends Vue {

}

</script>

<style scoped>

.mini-article-card {
  width: auto;
  height: auto;
  border: 2px solid black;
  text-align:left;
  overflow: hidden;
  border-radius: 10px;
  text-decoration: none;
  transition: color 0.2s linear;
}

.mini-article-card:hover{
  background-color: #E6EDF2;
}

.header{
  margin-top: 15px;
  width: 100%;
  text-align: center;
  font-size: 20px;
  font-weight: 700;

}
.img{
  margin-top: 10px;
  height: auto;
  text-align: center;
  width: 100%;
}

.img>img{
  max-width: 100%;
  max-height: 200px;
}

.data{
  margin-top: 10px;
  width: 100%;
  text-align: center;
  font-size: 15px;
  color: steelblue;

}

.description{
  margin: 10px 0px 15px 10px;
  width: 100%;
  max-width: 300px;
  text-align: left;
  font-size: 15px;

}

@media (min-width: 601px) and (max-width: 900px) {
  .description{
    text-align: center;
    max-width: 100%;
  }
}
</style>