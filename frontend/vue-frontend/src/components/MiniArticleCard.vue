<template>
  <div class="mini-article-card">
    <div class="btn">
      <delete-button
          v-if="store.getters.CAN_EDIT_ARTICLES"
      />
    </div>

    <div class="header">
      {{header}}
    </div>
    <div class="img">
      <img :src="img">
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
import DeleteButton from "@/components/DeleteButton.vue";
import {Store, useStore} from "@/store/index";

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
  },
  components: {
    DeleteButton
  }
})


export default class MiniArticleCard extends Vue {
  store: Store = useStore();
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
.btn{
  float: right;
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