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

  vertical-align: top;

  margin-top: 30px;
  padding: 10px 15px;

  border: 1px solid black;
  font-size: 15px;
  font-weight: bold;

  background-color: #E6EDF2;

  text-transform: uppercase;
  cursor: pointer;
  transition: color 0.2s linear;
}

.edit-button:hover {
  color: lightsalmon;
}

</style>