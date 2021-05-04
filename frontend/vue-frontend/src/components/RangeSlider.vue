<template>
<div class="slider" ref="slider" @mouseup="onTouchLeftStop">
  <div class="slider-touch-left" ref="touchLeft"
  @mousemove="onTouchLeftMove"
  @touchmove="onTouchLeftMove"
  @mousedown="onTouchLeftStart"
  @touchstart="onTouchLeftStart"
  >
    <span class="round-span"></span>
  </div>
  <div class="slider-touch-right" ref="touchRight"
  @mousemove="onTouchRightMove"
  @touchmove="onTouchRightMove"
  @mousedown="onTouchRightStart"
  @touchstart="onTouchRightStart"
  >
    <span class="round-span"></span>
  </div>
  <div class="slider-line" ref="line">
    <span ref="lineSpan"></span>
  </div>
</div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  name: "range-slider",
  props: {
    min: {
      type: Number,
      required: true
    },
    max: {
      type: Number,
      required: true
    },
    step: {
      type: Number,
      default: 1
    }
  }
})
export default class RangeSlider extends Vue{
  x: number = 0;
  startX: number = 0;
  maxX:number = 0;
  normalizeFact: number = 0;
  initialValue: number = 0;
  dragStarted: boolean = false;
  $refs!:{
    touchLeft: HTMLElement,
    touchRight: HTMLElement,
    lineSpan: HTMLElement,
    line: HTMLElement,
    slider: HTMLElement
  }
  $props!:{
    min:number,
    max:number,
    step:number
  }
  mounted(){
    this.reset();
    this.maxX = Number(this.$refs.lineSpan.offsetWidth) - this.normalizeFact;
    this.initialValue = Number(this.$refs.lineSpan.offsetWidth) - this.normalizeFact;
    this.setMinValue(this.$props.min);
    this.setMaxValue(this.$props.max);
  }
  px(val:any){
    return val + 'px';
  }
  changeValues(touchLeftLeft: ()=>number, touchRightLeft: ()=>number, lineSpanMarginLeft: ()=>number, lineSpanWidth: ()=>number){
    this.$refs.touchLeft.style.left = this.px(touchLeftLeft());
    this.$refs.touchRight.style.left = this.px(touchRightLeft());
    this.$refs.lineSpan.style.marginLeft = this.px(lineSpanMarginLeft());
    this.$refs.lineSpan.style.width = this.px(lineSpanWidth());
  }
  reset() {
    let self = this;
    this.changeValues(
        ()=>0,
        ()=>(Number(self.$refs.slider.offsetWidth) - Number(self.$refs.touchLeft.offsetWidth)),
        ()=>0,
        ()=>Number(self.$refs.slider.offsetWidth) - Number(self.$refs.touchLeft.offsetWidth)
    );
    this.x = 0;
    this.startX = 0;
  }
  setMinValue(minValue){
    console.debug(`setting minValue ${minValue}`);
    const ratio = ((minValue - this.$props.min)/(this.$props.max - this.$props.min));
    let self = this;
    this.changeValues(
        ()=>
            Math.ceil(ratio*(self.$refs.slider.offsetWidth - (self.$refs.touchLeft.offsetWidth + self.normalizeFact))),
        ()=>
            Number(self.$refs.touchRight.style.left),
        ()=>Number(self.$refs.touchLeft.offsetLeft),
        ()=>Number(self.$refs.touchRight.offsetLeft) - Number(self.$refs.touchLeft.offsetLeft),
    );
    this.$emit("min-temp-value", minValue);
    this.$emit("min-value", minValue);
  }
  setMaxValue(maxValue){
    console.debug(`setting maxValue ${maxValue}`);
    const ratio = ((maxValue - this.$props.min)/(this.$props.max - this.$props.min));
    let self=this;
    this.changeValues(
        ()=>Number(self.$refs.touchLeft.style.left),
        ()=>Math.ceil(ratio * (self.$refs.slider.offsetWidth - (self.$refs.touchLeft.offsetWidth + self.normalizeFact))+self.normalizeFact) as number,
        ()=>Number(self.$refs.touchLeft.offsetLeft),
        ()=>Number(self.$refs.touchRight.offsetLeft) - Number(self.$refs.touchLeft.offsetLeft)
    );
    this.$emit("max-temp-value", maxValue);
    this.$emit("max-value", maxValue);
  }
  onTouchLeftStart(event: MouseEvent | TouchEvent){
    event.preventDefault();
    console.debug("onTouchLeftStart");
    let eventTouch: any = event;
    if (window.TouchEvent && event instanceof TouchEvent) {
      eventTouch = event.touches[0];
    }
    this.x = Number(this.$refs.touchLeft.offsetLeft);
    this.startX = eventTouch.pageX - this.x;
    this.dragStarted = true;
    document.addEventListener('mouseup', this.onTouchLeftStop);
    document.addEventListener('touchend', this.onTouchLeftStop);
  }
  onTouchRightStart(event: MouseEvent | TouchEvent){
    event.preventDefault();
    console.debug("onTouchRightStart");
    let eventTouch: any = event;
    if (window.TouchEvent && event instanceof TouchEvent) {
      eventTouch = event.touches[0];
    }
    this.x = Number(this.$refs.touchRight.offsetLeft);
    this.startX = eventTouch.pageX - this.x;
    this.dragStarted = true;
    document.addEventListener('mouseup', this.onTouchRightStop);
    document.addEventListener('touchend', this.onTouchRightStop);
  }
  onTouchLeftStop(){
    let minValue = this.calculateMinValue();
    this.$emit('min-temp-value', minValue);
    this.$emit('min-value', minValue);
    document.removeEventListener('mouseup', this.onTouchLeftStop);
    document.removeEventListener('touchend', this.onTouchLeftStop);
    console.debug("onTouchLeftStop");
    this.dragStarted = false;
  }
  onTouchRightStop(){
    let maxValue = this.calculateMaxValue();
    this.$emit('max-temp-value', maxValue);
    this.$emit('max-value', maxValue);
    document.removeEventListener('mouseup', this.onTouchRightStop);
    document.removeEventListener('touchend', this.onTouchRightStop);
    console.debug("onTouchRightStop");
    this.dragStarted = false;
  }
  onTouchLeftMove(event: (MouseEvent | TouchEvent)){
    console.debug("onTouchLeftMove");
    console.debug(this.dragStarted);
    if (!this.dragStarted) return;
    //@ts-ignore
    let eventTouch:any = (event.touches && event instanceof TouchEvent) ? event.touches[0] : event
    this.x = eventTouch.pageX - this.startX;
    if (this.x > (Number(this.$refs.touchRight.offsetLeft) -Number(this.$refs.touchLeft.offsetWidth + 10))){
      this.x = (Number(this.$refs.touchRight.offsetLeft) - Number(this.$refs.touchLeft.offsetWidth) + 10);
    }
    else if (this.x < 0) {
      this.x = 0;
    }
    console.debug(this.$refs.touchLeft);
    this.$refs.touchLeft.style.left = this.px(this.x);
    this.$emit('min-temp-value', this.calculateMinValue());
    this.$refs.lineSpan.style.marginLeft = this.px(Number(this.$refs.touchLeft.offsetLeft));
    this.$refs.lineSpan.style.width = this.px(Number(this.$refs.touchRight.offsetLeft) - Number(this.$refs.touchLeft.offsetLeft));
  }
  onTouchRightMove(event: (MouseEvent | TouchEvent)){
    let eventTouch:any = (window.TouchEvent && event instanceof TouchEvent) ? event.touches[0] : event;
    console.debug("onTouchRightMove");
    if (!this.dragStarted) return;
    this.x = eventTouch.pageX - this.startX;
    if (this.x < (this.$refs.touchLeft.offsetLeft + this.$refs.touchLeft.offsetWidth - 10)){
      this.x = (Number(this.$refs.touchLeft.offsetLeft) + Number(this.$refs.touchLeft.offsetWidth) - 10);
    }
    else if (this.x > this.maxX){
      this.x = this.maxX;
    }
    this.$refs.touchRight.style.left = this.px(this.x);
    this.$emit('max-temp-value', this.calculateMaxValue());
    this.$refs.lineSpan.style.marginLeft = this.px(Number(this.$refs.touchLeft.offsetLeft));
    this.$refs.lineSpan.style.width = this.px(Number(this.$refs.touchRight.offsetLeft) - Number(this.$refs.touchLeft.offsetLeft));
  }
  calculateMinValue(){
    console.debug(this.$refs.lineSpan.offsetLeft, this.initialValue);
    let minValue = Number(this.$refs.lineSpan.offsetLeft / this.initialValue);
    console.debug('let minValue=', minValue);
    console.debug(JSON.stringify(this, null, 2));
    console.debug(this.$props.max, this.$props.min);
    minValue = minValue * Number(this.$props.max - this.$props.min) + this.$props.min;
    console.debug('minValue=', minValue);
    let multi = Math.floor((minValue / this.$props.step));
    console.debug('let multi=', multi);
    minValue = Number(this.$props.step) * multi;
    console.debug('minValue=', minValue);
    return minValue;
  }
  calculateMaxValue(){
    let newValue = (this.$refs.lineSpan.offsetWidth - this.normalizeFact)/this.initialValue;
    let minValue = this.$refs.lineSpan.offsetLeft / this.initialValue;
    let maxValue = minValue + newValue;
    maxValue = maxValue * (this.$props.max - this.$props.min) + this.$props.min;
    let multi = Math.floor((maxValue / this.$props.step));
    maxValue = this.$props.step * multi;
    console.debug('maxValue=', minValue);
    return maxValue;
  }
}
</script>

<style scoped>
.slider {
  display: block;
  position: relative;
  height: 36px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select:none;
  -o-user-select:none;
  user-select:none;
}
.slider-touch-left, .slider-touch-right {
  box-sizing: border-box;
  display: block;
  position: absolute;
  height: 36px;
  width: 36px;
  padding: 6px;
  z-index: 2;
}
.round-span {
  display: block;
  width: 100%;
  height: 100%;
  background: #F0F0F0;
  border: 1px solid #A4A4A4;
  border-radius: 50%;
}
.slider-line {
  box-sizing: border-box;
  position: absolute;
  display: block;
  width: calc(100% - 36px);
  left: 18px;
  top: 16px;
  height: 4px;
  border-radius: 4px;
  background: #F0F0F0;
  z-index: 0;
  overflow: hidden;
}

.slider-line span {
  display: block;
  height: 100%;
  /*width: 0%;*/
  background: orange;
}
</style>