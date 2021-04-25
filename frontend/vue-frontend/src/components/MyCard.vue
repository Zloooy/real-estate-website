<template>
  <div class = "card">
    <div class = "card__image">
      <img :src="image">
    </div>
    <div class = "card__desk">
      <div class = "title"></div>
      <div class = "metro"></div>
      <div class = "price"></div>
    </div>
  </div>
</template>


<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  name: "my-card",
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
      type: String
    }
  }
})

export default class MyCard extends Vue {

}
</script>

<style scoped>

.card{
  margin-top: 60px;
  width: 30%;
  height: 600px;
  margin-bottom: 30px;
}

.card__image{

}

.card__desk{

}

.title{
  font-size: 25px ;
  font-weight: 700;

}

.metro{
  font-size: 15px ;
}

.price{
  font-size: 15px ;
}


</style>