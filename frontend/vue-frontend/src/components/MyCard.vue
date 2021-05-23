<template>
  <div class="card" @click="$emit('click', null)">
    <div class="btn">
      <delete-button
      v-if="store.getters.CAN_MANAGE_COMPLEXES"
      @click="$emit('delete-complex')"
      />
    </div>

    <div class="card__image">
      <img :src="image">
    </div>
    <div class="card__desk">
      <div class="title">
        {{title}}
      </div>
      <div class="metro">
        {{metro}}
      </div>
      <div class="price">
        от {{price}} руб/m²
      </div>
    </div>
  </div>
</template>


<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DeleteButton from "@/components/DeleteButton.vue";
import {Store, useStore} from "@/store/index";

@Options({
  name: "my-card",
  emits: ['delete-article'],
  props: {
    image: {
      type: String
    },
    title:{
      type: String
    },
    metro:{
      type: String
    },
    price:{
      type: Number
    }
  },
  components: {
    DeleteButton
  }
})

export default class MyCard extends Vue {
  store: Store = useStore();

}
</script>

<style scoped>

.card {
  position: relative;
  height: 400px;
  text-align:center;
  margin: 5px 5px;
  height: auto;
  cursor: pointer;
  border-radius: 5px;
}

.btn{
  z-index: 2;
  float: right;
}
.card:hover{
  background-color: #E6EDF2;
}

.card__image{

  overflow: hidden;
  border-radius: 5px;
  max-width: 100%;
}
.card__image img{
  max-width: 100%;
  max-height: 350px;
  border-radius: 5px;
}

.card__desk{
  text-align: left;
  padding: 10px;
}

.title{
  max-width: 100%;
  font-size: 22px;
  font-weight: 700;
  margin-bottom: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  text-decoration: none;
}

.metro{
  font-size: 15px ;
}

.price{
  font-size: 15px ;
}



@media (max-width: 900px){
  .title{
    font-size: 20px ;
  }
}
</style>
