<template>
  <div class="edit-button">редактировать</div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
@Options({
  name:"edit-button",
})

export default class EditButton extends Vue {}
</script>

<style scoped>

.edit-button{
  width: 150px;
  text-align: center;
  text-transform: uppercase;
  cursor: pointer;

  vertical-align: top;

  margin-top: 30px;
  padding: 10px 15px;

  background: white;
  padding: 10px 15px;
  border: 2px solid #76ACDA;
  border-radius: 5px;
  font-size: 15px;
  font-weight: bold;
  color: #333333;
  transition: color 0.2s linear;
}

.edit-button:hover {
  color: lightsalmon;
  background-color: #E6EDF2;

}

</style>