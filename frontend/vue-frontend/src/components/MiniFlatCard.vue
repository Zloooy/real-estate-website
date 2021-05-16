<template>
  <div class="mini-flat-card">

    <div class="box1">
      <div class="rooms">{{rooms}}к</div>

      <div class="price">{{price}} Р</div>
    </div>
    <div class="btn">
      <delete-button/>
    </div>
    <div class="box2">
      <div class="square">{{square}} м2</div>

      <div class="floor">{{floor}} этаж</div>
    </div>

  </div>

</template>


<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DeleteButton from "@/components/DeleteButton.vue";

@Options({
  name: "mini-flat-card",
  props: {
    rooms:{
      type: Number
    },
    price:{
      type: Number
    },
    square:{
      type: Number
    },
    floor:{
      type: Number
    },
  },
  components:{
    DeleteButton
  }
})


export default class MiniFlatCard extends Vue {

}

</script>

<style scoped>

.mini-flat-card {
  width: 200px;
  height: 100px;
  border: 2px solid black;
  text-align:left;
  overflow: hidden;
  border-radius: 10px;
  text-decoration: none;
  transition: color 0.2s linear;
}
.mini-flat-card:hover{
  background-color: #E6EDF2;
}

.btn{
  float: right;
}

.box1{

  display: grid;
  grid-template-columns: 1fr 7fr;
}

.box2{
  display: grid;
  grid-template-columns: 4fr 4fr;
}

.rooms{
  font-size: 20px;
  text-align:left;
  margin: 10px 10px;

}
.price{
  font-size: 20px;
  font-weight: 700;
  text-align: right;
  margin: 10px 10px;
}
.square{
  text-align:left;
  margin: 10px 10px;
}
.floor{
  text-align: right;
  margin: 10px 10px;
}
</style>

