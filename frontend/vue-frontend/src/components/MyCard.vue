<template>
  <div class="card">
    <div class="card__image">
      <img :src="image">
    </div>
    <div class="card__desk">
      <div class="title"></div>
      {{title}}
      <div class="metro"></div>
      {{metro}}
      <div class="price"></div>
      от {{price}} руб/m²
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
      type: Number
    }
  }
})

export default class MyCard extends Vue {

}
</script>

<style scoped>

.card{
  border: 3px solid black;
  margin-top: 60px;
  width: 30%;
  height: 300px;
  margin-bottom: 30px;
  text-align:center;
}

.card__image{
}
.card__image img{
	max-width: 100%;
}

.card__desk{
text-align: left;
padding-left: 5px;
padding-top: 5px;
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
