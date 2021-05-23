<template>
  <div class="mini-article-card">
    <div class="btn">
      <delete-button
          v-if="store.getters.CAN_EDIT_ARTICLES"
          @click="$emit('delete-article')"
      />
    </div>
<div class="content" @click="$emit('click')">
    <div class="header">
      {{header}}
    </div>
    <div class="img">
      <img :src="img">
    </div>
    <div class="data">
      {{new Date(data)}}
    </div>
    <div class="description">
      {{description}}
    </div>

  </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DeleteButton from "@/components/DeleteButton.vue";
import {Store, useStore} from "@/store/index";

@Options({
  name: "mini-article-card",
  emits: ['delete-article', 'click'],
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
  text-align:left;
  border-radius: 10px;
  transition: color 0.2s linear;
}

.mini-article-card:hover{
  background-color: #E6EDF2;
}


.header{
  margin-top: 15px;
  max-width: 100%;
  text-align: center;
  font-size: 20px;
  font-weight: 700;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  text-decoration: none;
}
.btn{
  float: right;
}
.img{
  overflow: hidden;
  border-radius: 5px;
  max-width: 100%;
  margin-top: 10px;
}

.img>img{
  max-width: 100%;
  max-height: 350px;
  border-radius: 5px;
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
  width: 95%;
  text-align: left;
  font-size: 15px;
  overflow: hidden;

  text-overflow: ellipsis;
  white-space: nowrap;
  text-decoration: none;

}

@media (min-width: 601px) and (max-width: 900px) {
  .description{
    text-align: center;
    max-width: 100%;
  }
}
</style>