<template>
  <div class="horizontal-category-select">
  <button v-for="(category, i) in categories"
          :key="i"
          :class="{'selected-button': i===selectedIndex, 'category-button':true}"
          @click="onSelect(i, category)"
  >
    {{ category[categoryKey] }}
  </button>
  </div>
</template>

<script lang="ts">
import {Vue, Options} from "vue-class-component";

@Options({
  name: "horizontal-category-select",
  props: {
    categories: {
      type: Array,
      default: []
    },
    categoryKey: {
      type: String,
      default: "name"
    },
    firstSelectedIndex: {
      type: Number,
      default: -1
    }
  },
  computed: {
    selectedIndex() {
      return this.currentSelectedIndex !== -1 ? this.currentSelectedIndex : this.firstSelectedIndesx;
    }
  }
})
export default class HorizontalCategorySelect extends Vue {
  currentSelectedIndex: number = -1;
  mounted(){
    //@ts-ignore
    this.currentSelectedIndex = this.$props.firstSelectedIndex;
    //@ts-ignore
    this.emitSelectedCategory(this.$props.categories[this.currentSelectedIndex]);
  }
  onSelect(selectedIndex: number, selectedCategory: any){
    this.currentSelectedIndex = selectedIndex;
    this.emitSelectedCategory(selectedCategory);
  }
  emitSelectedCategory(category: any) {
    this.$emit("select", category);
  }
}
</script>

<style scoped>
.horizontal-category-select {
  max-width: 1060px;
  display: grid;
  grid-template-columns: repeat(3,1fr);
  grid-gap: 15px;
  margin: 0 auto;
  margin-top: 60px;
}
.category-button {
  vertical-align: top;
  padding: 10px 15px;
  border: 1px solid black;
  font-size: 15px;
  font-weight: bold;
  min-height: 40px ;
  color: #333333;
  background-color: #E6EDF2;
  text-transform: uppercase;
  transition: color 0.2s linear;
}
.category-button:hover {
  color: lightsalmon;
}
.selected-button {
  color: lightsalmon;
}

@media (max-width: 601px) {
  .horizontal-category-select {
    grid-template-columns: 1fr;
  }
}
</style>