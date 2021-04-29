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
  display: inline-block;
  margin: 60px auto 1px
}
.category-button {
  display: inline-block;
  vertical-align: top;
  padding: 10px 15px;
  border: 3px solid black;
  font-size: 15px;
  font-weight: bold;
  color: black;
  text-transform: uppercase;
  transition: color 0.2s linear;
  margin: 0 2px;
}
.category-button:hover {
  color: lightsalmon;
}
.selected-button {
  color: lightsalmon;
}
</style>