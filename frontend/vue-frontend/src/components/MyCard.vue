<template>
  <div class="card">
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

.card {
  height: 400px;
  border: 3px solid black;
  text-align:center;
  overflow: hidden;
  margin: 5px 5px;
}

.card__image{
}
.card__image img{
	max-width: 100%;
  max-height: 300px;
}

.card__desk{
  max-width:95%;
  text-align: left;
  padding: 5px;
  text-overflow:ellipsis;
  white-space: nowrap;
}

.title{
  max-width: 10%;
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
